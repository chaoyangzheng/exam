package com.exam.service.impl;

import com.exam.dao.ExamSessionDao;
import com.exam.dao.PapersDao;
import com.exam.dao.QuestionsDao;
import com.exam.entity.ExamSession;
import com.exam.entity.Papers;
import com.exam.entity.Questions;
import com.exam.service.PapersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PapersServiceImpl implements PapersService {
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private PapersDao papersDao;
    @Autowired
    private QuestionsDao questionsDao;
    @Autowired
    private ExamSessionDao examSessionDao;

    /**
     * 根据考试场次和考生查询试卷
     *
     * @param examSessionId 考试场次id
     * @param studentId     考生id
     * @return 试卷
     * @author SHIGUANGYI
     * @date 2019/10/16
     */
    @Override
    public List<Papers> selectPaper(String examSessionId, String studentId) {
        //生成试卷在redis中的key
        String redisKey = "papers:examSessionId=" + examSessionId + "studentId=" + studentId;

        //从redis中查询试卷
        List<Papers> papersList = (List<Papers>) redisTemplate.opsForValue().get(redisKey);

        if (null == papersList || 0 == papersList.size()) {
            //如果redis没有对应试卷说明可能已经交卷或还未生成
            //从mysql查询是否已有对应试卷，即是否已交卷
            papersList = papersDao.findPapersByExamSessionIdAndStudentId(examSessionId, studentId);
            if (null == papersList || 0 == papersList.size()) {
                //如果mysql中也没有对应试卷说明试卷还未生成
                //从mysql中随机抽取2道单选，2道多选，4道判断，2道简答
                List<Questions> questionsList = new ArrayList<>();
                questionsList.addAll(questionsDao.findRandomQuestionsByExamSessionIdAndQuestionsTypeId(examSessionId, 0, 2));
                questionsList.addAll(questionsDao.findRandomQuestionsByExamSessionIdAndQuestionsTypeId(examSessionId, 1, 2));
                questionsList.addAll(questionsDao.findRandomQuestionsByExamSessionIdAndQuestionsTypeId(examSessionId, 2, 4));
                questionsList.addAll(questionsDao.findRandomQuestionsByExamSessionIdAndQuestionsTypeId(examSessionId, 3, 2));
                if (questionsList.size() != 10) {
                    throw new RuntimeException("试卷生成失败");
                }

                //生成试卷id
                String papersId = UUID.randomUUID().toString().replace("-", "");
                //将题目存入试卷集合
                for (Questions questions : questionsList) {
                    Papers papers = new Papers();
                    papers.setPapersTableId(UUID.randomUUID().toString().replace("-", ""));
                    papers.setPapersId(papersId);
                    papers.setSubjectId(questions.getQuestionsSubjectId());
                    papers.setQuestionsId(questions.getQuestionsId());
                    papers.setStudentId(studentId);
                    papers.setQuestions(questions);

                    papersList.add(papers);
                }

                //获取考试时间，用于设置试卷缓存时长
                ExamSession examSession = examSessionDao.selectById(examSessionId);
                Integer duringTime = examSession.getDuringTime();

                //新试卷存入redis
                redisTemplate.opsForValue().set(redisKey, papersList);
            } else {
                //如果mysql中有对应试卷说明已交卷
                throw new RuntimeException("已交卷，不能重复考试");
            }
        }

        //消除数据中的正确答案，防止作弊
        for (int i = 0; i < papersList.size(); i++) {
            Papers papers = papersList.get(i);
            Questions questions = papers.getQuestions();
            questions.setQuestionsAnswer(null);
            papers.setQuestions(questions);
            papersList.set(i, papers);
        }

        return papersList;
    }

    /**
     * 更新试卷缓存
     *
     * @param examSessionId 考试场次id
     * @param studentId     考生id
     * @param papersIndex   题目下标
     * @param answer        学生答案
     * @return true=更新缓存成功
     * @author SHIGUANGYI
     * @date 2019/10/17
     */
    @Override
    public Boolean updatePaperCache(String examSessionId, String studentId, Integer papersIndex, String answer) {
        //生成试卷在redis中的key
        String redisKey = "papers:examSessionId=" + examSessionId + "studentId=" + studentId;

        //从redis中查询试卷
        List<Papers> papersList = (List<Papers>) redisTemplate.opsForValue().get(redisKey);

        if (null == papersList || 0 == papersList.size()) {
            throw new RuntimeException("缓存中不存在该试卷");
        }

        //获取刚答的题目
        Papers papers = papersList.get(papersIndex);
        //更新题目
        papers.setStudentAnswer(answer);
        papersList.set(papersIndex, papers);

        redisTemplate.opsForValue().set(redisKey, papersList);
        return true;
    }

    /**
     * 提交缓存中的试卷到mysql
     *
     * @param examSessionId 考试场次id
     * @param studentId     考生id
     * @return true=提交成功
     * @author SHIGUANGYI
     * @date 2019/10/17
     */
    @Override
    public Boolean submitPaperCache(String examSessionId, String studentId) {
        //生成试卷在redis中的key
        String redisKey = "papers:examSessionId=" + examSessionId + "studentId=" + studentId;

        //从redis中查询试卷
        List<Papers> papersList = (List<Papers>) redisTemplate.opsForValue().get(redisKey);

        if (null == papersList || 0 == papersList.size()) {
            throw new RuntimeException("缓存中不存在该试卷");
        }

        //存入数据库
        papersDao.insertPapers(papersList);
        //完善中间表
        Papers papers = papersList.get(0);
        String papersId = papers.getPapersId();
        examSessionDao.updatePapersIdInSessionPapersStudent(examSessionId, studentId, papersId);
        //清除缓存
        redisTemplate.delete(redisKey);

        return true;
    }

    /**
     * 自动对缓存中的试卷的选择判断进行打分，并重新存入缓存
     *
     * @param examSessionId 考试场次id
     * @param studentId     考生id
     * @return true=提交成功
     * @author SHIGUANGYI
     * @date 2019/10/17
     */
    @Override
    public Boolean autoScore(String examSessionId, String studentId) {
        //生成试卷在redis中的key
        String redisKey = "papers:examSessionId=" + examSessionId + "studentId=" + studentId;

        //从redis中查询试卷
        List<Papers> papersList = (List<Papers>) redisTemplate.opsForValue().get(redisKey);

        if (null == papersList || 0 == papersList.size()) {
            throw new RuntimeException("缓存中不存在该试卷");
        }

        for (int i = 0; i < papersList.size(); i++) {
            Papers papers = papersList.get(i);
            String studentAnswer = papers.getStudentAnswer();
            if (null == studentAnswer) {
                studentAnswer = "";
                papers.setStudentAnswer(studentAnswer);
            }

            Integer questionsTypeId = papers.getQuestions().getQuestionsTypeId();
            //自动给非简答题打分
            if (questionsTypeId < 3) {
                String questionsAnswer = papers.getQuestions().getQuestionsAnswer();
                if (questionsAnswer.equals(studentAnswer)) {
                    papers.setQuestionScore(10.0);
                } else {
                    papers.setQuestionScore(0.0);
                }
            }

            papersList.set(i, papers);
        }

        redisTemplate.opsForValue().set(redisKey, papersList);
        return true;
    }
}

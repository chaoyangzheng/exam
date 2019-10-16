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
import java.util.concurrent.TimeUnit;

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
                //从mysql中随机抽取1道单选，1道多选，2道判断，1道简答
                List<Questions> questionsList = new ArrayList<>();
                questionsList.addAll(questionsDao.findRandomQuestionsByExamSessionIdAndQuestionsTypeId(examSessionId, 0, 1));
                questionsList.addAll(questionsDao.findRandomQuestionsByExamSessionIdAndQuestionsTypeId(examSessionId, 1, 1));
                questionsList.addAll(questionsDao.findRandomQuestionsByExamSessionIdAndQuestionsTypeId(examSessionId, 2, 2));
                questionsList.addAll(questionsDao.findRandomQuestionsByExamSessionIdAndQuestionsTypeId(examSessionId, 3, 1));
                if (questionsList.size() != 5) {
                    throw new RuntimeException("试卷生成失败，题目数量不符合要求");
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

                //新试卷存入redis，缓存时长比考试时间长30分钟
                redisTemplate.opsForValue().set(redisKey, papersList, duringTime + 30, TimeUnit.MINUTES);
            } else {
                //如果mysql中有对应试卷说明已交卷
                throw new RuntimeException("已交卷，不能重复考试");
            }
        }

        return papersList;
    }
}

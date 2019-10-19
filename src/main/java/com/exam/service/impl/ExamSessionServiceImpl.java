package com.exam.service.impl;

import com.exam.dao.ExamSessionDao;
import com.exam.dao.ExamnieeInfoDao;
import com.exam.dao.PapersDao;
import com.exam.entity.ExamSession;
import com.exam.entity.ExamnieeInfo;
import com.exam.entity.Papers;
import com.exam.service.ExamSessionService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sound.midi.Soundbank;
import java.util.List;

/**
 * @author SHIGUANGYI
 * @date 2019/10/14
 */
@Service
@Transactional
public class ExamSessionServiceImpl implements ExamSessionService {
    @Autowired(required = false)
    private ExamSessionDao examSessionDao;
    @Autowired(required = false)
    private ExamnieeInfoDao examnieeInfoDao;
    @Autowired(required = false)
    private PapersDao papersDao;

    /**
     * 分页查询所有考试场次
     *
     * @param pageNum  当前页码
     * @param pageSize 每页条数
     * @return 当前页所有考试场次的list
     * @author SHIGUANGYI
     * @date 2019/10/14
     */
    @Override
    public List<ExamSession> selectAll(Integer pageNum, Integer pageSize) {
        List<ExamSession> examSessionList = null;
        PageHelper.startPage(pageNum, pageSize);
        examSessionList = examSessionDao.selectAll();
        return examSessionList;
    }

    /**
     * 根据考试场次id查询考试场次
     *
     * @param id 考试场次id
     * @return 考试场次
     * @author SHIGUANGYI
     * @date 2019/10/14
     */
    @Override
    public ExamSession selectById(String id) {
        ExamSession examSession = null;
        examSession = examSessionDao.selectById(id);
        return examSession;
    }

    /**
     * 修改指定考试场次
     *
     * @param examSession 被修改的考试场次
     * @author SHIGUANGYI
     * @date 2019/10/14
     */
    @Override
    public void updateExamSession(ExamSession examSession) {
        examSessionDao.updateExamSession(examSession);
    }

    /**
     * 添加考试场次
     *
     * @param examSession 新添加的考试场次
     * @author SHIGUANGYI
     * @date 2019/10/14
     */
    @Override
    public void insertExamSession(ExamSession examSession) {
        examSessionDao.insertExamSession(examSession);
    }

    /**
     * 删除考试场次
     *
     * @param id 考试场次id
     * @author SHIGUANGYI
     * @date 2019/10/14
     */
    @Override
    public void deleteById(String id) {
        examSessionDao.deleteById(id);
    }

    /**
     * 批量删除考试场次
     *
     * @param idList 考试场次id的list
     * @author SHIGUANGYI
     * @date 2019/10/15
     */
    @Override
    public void deleteAllById(List<String> idList) {
        examSessionDao.deleteAllById(idList);
    }

    /**
     * 检测考生是否能进行本场考试
     * 学生是否已报名本场考试
     * 当前时间考试是否正在进行
     *
     * @param examSessionId 考试场次id
     * @param studentId     考生id
     * @return true=能进行本场考试
     * @author SHIGUANGYI
     * @date 2019/10/16
     */
    @Override
    public Boolean checkStudentCanExam(String examSessionId, String studentId) {
        List<Papers> papersList = papersDao.findPapersByExamSessionIdAndStudentId(examSessionId, studentId);
        if (papersList != null && papersList.size() != 0) {
            throw new RuntimeException("已参加过本场考试");
        }
        ExamSession examSession = examSessionDao.selectById(examSessionId);
        if (null == examSession) {
            throw new RuntimeException("考试场次不存在");
        }
        ExamnieeInfo examnieeInfo = examnieeInfoDao.findExamnieeInfoByExamSessionIdAndStudentId(examSessionId, studentId);
        if (null == examnieeInfo) {
            throw new RuntimeException("未报名本场考试");
        }
        Long currentTime = System.currentTimeMillis();
        Long beginTime = examSession.getBeginTime().getTime();
        if (currentTime < beginTime) {
            throw new RuntimeException("考试未开始");
        }
        Long endTime = examSession.getEndTime().getTime();
        if (currentTime > endTime) {
            throw new RuntimeException("考试已结束");
        }
        return true;
    }

    /**
     * 分页查询当前学生的所有考试场次
     *
     * @param pageNum  当前页码
     * @param pageSize 每页条数
     * @param userId   用户id
     * @return 当前页所有考试场次的list
     * @author SHIGUANGYI
     * @date 2019/10/17
     */
    @Override
    public List<ExamSession> selectAllOfStudent(Integer pageNum, Integer pageSize, String userId) {
        PageHelper.startPage(pageNum, pageSize);
        List<ExamSession> examSessionList = examSessionDao.selectAllOfStudent(userId);
        return examSessionList;
    }

    @Override
    public List<ExamSession> findAllUnExamInfo(Integer page, Integer limit) {
//        PageHelper.startPage(page,limit);
        List<ExamSession> examList = examSessionDao.findAllUnExamInfo();
        if (examList!=null){
            return examList;
        }
        return null;
    }

    @Override
    public Integer kaiShiBaoMing(ExamSession examSession) {
        System.out.println("进来了开始报名service"+examSession.getId());
        int i = examSessionDao.selectExamStuNum(examSession.getId());
        System.out.println(i);
        if (i<examSession.getStudentNum()){
            System.out.println("进来了判断");
            return i;
        }
        return null;
    }
}

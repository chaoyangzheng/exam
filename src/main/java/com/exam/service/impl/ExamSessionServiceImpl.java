package com.exam.service.impl;

import com.exam.dao.ExamSessionDao;
import com.exam.entity.ExamSession;
import com.exam.service.ExamSessionService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author SHIGUANGYI
 * @date 2019/10/14
 */
@Service
public class ExamSessionServiceImpl implements ExamSessionService {
    @Autowired
    private ExamSessionDao examSessionDao;

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
        String id = UUID.randomUUID().toString().replace("-", "");
        examSession.setId(id);
        examSessionDao.insertExamSession(examSession);
    }
}

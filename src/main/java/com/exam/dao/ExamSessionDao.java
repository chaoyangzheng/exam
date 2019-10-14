package com.exam.dao;

import com.exam.entity.ExamSession;

import java.util.List;

/**
 * @author SHIGUANGYI
 * @date 2019/10/14
 */
public interface ExamSessionDao {
    /**
     * 查询所有考试场次
     *
     * @return 所有考试场次的list
     * @author SHIGUANGYI
     * @date 2019/10/14
     */
    List<ExamSession> selectAll();

    /**
     * 根据考试场次id查询考试场次
     *
     * @param id 考试场次id
     * @return 考试场次
     * @author SHIGUANGYI
     * @date 2019/10/14
     */
    ExamSession selectById(String id);

    /**
     * 修改指定考试场次
     *
     * @param examSession 被修改的考试场次
     * @author SHIGUANGYI
     * @date 2019/10/14
     */
    void updateExamSession(ExamSession examSession);

    /**
     * 添加考试场次
     *
     * @param examSession 新添加的考试场次
     * @author SHIGUANGYI
     * @date 2019/10/14
     */
    void insertExamSession(ExamSession examSession);
}

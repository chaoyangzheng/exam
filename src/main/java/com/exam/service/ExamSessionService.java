package com.exam.service;

import com.exam.entity.ExamSession;

import java.util.List;

/**
 * @author SHIGUANGYI
 * @date 2019/10/14
 */
public interface ExamSessionService {
    /**
     * 分页查询所有考试场次
     *
     * @param pageNum  当前页码
     * @param pageSize 每页条数
     * @return 当前页所有考试场次的list
     * @author SHIGUANGYI
     * @date 2019/10/14
     */
    List<ExamSession> selectAll(Integer pageNum, Integer pageSize);

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

    /**
     * 删除考试场次
     *
     * @param id 考试场次id
     * @author SHIGUANGYI
     * @date 2019/10/14
     */
    void deleteById(String id);

    /**
     * 批量删除考试场次
     *
     * @param idList 考试场次id的list
     * @author SHIGUANGYI
     * @date 2019/10/15
     */
    void deleteAllById(List<String> idList);
}

package com.exam.dao;

import com.exam.entity.ExamSession;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 更新session_papers_student表中的papersId
     *
     * @param examSessionId 考试场次id
     * @param studentId     学生id
     * @param papersId      试卷id
     * @author SHIGUANGYI
     * @date 2019/10/17
     */
    void updatePapersIdInSessionPapersStudent(@Param("examSessionId") String examSessionId, @Param("studentId") String studentId, @Param("papersId") String papersId);

    /**
     * 分页查询当前学生的所有考试场次
     *
     * @param userId   用户id
     * @return 当前页所有考试场次的list
     * @author SHIGUANGYI
     * @date 2019/10/17
     */
    List<ExamSession> selectAllOfStudent(String userId);
}

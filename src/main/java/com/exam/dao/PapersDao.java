package com.exam.dao;

import com.exam.entity.Papers;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PapersDao {
    /**
     * 根据考试场次和考生查询试卷
     *
     * @param examSessionId 考试场次id
     * @param studentId     考生id
     * @return 试卷
     * @author SHIGUANGYI
     * @date 2019/10/16
     */
    List<Papers> findPapersByExamSessionIdAndStudentId(@Param("examSessionId") String examSessionId, @Param("studentId") String studentId);


}

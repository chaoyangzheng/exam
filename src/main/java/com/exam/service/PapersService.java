package com.exam.service;

import com.exam.entity.Papers;

import java.util.List;

public interface PapersService {
    /**
     * 根据考试场次和考生查询试卷
     *
     * @param examSessionId 考试场次id
     * @param studentId     考生id
     * @return 试卷
     * @author SHIGUANGYI
     * @date 2019/10/16
     */
    List<Papers> selectPaper(String examSessionId, String studentId);
}

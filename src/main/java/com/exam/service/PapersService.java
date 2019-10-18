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
    Boolean updatePaperCache(String examSessionId, String studentId, Integer papersIndex, String answer);

    /**
     * 提交缓存中的试卷到mysql
     *
     * @param examSessionId 考试场次id
     * @param studentId     考生id
     * @return true=提交成功
     * @author SHIGUANGYI
     * @date 2019/10/17
     */
    Boolean submitPaperCache(String examSessionId, String studentId);

    /**
     * 自动对缓存中的试卷的选择判断进行打分，并重新存入缓存
     *
     * @param examSessionId 考试场次id
     * @param studentId     考生id
     * @return true=提交成功
     * @author SHIGUANGYI
     * @date 2019/10/17
     */
    Boolean autoScore(String examSessionId, String studentId);
}

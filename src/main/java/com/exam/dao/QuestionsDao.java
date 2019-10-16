package com.exam.dao;

import com.exam.entity.Questions;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionsDao {
    /**
     * 根据考试场次，题目类型随机抽取指定数量的题目
     *
     * @param examSessionId   考试场次id
     * @param questionsTypeId 题目类型id
     * @param questionsNum    抽取的题目数量
     * @return 题目list
     * @author SHIGUANGYI
     * @date 2019/10/16
     */
    List<Questions> findRandomQuestionsByExamSessionIdAndQuestionsTypeId(@Param("examSessionId") String examSessionId, @Param("questionsTypeId") Integer questionsTypeId, @Param("questionsNum") Integer questionsNum);

    /**
     * 根据题目id查询题目
     *
     * @param questionsId 考试场次id
     * @return 题目
     * @author SHIGUANGYI
     * @date 2019/10/16
     */
    Questions findQuestionsByQuestionsId(String questionsId);
}

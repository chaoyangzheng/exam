package com.exam.dao;

import com.exam.entity.QuestionType;
import com.exam.entity.Questions;
import org.apache.ibatis.annotations.Param;

import com.exam.entity.Questions;

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

    /**
     * 添加，修改，删除试题
     * @author RongJing
     * @date 2019/10/15
     */
    public void insertQuestions(Questions questions);




    public void updateQuestions(Questions questions);

    public void deleteQuestions(String questionsId);

    /**
     * 查看题库（全部）
     * @author RongJing
     * @date 2019/10/15
     */
    public List<Questions> findAllQuestions();

    /**
     * 按照科目，上传老师，上传时间，题的类型查看题库
     * @author RongJing
     * @date 2019/10/15
     */
    public List<Questions> findBySubjectId(Integer questionsSubjectId);

    public List<Questions> findByUploadTeacherId(String uploadTeacherId);

    public List<Questions> findByUploadTime(String uploadTime);

    public List<Questions> findByQuestionsTypeId(Integer questionsTypeId);


    public List<QuestionType> findAllQuestionsType();



}

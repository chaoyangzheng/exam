package com.exam.dao;


import com.exam.entity.Questions;

import java.util.List;

public interface QuestionsDao {
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



}

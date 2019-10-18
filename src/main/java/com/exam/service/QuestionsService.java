package com.exam.service;

import com.exam.entity.QuestionType;
import com.exam.entity.Questions;

import java.util.List;

/**
 * @author RongJing
 * @date 2019/10/15
 */
public interface QuestionsService {

    /**
     * 添加，修改，删除试题
     * @author RongJing
     * @date 2019/10/15
     */
    public void insertQuestions(Questions questions);

    public  void updateQuestions(Questions questions);

    public  void deleteQuestions(String questionsId);

    /**
     * 查看题库（全部）
     * @author RongJing
     * @date 2019/10/15
     */
    public List<Questions> findAllQuestions(Integer pageNum,Integer pageSize);

    /**
     * 按照科目，上传老师，上传时间，题的类型查看题库
     * @author RongJing
     * @date 2019/10/15
     */
    public List<Questions> findBySubjectId(Integer questionsSubjectId,Integer pageNum,Integer pageSize);

    public List<Questions> findByUploadTeacherId(String uploadTeacherId,Integer pageNum,Integer pageSize);

    public List<Questions> findByUploadTime(String uploadTime,Integer pageNum,Integer pageSize);

    public List<Questions> findByQuestionsTypeId(Integer questionsTypeId,Integer pageNum,Integer pageSize);

    public List<QuestionType> findAllQuestionsType();

}

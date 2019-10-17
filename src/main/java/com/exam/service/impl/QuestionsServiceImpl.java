package com.exam.service.impl;

import com.exam.dao.QuestionsDao;
import com.exam.entity.Questions;
import com.exam.service.QuestionsService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service("layerService")
public class QuestionsServiceImpl implements QuestionsService {

    @Autowired(required = false)
    private QuestionsDao questionsDao;


    @Override
    public void insertQuestions(Questions questions) {
        questionsDao.insertQuestions(questions);
    }

    @Override
    public void updateQuestions(Questions questions) {
        questionsDao.updateQuestions(questions);
    }

    @Override
    public void deleteQuestions(String questionsId) {
        questionsDao.deleteQuestions(questionsId);
    }

    @Override
    public List<Questions> findAllQuestions(Integer pageNum,Integer pageSize) {
        List<Questions> questions_list = null;
        PageHelper.startPage(pageNum,pageSize);
        questions_list = questionsDao.findAllQuestions();
        return questions_list;
    }

    @Override
    public List<Questions> findBySubjectId(Integer questionsSubjectId,Integer pageNum,Integer pageSize) {
        return null;
    }

    @Override
    public List<Questions> findByUploadTeacherId(String uploadTeacherId,Integer pageNum,Integer pageSize) {
        return null;
    }

    @Override
    public List<Questions> findByUploadTime(String uploadTime,Integer pageNum,Integer pageSize) {
        return null;
    }

    @Override
    public List<Questions> findByQuestionsTypeId(Integer questionsTypeId,Integer pageNum,Integer pageSize) {
        return null;
    }
}

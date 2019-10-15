package com.exam.service.impl;

import com.exam.dao.QuestionsDao;
import com.exam.entity.Questions;
import com.exam.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class QuestionsServiceImpl implements QuestionsService {

    @Autowired(required = false)
    private QuestionsDao questionsDao;


    @Override
    public void insertQuestions(Questions questions) {

    }

    @Override
    public void updateQuestions(Questions questions) {

    }

    @Override
    public void deleteQuestions(String questionsId) {

    }

    @Override
    public List<Questions> findAllQuestions() {
        return null;
    }

    @Override
    public List<Questions> findBySubjectId(Integer questionsSubjectId) {
        return null;
    }

    @Override
    public List<Questions> findByUploadTeacherId(String uploadTeacherId) {
        return null;
    }

    @Override
    public List<Questions> findByUploadTime(String uploadTime) {
        return null;
    }

    @Override
    public List<Questions> findByQuestionsTypeId(Integer questionsTypeId) {
        return null;
    }
}

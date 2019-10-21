package com.exam.service.impl;

import com.exam.dao.QuestionsDao;
import com.exam.entity.QuestionType;
import com.exam.entity.Questions;
import com.exam.service.QuestionsService;
import com.exam.utils.ImportExcelUtil;
import com.exam.utils.ImportExecl;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;


//@Service("layerService")
@Service
public class QuestionsServiceImpl implements QuestionsService {

    @Autowired(required = false)
    private QuestionsDao questionsDao;


    @Override
    public void insertQuestions(InputStream questions) {
        try {
            List<Questions> questions1 = ImportExcelUtil.importExcel(questions, Questions.class);
            questionsDao.insertQuestions(questions1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertQuestion(Questions questions) {
        questionsDao.insertQuestion(questions);

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
    public List<Questions> findAllQuestions(Integer pageNum, Integer pageSize, Integer subjectId, Integer questionsTypeId, Date uploadTime) {
        List<Questions> questions_list = null;
        PageHelper.startPage(pageNum, pageSize);
        questions_list = questionsDao.findAllQuestions(subjectId, questionsTypeId, uploadTime);
        return questions_list;
    }

    @Override
    public List<Questions> findBySubjectId(Integer questionsSubjectId, Integer pageNum, Integer pageSize) {

        List<Questions> subjectId = questionsDao.findBySubjectId(questionsSubjectId);
        return subjectId;
    }

    @Override
    public List<Questions> findByUploadTeacherId(String uploadTeacherId, Integer pageNum, Integer pageSize) {
        return questionsDao.findByUploadTeacherId(uploadTeacherId);
    }

    @Override
    public List<Questions> findByUploadTime(String uploadTime, Integer pageNum, Integer pageSize) {

        return questionsDao.findByUploadTime(uploadTime);
    }

    @Override
    public List<Questions> findByQuestionsTypeId(Integer questionsTypeId, Integer pageNum, Integer pageSize) {
        return questionsDao.findByQuestionsTypeId(questionsTypeId);
    }

    @Override
    public List<QuestionType> findAllQuestionsType() {

        return questionsDao.findAllQuestionsType();
    }


}

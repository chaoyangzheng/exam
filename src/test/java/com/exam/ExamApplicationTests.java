package com.exam;

import com.exam.entity.QuestionType;
import com.exam.entity.Subject;
import com.exam.service.QuestionsService;
import com.exam.service.SubjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExamApplicationTests {

    @Autowired
    private SubjectService subjectService;

    @Test
    public void contextLoads () {
     /*   List<Subject> allQuestionsType = subjectService.findByUser("1");

        for (Subject subject : allQuestionsType){
            System.out.println(subject);
        */
    }

}

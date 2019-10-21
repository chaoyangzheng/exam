package com.exam;

import com.exam.entity.QuestionType;
import com.exam.entity.Questions;
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
    private QuestionsService questionsService;


    @Test
    public void contextLoads () {
        List<Questions> allQuestions = questionsService.findAllQuestions(1, 10, null, null, null);

        for (Questions questions : allQuestions){
            System.out.println(questions);
        }

    }

}

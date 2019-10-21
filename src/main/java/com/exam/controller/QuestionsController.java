package com.exam.controller;


import com.exam.common.JsonResult;
import com.exam.entity.QuestionType;
import com.exam.entity.Questions;
import com.exam.service.PapersService;
import com.exam.service.QuestionsService;
import com.exam.utils.UploadUtil;
import com.github.pagehelper.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;

import java.util.*;

/**
 * 题库管理
 *
 * @author RongJing
 * @date 2019/10/16
 */
@RestController
@RequestMapping("/questions")
public class QuestionsController {

    @Autowired(required = false)
    private QuestionsService questionsService;

    @Autowired(required = false)
    private UploadUtil upload;

    @Autowired
    private PapersService papersService;

    /**
     * 查看所有试题
     *
     * @param page  当前页码
     * @param limit 每页条数
     * @return code=0,msg="查询成功"，count=所有数据，data=当前页所有试题的list
     * @author RongJing
     * @date 2019/10/17
     */
    @RequestMapping("/questionsAll.do")
    public JsonResult questionsList(Integer page, Integer limit, Integer subjectId, Integer questionsTypeId, Date uploadTime) {
        System.out.println("subjectId = " + subjectId);
        System.out.println("questionsTypeId = " + questionsTypeId);
        System.out.println("uploadTime = " + uploadTime);


        List<Questions> questionsList;
        questionsList = questionsService.findAllQuestions(page, limit, subjectId, questionsTypeId, uploadTime);
        Long count = ((Page) questionsList).getTotal();

        List<Map<String, String>> list = new ArrayList<>();

        for (Questions q : questionsList) {
            Map<String, String> map = new HashMap<>();
            String questionsInfo = q.getQuestionsInfo();

            String[] split = questionsInfo.split("%-");

            for (int i = 0; i < split.length; i++) {
                if (i == 0) {
                    map.put("questionsInfo", split[i]);
                } else {
                    char choice = (char) ('A' + i - 1);
                    map.put("answer" + choice, split[i]);
                }
            }
            map.put("questionsId",q.getQuestionsId());
            map.put("subjectName",q.getSubject().getSubjectName());
            map.put("questionsTypeId",q.getQuestionsTypeId().toString());
            map.put("questionsAnswer",q.getQuestionsAnswer());
            map.put("questionsTypeName",q.getQuestionType().getQuestionsTypeName());
            list.add(map);
        }
        return new JsonResult(0, "查询成功", count, list);
    }


    @RequestMapping("/findBySubject.do")
    public JsonResult findBySubject(Integer questionsSubjectId, Integer page, Integer limit) {
        List<Questions> questionsList = questionsService.findBySubjectId(questionsSubjectId, page, limit);
        Long count = ((Page) questionsList).getTotal();
        return new JsonResult(0, "查询成功", count, questionsList);
    }


    @RequestMapping("/findByUploadTeacher.do")
    public JsonResult findByUploadTeacher(String uploadTeacherId, Integer page, Integer limit) {
        List<Questions> questionsList = questionsService.findByUploadTeacherId(uploadTeacherId, page, limit);
        Long count = ((Page) questionsList).getTotal();
        return new JsonResult(0, "查询成功", count, questionsList);
    }

    @RequestMapping("/findByUploadTime.do")
    public JsonResult findByUploadTime(String uploadTime, Integer page, Integer limit) {
        List<Questions> questionsList = questionsService.findByUploadTime(uploadTime, page, limit);
        Long count = ((Page) questionsList).getTotal();
        return new JsonResult(0, "查询成功", count, questionsList);
    }

    @RequestMapping("/findByQuestionsType.do")
    public JsonResult findByQuestionsType(Integer questionsTypeId, Integer page, Integer limit) {
        List<Questions> questionsList = questionsService.findByQuestionsTypeId(questionsTypeId, page, limit);
        Long count = ((Page) questionsList).getTotal();
        return new JsonResult(0, "查询成功", count, questionsList);
    }


    @RequestMapping("/findAllType.do")
    @ResponseBody
    public JsonResult findAllType() {
        List<QuestionType> allQuestionsType = questionsService.findAllQuestionsType();

        return new JsonResult(0, "查询成功", null, allQuestionsType);
    }


    @RequestMapping("/deleteOne.do")
    @ResponseBody
    public JsonResult deleteOne(String questionsId) {
        questionsService.deleteQuestions(questionsId);

        return new JsonResult(0, "已成功删除", null, null);
    }


    @RequestMapping("/insertOne.do")
    public JsonResult insertOne(@RequestBody @RequestParam Questions questions, @RequestParam HttpServletRequest request) {

        if (questions.getQuestionsTypeId() != null || "".equals(questions.getQuestionsTypeId())) {
            if (questions.getQuestionsTypeId() == 1 || questions.getQuestionsTypeId() == 0) {
                questions.setQuestionsAnswer(request.getParameter("title") + "%-" + request.getParameter("titleA") + "%-" + request.getParameter("titleB") + "%-" + request.getParameter("titleC") + "%-" + request.getParameter("titleD"));
            }

            questions.setUploadTime(new Date());

            questionsService.insertQuestion(questions);

            return new JsonResult(0, "已添加成功", null, null);
        } else {
            return new JsonResult(1, "请选择试题类型", null, null);
        }

    }


    //单项选择提交
    @PostMapping("/insertOneChoice")
    public JsonResult insertOneChoice(String title, String titleA, String titleB, String titleC, String titleD, String oneChoices, Questions questions) {
        questions.setQuestionsInfo(title + "%-" + titleA + "%-" + titleB + "%-" + titleC + "%-" + titleD);
        questions.setQuestionsAnswer(oneChoices);
        return new JsonResult(0, "已成功提交", null, null);

    }

    //多项选择提交
    @PostMapping("/insertChoices")
    public JsonResult insertChoices(String title, String titleA, String titleB, String titleC, String titleD, String ChoiceA, String ChoiceB, String ChoiceC, String ChoiceD, Questions questions) {
        questions.setQuestionsInfo(title + "%-" + titleA + "%-" + titleB + "%-" + titleC + "%-" + titleD);
        if (ChoiceA == null || "".equals(ChoiceA)) {
            //当答案没有A的情况，BCD,BC,BD,CD
            if (ChoiceB == null || "".equals(ChoiceB)) {
                if ((ChoiceC == null || "".equals(ChoiceC)) && (ChoiceD == null || "".equals(ChoiceD))) {
                    return new JsonResult(1, "多选题答案选项必须为两个或两个以上", null, null);
                }
                questions.setQuestionsAnswer(ChoiceC + ChoiceD);
            } else if (ChoiceC == null || "".equals(ChoiceC)) {
                if (ChoiceD == null || "".equals(ChoiceD)) {
                    return new JsonResult(1, "多选题答案选项必须为两个或两个以上", null, null);
                } else {
                    questions.setQuestionsAnswer(ChoiceB + ChoiceD);
                }
            } else if (ChoiceD == null || "".equals(ChoiceD)) {
                if (ChoiceC == null || "".equals(ChoiceC)) {
                    return new JsonResult(1, "多选题答案选项必须为两个或两个以上", null, null);
                } else {
                    questions.setQuestionsAnswer(ChoiceB + ChoiceC);
                }
            } else {
                questions.setQuestionsAnswer(ChoiceB + ChoiceC + ChoiceD);
            }
            //当答案中没有B的情况且有A的情况，AC,AD,ACD
        } else if (ChoiceB == null || "".equals(ChoiceB)) {
            if ((ChoiceC == null || "".equals(ChoiceC)) && (ChoiceD == null || "".equals(ChoiceD))) {
                return new JsonResult(1, "多选题答案选项必须为两个或两个以上", null, null);
            } else if (ChoiceC == null || "".equals(ChoiceC)) {
                questions.setQuestionsAnswer(ChoiceA + ChoiceD);
            } else if (ChoiceD == null || "".equals(ChoiceD)) {
                questions.setQuestionsAnswer(ChoiceA + ChoiceC);
            } else {
                questions.setQuestionsAnswer(ChoiceA + ChoiceC + ChoiceD);
            }
            //当答案中没有C的情况且有AB的情况，ABD,AB
        } else if (ChoiceC == null || "".equals(ChoiceC)) {
            if (ChoiceD == null || "".equals(ChoiceD)) {
                questions.setQuestionsAnswer(ChoiceA + ChoiceB);
            } else {
                questions.setQuestionsAnswer(ChoiceA + ChoiceB + ChoiceD);
            }
        } else if (ChoiceD == null || "".equals(ChoiceD)) {
            questions.setQuestionsAnswer(ChoiceA + ChoiceB + ChoiceC);
        } else {
            questions.setQuestionsAnswer(ChoiceA + ChoiceB + ChoiceC + ChoiceD);
        }

        questionsService.insertQuestion(questions);
        return new JsonResult(0, "已成功提交", null, null);

    }

    //判断题提交
    @PostMapping("/insertJudge")
    public JsonResult insertJudge(Questions questions, String title, String judge) {
        questions.setQuestionsInfo(title);
        questions.setQuestionsAnswer(judge);
        questionsService.insertQuestion(questions);
        return new JsonResult(0, "已成功提交", null, null);
    }


    //简答题提交
    @PostMapping("/insertJd")
    public JsonResult insertJd(Questions questions, String title, String desc) {
        questions.setQuestionsInfo(title);
        questions.setQuestionsAnswer(desc);

        questionsService.insertQuestion(questions);
        return new JsonResult(0, "已成功提交", null, null);
    }


}

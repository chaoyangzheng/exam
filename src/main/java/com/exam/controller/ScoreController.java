package com.exam.controller;

import com.exam.common.JsonResult;
import com.exam.entity.Papers;
import com.exam.entity.Score;
import com.exam.entity.ShortAnswerQuestions;
import com.exam.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 考生成绩管理
 *
 * @author zhangyuanzhe
 * @date 2019/10/15
 */
@Controller
@RequestMapping("/scoreInfo")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;


    /**
     * 查询所有考生成绩信息
     * &&
     * 所有考生成绩信息总记录数
     *
     * @author zhangyuanzhe
     * @date 2019/10/15
     */
    @RequestMapping("/scoreList.do")
    public JsonResult findAllScore(Integer currentPage, Integer pageSize) {

        List<Score> allScore = scoreService.findAllScore(currentPage, pageSize);
        Long allCount = scoreService.findAllCount();

        if (allScore != null && allCount != null) {
            return new JsonResult(0, "成功", allCount, allScore);
        } else {
            return new JsonResult(1, "失败", null, null);
        }
    }


    /**
     * 查询所有未打分的简答题（SAQ）
     *
     * @author zhangyuanzhe
     * @date 2019/10/16
     */
    @RequestMapping("/teacherUpdate.do")
    public JsonResult selectSAQ() {

        List<ShortAnswerQuestions> allNoCorrectionSAQ = scoreService.findAllNoCorrectionSAQ();
        if (allNoCorrectionSAQ != null) {
            return new JsonResult(0, "成功", null, allNoCorrectionSAQ);
        } else {
            return new JsonResult(1, "失败", null, null);
        }
    }


    /**
     * 保存打好分的简答题（SAQ）
     *
     * @author zhangyuanzhe
     * @date 2019/10/16
     */
    @RequestMapping("/updateSAQScore")
    public void updateScore(String papersId, String questionsId, Double questionScore) {

        if (papersId != null && questionsId != null && questionScore != null) {
            Papers papers = new Papers();
            papers.setPapersId(papersId);
            papers.setQuestionsId(questionsId);
            papers.setQuestionScore(questionScore);
            scoreService.updateScore(papers);
        } else {
            throw new RuntimeException("分数传递失败");
        }
    }



}

package com.exam.controller;

import com.exam.common.JsonResult;
import com.exam.entity.Papers;
import com.exam.entity.Score;
import com.exam.entity.ShortAnswerQuestions;
import com.exam.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 考生成绩管理
 *
 * @author zhangyuanzhe
 * @date 2019/10/15
 */
@RestController
@RequestMapping("/scoreInfo")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;


    /**
     * 查询所有考生成绩信息
     * &&
     * 所有考生成绩信息总记录数
     * &&
     * 条件查询成绩信息
     *
     * @author zhangyuanzhe
     * @date 2019/10/15
     */
    @RequestMapping("/scoreList.do")
    public JsonResult findAllScore(Integer page, Integer limit, String selectScore, String msg) {

        System.out.println(page);
        System.out.println(limit);

        List<Score> allScore = scoreService.findAllScore(page, limit, selectScore, msg);
        Long allCount = scoreService.findAllCount(selectScore, msg);
        if (allScore != null && allCount != null) {
            return new JsonResult(0, "成功", allCount, allScore);
        } else {
            return new JsonResult(1, "失败", null, null);
        }
    }


    /**
     * 删除某个学生的成绩
     *
     * @param papersId 试卷 ID
     * @author zhangyuanzhe
     * @date 2019/10/16
     */
    @RequestMapping("/deleteScore.do")
    public JsonResult deleteScoreByPapersId(String papersId) {
        scoreService.deleteScoreByPapersId(papersId);
        return new JsonResult(0, "删除成功", null, null);
    }


    /**
     * 删除多个学生的成绩
     *
     * @param papersId 试卷 ID
     * @author zhangyuanzhe
     * @date 2019/10/16
     */
    @RequestMapping("/deleteAllScore.do")
    public JsonResult deleteAllScoreByPapersId(List<String> papersId) {
        scoreService.deleteAllScoreByPapersId(papersId);
        return new JsonResult(0, "删除成功", null, null);
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
    @RequestMapping("/updateSAQScore.do")
    public JsonResult updateScore(String papersId, String questionsId, Double questionScore) {
        if (papersId != null && questionsId != null && questionScore != null) {
            Papers papers = new Papers();
            papers.setPapersId(papersId);
            papers.setQuestionsId(questionsId);
            papers.setQuestionScore(questionScore);
            scoreService.updateScore(papers);
            List<String> questionsNoScore = scoreService.findQuestionsNoScore(papersId);
            if (questionsNoScore == null || questionsNoScore.size() == 0) {
                scoreService.updateScoreSUM(papersId);
            } else {
                return new JsonResult(1,"本试卷还有题目未被改完，无法计算总成绩",null,null);
            }
        } else {
            throw new RuntimeException("分数传递失败");
        }
        return new JsonResult(0, "成功", null, null);
    }


}

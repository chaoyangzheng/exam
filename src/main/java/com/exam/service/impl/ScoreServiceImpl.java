package com.exam.service.impl;

import com.exam.dao.ScoreDao;
import com.exam.entity.Papers;
import com.exam.entity.Score;
import com.exam.entity.ShortAnswerQuestions;
import com.exam.service.ScoreService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangyuanzhe
 * @date 2019/10/15
 */
@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired(required = false)
    private ScoreDao scoreDao;

    /**
     * 查询所有考生成绩信息
     *
     * @return 所有考生成绩信息的list
     * @author zhangyuanzhe
     * @date 2019/10/15
     */
    @Override
    public List<Score> findAllScore(Integer currentPage, Integer pageSize, String selectScore, String msg) {

        //判断当前页是否为空，或者小于1
        //当前页默认为第一页
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }

        //判断每页显示个数是否为空，或小于1
        // 默认显示个数默认值为10
        if (null == pageSize || pageSize < 1) {
            pageSize = 10;
        }

        //分页工具，传的参数为当前页，每页显示个数
        PageHelper.startPage(currentPage, pageSize);
        List<Score> allScore = scoreDao.findAllScore(selectScore, msg);

        return allScore;
    }


    /**
     * 查询所有考生成绩信息的总记录数
     *
     * @return 总记录数
     * @author zhangyuanzhe
     * @date 2019/10/15
     */
    @Override
    public Long findAllCount() {
        Long allCount = scoreDao.findAllCount();
        if (allCount == null) {
            throw new RuntimeException("没有考生分数记录");
        }
        return allCount;
    }


    /**
     * 删除某个学生的成绩
     *
     * @param papersId 试卷 ID
     * @author zhangyuanzhe
     * @date 2019/10/16
     */
    @Override
    public void deleteScoreByPapersId(String papersId) {
        if (papersId == null) {
            throw new RuntimeException("没有选择删除项");
        }
        scoreDao.deleteScoreByPapersId(papersId);
    }


    /**
     * 删除多个学生的成绩
     *
     * @param papersId 试卷 ID
     * @author zhangyuanzhe
     * @date 2019/10/16
     */
    @Override
    public void deleteAllScoreByPapersId(List<String> papersId) {
        if (papersId == null) {
            throw new RuntimeException("没有选择删除项");
        }
        scoreDao.deleteAllScoreByPapersId(papersId);
    }


    /**
     * 查询所有未被批改的简答题（SAQ）
     *
     * @author zhangyuanzhe
     * @date 2019/10/16
     */
    @Override
    public List<ShortAnswerQuestions> findAllNoCorrectionSAQ() {
        List<ShortAnswerQuestions> allNoCorrectionSAQ = scoreDao.findAllNoCorrectionSAQ();
        if (allNoCorrectionSAQ != null) {
            return allNoCorrectionSAQ;
        }
        throw new RuntimeException("所有简答题都已改完");
    }


    /**
     * 保存打好分的简答题分数（SAQ）
     *
     * @author zhangyuanzhe
     * @date 2019/10/16
     */
    @Override
    public void updateScore(Papers papers) {
        if (papers == null) {
            throw new RuntimeException("分数未提交成功");
        }
        scoreDao.updateScore(papers);
    }


    /**
     * 获取某张卷子的题目是否还有未被更改
     *
     * @author zhangyuanzhe
     * @date 2019/10/19
     */
    @Override
    public List<String> findQuestionsNoScore(String papersId) {
        return scoreDao.findQuestionsNoScore(papersId);
    }


    /**
     * 总分上传
     *
     * @author zhangyuanzhe
     * @date 2019/10/19
     */
    @Override
    public void updateScoreSUM(String papersId) {
        Double sum = scoreDao.findQuestionsScore(papersId);
        scoreDao.updateScoreSUM(papersId, sum);
    }
}

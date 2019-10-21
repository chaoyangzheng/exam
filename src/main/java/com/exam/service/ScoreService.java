package com.exam.service;

import com.exam.entity.Papers;
import com.exam.entity.Score;
import com.exam.entity.ShortAnswerQuestions;

import java.util.List;

/**
 * @author zhangyuanzhe
 * @date 2019/10/15
 */
public interface ScoreService {

    /**
     * 查询所有考生成绩信息
     *
     * @return 所有考生成绩信息的list
     * @author zhangyuanzhe
     * @date 2019/10/15
     */
    public List<Score> findAllScore(Integer currentPage, Integer pageSize, String selectScore, String msg);

    /**
     * 查询所有考生成绩信息的总记录数
     *
     * @return 总记录数
     * @author zhangyuanzhe
     * @date 2019/10/15
     */
    public Long findAllCount(String selectScore, String msg);


    /**
     * 删除某个学生的成绩
     *
     * @param papersId 试卷 ID
     * @author zhangyuanzhe
     * @date 2019/10/16
     */
    public void deleteScoreByPapersId(String papersId);


    /**
     * 删除多个学生的成绩
     *
     * @param papersId 试卷 ID
     * @author zhangyuanzhe
     * @date 2019/10/16
     */
    public void deleteAllScoreByPapersId(List<String> papersId);


    /**
     * 查询所有未被批改的简答题（SAQ）
     *
     * @return list<ShortAnswerQuestions>
     * @author zhangyuanzhe
     * @date 2019/10/16
     */
    public List<ShortAnswerQuestions> findAllNoCorrectionSAQ();


    /**
     * 保存打好分的简答题分数（SAQ）
     *
     * @author zhangyuanzhe
     * @date 2019/10/16
     */
    public void updateScore(Papers papers);


    /**
     * 获取某张卷子的题目是否还有未被更改
     *
     * @author zhangyuanzhe
     * @date 2019/10/19
     */
    public List<String> findQuestionsNoScore(String papersId);



    /**
     * 总分计算
     *
     * @author zhangyuanzhe
     * @date 2019/10/19
     */
    public void updateScoreSUM(String papersId);

}

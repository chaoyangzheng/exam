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
    public List<Score> findAllScore(Integer currentPage, Integer pageSize);

    /**
     * 查询所有考生成绩信息的总记录数
     *
     * @return 总记录数
     * @author zhangyuanzhe
     * @date 2019/10/15
     */
    public Long findAllCount();


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

}

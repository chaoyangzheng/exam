package com.exam.service;

import com.exam.entity.Score;

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
    public List<Score> findAllScore();
}

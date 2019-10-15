package com.exam.service.impl;

import com.exam.dao.ScoreDao;
import com.exam.entity.Score;
import com.exam.service.ScoreService;
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
    public List<Score> findAllScore() {
        return scoreDao.findAllScore();
    }
}

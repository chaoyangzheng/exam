package com.exam.service.impl;

import com.exam.dao.SubjectDao;
import com.exam.entity.Subject;
import com.exam.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;



@Service
public class SubjectServiceImpl implements SubjectService {


    @Autowired(required = false)
    private  SubjectDao subjectDao;

    /**
     * 查询所有科目
     *
     * @return 所有科目的list
     * @author RongJing
     * @date 2019/10/15
     */
    @Override
    public List<Subject> findAllSubject() {

        return subjectDao.findAllSubject();
    }



    /**
     * 添加科目
     *
     * @author RongJing
     * @date 2019/10/15
     */
    @Override
    public void insertSubject(Subject subject) {

        subjectDao.insertSubject(subject);


    }





    /**
     * 删除科目
     *
     * @author RongJing
     * @date 2019/10/15
     */
    @Override
    public void deleteSubject(String subjectId) {

        subjectDao.deleteSubject(subjectId);
    }


    /**
     * 查找一，二级标题
     *
     * @author RongJing
     * @date 2019/10/15
     */
    @Override
    public Subject findByParentId(String parentId) {

        return subjectDao.findByParentId(parentId);
    }
}

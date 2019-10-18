package com.exam.service.impl;

import com.exam.dao.SubjectDao;
import com.exam.entity.Subject;
import com.exam.entity.User;
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

        List<Subject> allSubject = subjectDao.findAllSubject();

        return allSubject;

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
     * 查找二级标题
     *
     * @author RongJing
     * @date 2019/10/15
     */
    @Override
    public Subject findByParentId(Integer parentId) {

        if(parentId == null){
            subjectDao.findAllFirst();
        }
        return subjectDao.findByParentId(parentId);
    }

    @Override
    public List<Subject> findAll() {
        List<Subject> subjectList = subjectDao.findAll();
        return subjectList;
    }

    @Override
    public Subject findBySubjectId(Integer subjectId) {

        return subjectDao.findById(subjectId);
    }

    @Override
    public List<Subject> findAllFirst() {

        return subjectDao.findAllFirst();

    }

    @Override
    public List<Subject> findAllSecond(Integer parentId) {

        return subjectDao.findAllSecond(parentId);
    }

    @Override
    public List<Subject> findByUser(User user) {
        List<Subject> subjectByUser = subjectDao.findByUser(user);
        return subjectByUser;

    }


}

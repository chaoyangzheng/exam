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
     * 查询所有一级科目
     *
     * @return 所有一级科目的list
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
     * 查找二级标题，根据parentId找到相应的subject对象
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


    /**
     * 查找所有subject对象
     *
     * @author RongJing
     * @date 2019/10/15
     */
    @Override
    public List<Subject> findAll() {
        List<Subject> subjectList = subjectDao.findAll();
        return subjectList;
    }


    /**
     * 根据subjectId找到相应的subject对象
     * @author RongJing
     * @date 2019/10/15
     */
    @Override
    public Subject findBySubjectId(Integer subjectId) {

        return subjectDao.findById(subjectId);
    }


    /**
     * 查找所有一级标题
     * @date 2019/10/15
     */
    @Override
    public List<Subject> findAllFirst() {

        return subjectDao.findAllFirst();

    }


    /**
     * 查找所有二级标题
     * @date 2019/10/15
     */
    @Override
    public List<Subject> findAllSecond(Integer subjectId) {

        return subjectDao.findAllSecond(subjectId);
    }


    /**
     * 根据用户id找到相应的subjectId,在找到相应的subject对象
     * @date 2019/10/15
     */
    @Override
    public List<Subject> findByUser(User user) {
        List<Subject> subjectByUser = subjectDao.findByUser(user);
        return subjectByUser;

    }



    /**
     * 查询所有科目的平均分
     *
     * @return 所有科目的平均分的list
     * @author SHIGUANGYI
     * @date 2019/10/19
     */
    @Override
    public List<Subject> findAllAverageScore(){
        return subjectDao.findAllAverageScore();
    }

    /**
     * 查询所有科目的最高分
     *
     * @return 所有科目的最高分的list
     * @author SHIGUANGYI
     * @date 2019/10/19
     */
    @Override
    public List<Subject> findAllMaxScore(){
        return subjectDao.findAllMaxScore();
    }
}

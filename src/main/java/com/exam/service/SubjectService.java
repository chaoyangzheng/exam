package com.exam.service;


import com.exam.entity.Subject;

import java.util.List;

/**
 * @author rongjing
 * @date 2019/10/1
 */
public interface SubjectService {

    /**
     * 查询所有科目
     *
     * @return 所有科目的list
     * @author RongJing
     * @date 2019/10/15
     */
    public List<Subject> findAllSubject();




    /**
     * 添加科目
     *
     * @author RongJing
     * @date 2019/10/15
     */
    public void insertSubject(Subject subject);


    /**
     * 删除科目
     *
     * @author RongJing
     * @date 2019/10/15
     */
    public void deleteSubject(String subjectId);



    /**
     * 查找一，二级标题
     *
     * @author RongJing
     * @date 2019/10/15
     */
    public Subject findByParentId(String parentId);

    /*zxs*/
    public List<Subject> findAll();

    /**
     * 查询所有科目的平均分
     *
     * @return 所有科目的平均分的list
     * @author SHIGUANGYI
     * @date 2019/10/19
     */
    List<Subject> findAllAverageScore();

    /**
     * 查询所有科目的最高分
     *
     * @return 所有科目的最高分的list
     * @author SHIGUANGYI
     * @date 2019/10/19
     */
    List<Subject> findAllMaxScore();
}

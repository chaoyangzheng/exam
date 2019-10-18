package com.exam.service;


import com.exam.entity.Subject;
import com.exam.entity.User;
import org.apache.ibatis.annotations.Select;

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
    public Subject findByParentId(Integer parentId);

    /*zxs*/
    public List<Subject> findAll();


    public Subject findBySubjectId(Integer subjectId);


    public List<Subject> findAllFirst();



    public List<Subject> findAllSecond(Integer subjectId);



public List<Subject> findByUser(User user);


}

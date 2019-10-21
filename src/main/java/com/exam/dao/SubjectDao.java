package com.exam.dao;

import com.exam.entity.Subject;

import com.exam.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SubjectDao {


    /**
     * 查询所有一级科目
     *
     * @return 所有一级科目的list
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
     * 根据parentId找到subject对象，
     * @author RongJing
     * @date 2019/10/15
     */
    public Subject findByParentId(Integer parentId);

    /*zxs*/

     public List<Subject> findAll();



    /**
     * 根据subjectId查到相应的subject对象
     * @return 所有一级科目的list
     */
    Subject findById(Integer subjectId);


     /**
      * 查询所有一级科目
      * @author chaoyang
      * @date 2019/10/15
      * @return java.util.List<com.exam.entity.Subject>
      */
     @Select("select subject_id,subject_name from t_subject where parent_id is null")
     public List<Subject> findAllFirst();


    /**
     * 查询所有二级科目，一级标题subjectId作为parentId来查
     * @author RongJing
     * @date 2019/10/17
     * @return java.util.List<com.exam.entity.Subject>
     */
    @Select("select subject_id,subject_name,parent_id from t_subject where parent_id = #{subjectId}")
    public List<Subject> findAllSecond(Integer subjectId);


    /**
     * 根据用户的id，来找到相应的subjectId,找到相应的对象
     * @author RongJing
     * @date 2019/10/17
     * @return java.util.List<com.exam.entity.Subject>
     */
    public List<Subject> findByUser(User user);


}

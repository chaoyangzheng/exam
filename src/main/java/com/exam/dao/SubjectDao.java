package com.exam.dao;

import com.exam.entity.Subject;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SubjectDao {


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
      * 查询所有一级科目
      * @author chaoyang
      * @date 2019/10/15
      * @return java.util.List<com.exam.entity.Subject>
      */
     @Select("select subject_id,subject_name from t_subject where parent_id is null")
     public List<Subject> findAllFirst();
}

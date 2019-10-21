package com.exam.dao;

import com.exam.entity.Subject;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
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

     /**
      * 删除用户对应的教师学科
      * @author chaoyang
      * @date 2019/10/16
      * @param  * @param userId
      * @return java.lang.Integer
      */
     @Delete("delete from t_user_subject where user_id = #{userId} ")
     public Integer deleteUserSubject(@Param("userId") String userId);

     @Insert("insert into t_user_subject (user_subject_id,user_id,subject_id) values(#{userSubjectId},#{userId},#{subjectId})")
     public Integer addUserUserSubject(@Param("userId") String userId,@Param("subjectId") Integer subjectId,@Param("userSubjectId") String userSubjectId);
}

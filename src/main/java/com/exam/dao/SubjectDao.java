package com.exam.dao;

import com.exam.entity.Subject;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SubjectDao {

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

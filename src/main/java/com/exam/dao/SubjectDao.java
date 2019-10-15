package com.exam.dao;

import com.exam.entity.Subject;

import java.util.List;

public interface SubjectDao {

    /*zxs*/
     public List<Subject> findAll();

    Subject findById(Integer subjectId);
}

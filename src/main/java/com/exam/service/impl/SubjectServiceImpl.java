package com.exam.service.impl;

import com.exam.dao.SubjectDao;
import com.exam.entity.Subject;
import com.exam.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SubjectServiceImpl implements SubjectService {
    /*zxs*/
    @Autowired(required = false)
    private SubjectDao subjectDao;

    @Override
    public List<Subject> findAll() {
        List<Subject> subjectList = subjectDao.findAll();
        return subjectList;
    }
    /*end*/
}

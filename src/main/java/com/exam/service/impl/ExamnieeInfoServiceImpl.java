package com.exam.service.impl;

import com.exam.common.JsonResult;
import com.exam.dao.ExamnieeInfoDao;
import com.exam.entity.ExamnieeInfo;
import com.exam.service.ExamnieeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExamnieeInfoServiceImpl implements ExamnieeInfoService {
    @Autowired(required = false)
    private ExamnieeInfoDao examnieeInfoDao;

    @Override
    public JsonResult findAllExamnieeInfo() {
        List<ExamnieeInfo> allExamnieeInfo = examnieeInfoDao.findAllExamnieeInfo();
        if (allExamnieeInfo!=null){
            Long count = examnieeInfoDao.findAllExamnieeNumber();
            return new JsonResult(0,"正常",count,allExamnieeInfo);
        }
        return new JsonResult(1,"失败",0L,null);
    }

    @Override
    public JsonResult deleteExamnieeInfoById(String examnieeId) {
        int i = examnieeInfoDao.deleteExamnieeInfoById(examnieeId);
        if (i>0){
            List<ExamnieeInfo> allExamnieeInfo = examnieeInfoDao.findAllExamnieeInfo();
            Long count = examnieeInfoDao.findAllExamnieeNumber();
            return new JsonResult(0,"删除成功",count,allExamnieeInfo);
        }
        return new JsonResult(1,"失败",0L,null);
    }

    @Override
    public JsonResult addExamnieeInfo(ExamnieeInfo examnieeInfo) {
        int i = examnieeInfoDao.addExamnieeInfo(examnieeInfo);
        if (i>0){
//            Long count = examnieeInfoDao.findAllExamnieeNumber();
            return new JsonResult(0,"成功",null,"添加成功");
        }
        return new JsonResult(1,"失败",0L,null);
    }

    @Override
    public JsonResult updateExamnieeInfo(ExamnieeInfo examnieeInfo) {
        int i = examnieeInfoDao.updateExamnieeInfo(examnieeInfo);
        if (i>0){
            Long count = examnieeInfoDao.findAllExamnieeNumber();
            return new JsonResult(0,"成功",null,"修改成功");
        }
        return new JsonResult(1,"失败",0L,null);
    }

    @Override
    public JsonResult findExamnieeInfoByEid(String eId) {
        ExamnieeInfo examniee = examnieeInfoDao.findExamnieeInfoByEid(eId);
        if (examniee!=null) {
            return new JsonResult(0, "成功", null, examniee);
        }return new JsonResult(1,"失败",null,"查询失败");
    }

    @Override
    public JsonResult findExamnieeInfoById(String examnieeId) {
        ExamnieeInfo examniee = examnieeInfoDao.findExamnieeInfoById(examnieeId);
        if (examniee!=null) {
            return new JsonResult(0, "成功", null, examniee);
        }return new JsonResult(1,"失败",null,"查询失败");
    }

    @Override
    public JsonResult findExamnieeInfoByName(String examnieeName) {
        ExamnieeInfo examniee = examnieeInfoDao.findExamnieeInfoByName(examnieeName);
        if (examniee!=null) {
            return new JsonResult(0, "成功", null, examniee);
        }return new JsonResult(1,"失败",null,"查询失败");
    }
}

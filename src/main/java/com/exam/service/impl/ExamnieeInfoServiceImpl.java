package com.exam.service.impl;

import com.exam.common.JsonResult;
import com.exam.dao.ExamnieeInfoDao;
import com.exam.entity.ExamnieeInfo;
import com.exam.service.ExamnieeInfoService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExamnieeInfoServiceImpl implements ExamnieeInfoService {
    @Autowired(required = false)
    private ExamnieeInfoDao examnieeInfoDao;

    @Override
    public JsonResult findAllExamnieeInfo(Integer pageNum, Integer pageSize) {
        List<ExamnieeInfo> allExamnieeInfo = null;
        PageHelper.startPage(pageNum, pageSize);
        allExamnieeInfo = examnieeInfoDao.findAllExamnieeInfo();
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

    /**
     * 查询报考该考试场次的学生
     *
     * @param pageNum       当前页码
     * @param pageSize      每页条数
     * @param examSessionId 考试场次id
     * @return 考生list
     * @author SHIGUANGYI
     * @date 2019/10/15
     */
    @Override
    public List<ExamnieeInfo> findExamnieeInfoByExamSessionId(Integer pageNum, Integer pageSize, String examSessionId) {
        PageHelper.startPage(pageNum, pageSize);
        List<ExamnieeInfo> examnieeInfoList = examnieeInfoDao.findExamnieeInfoByExamSessionId(examSessionId);
        return examnieeInfoList;
    }
}

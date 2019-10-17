package com.exam.service.impl;

import com.exam.common.JsonResult;
import com.exam.dao.ExamnieeInfoDao;
import com.exam.entity.ExamnieeInfo;
import com.exam.service.ExamnieeInfoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ExamnieeInfoServiceImpl implements ExamnieeInfoService {
    @Autowired(required = false)
    private ExamnieeInfoDao examnieeInfoDao;

    @Override
    public JsonResult findAllExamnieeInfo(Integer pageNum, Integer pageSize) {
        Page<ExamnieeInfo> allExamnieeInfo = null;
        PageHelper.startPage(pageNum, pageSize);
        allExamnieeInfo = (Page<ExamnieeInfo>) examnieeInfoDao.findAllExamnieeInfo();
        Long count = allExamnieeInfo.getTotal();
        if (allExamnieeInfo!=null){
//            Long count = examnieeInfoDao.findAllExamnieeNumber();
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
        String s =  UUID.randomUUID().toString();
        examnieeInfo.seteId(s);
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
        List<ExamnieeInfo> examnieeInfoById = examnieeInfoDao.findExamnieeInfoById(examnieeId);
        if (examnieeInfoById!=null) {
            return new JsonResult(0, "成功", null, examnieeInfoById);
        }return new JsonResult(1,"失败",null,"查询失败");
    }

    @Override
    public JsonResult findExamnieeInfoByName(String examnieeName) {
        ExamnieeInfo examniee = examnieeInfoDao.findExamnieeInfoByName(examnieeName);
        if (examniee!=null) {
            return new JsonResult(0, "成功", null, examniee);
        }return new JsonResult(1,"失败",null,"查询失败");
    }

    @Override
    public JsonResult deleteExamnieeInfoByIds(List<String> ids) {
        int i = examnieeInfoDao.deleteExamnieeInfoByIds(ids);
        if (i == 0){
            return new JsonResult(1,"失败",null,"删除失败");
        }else if (i>0){
            return new JsonResult(0,"成功",null,"删除了"+i+"条记录");
        }
//        System.out.println(i);
        return null;
    }

    @Override
    public JsonResult findAllExamnieeInfoAndSubjectName(Integer pageNum, Integer pageSize) {
        Page<ExamnieeInfo> allExamnieeInfo = null;
        PageHelper.startPage(pageNum, pageSize);
        allExamnieeInfo = (Page<ExamnieeInfo>) examnieeInfoDao.findAllExamnieeInfoAndSubjectName();
        Long count = allExamnieeInfo.getTotal();
        if (allExamnieeInfo!=null){
//            Long count = examnieeInfoDao.findAllExamnieeNumber();
            return new JsonResult(0,"正常",count,allExamnieeInfo);
        }
        return new JsonResult(1,"失败",0L,null);
    }

    @Override
    public JsonResult findExamnieeInfoByNames(String examnieeName) {
        List<ExamnieeInfo> examnieeInfoList = examnieeInfoDao.findExamnieeInfoByNames(examnieeName);
        if (examnieeInfoList.isEmpty()){
            return new JsonResult(1,"未找到此人",null,"查询失败");
        }
        return new JsonResult(0,"查询成功",null,examnieeInfoList);
    }

    @Override
    public JsonResult findExamnieeInfoBySubjectName(String subjectName) {
        List<ExamnieeInfo> list = examnieeInfoDao.findExamnieeInfoBySubjectName(subjectName);
        if (list.isEmpty()){
            return new JsonResult(1,"该学科没有考生或未找到有此学科",null,"查询失败");
        }
        return new JsonResult(0,"查询成功",null,list);
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

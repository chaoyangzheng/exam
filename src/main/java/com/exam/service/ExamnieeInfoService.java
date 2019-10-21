package com.exam.service;

import com.exam.common.JsonResult;
import com.exam.entity.ExamnieeInfo;

import java.util.List;

public interface ExamnieeInfoService {
    public JsonResult findAllExamnieeInfo(Integer pageNum, Integer pageSize);

    public JsonResult deleteExamnieeInfoById(String examnieeId);

    public JsonResult addExamnieeInfo(ExamnieeInfo examnieeInfo);

    public JsonResult updateExamnieeInfo(ExamnieeInfo examnieeInfo);

    public JsonResult findExamnieeInfoByEid(String eId);

    public JsonResult findExamnieeInfoById(String examnieeId);

    public JsonResult findExamnieeInfoByName(String examnieeName);

    public JsonResult deleteExamnieeInfoByIds(List<String> ids);

    public JsonResult findAllExamnieeInfoAndSubjectName(Integer pageNum, Integer pageSize);

    public JsonResult findExamnieeInfoByNames(String examnieeName);

    public JsonResult findExamnieeInfoBySubjectName(String subjectName);

    public JsonResult addKaoShi(ExamnieeInfo examnieeInfo,String id);

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
    List<ExamnieeInfo> findExamnieeInfoByExamSessionId(Integer pageNum, Integer pageSize, String examSessionId);
}

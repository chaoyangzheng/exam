package com.exam.service;

import com.exam.common.JsonResult;
import com.exam.entity.ExamnieeInfo;

import java.util.List;

public interface ExamnieeInfoService {
    public JsonResult findAllExamnieeInfo();

    public JsonResult deleteExamnieeInfoById(String examnieeId);

    public JsonResult addExamnieeInfo(ExamnieeInfo examnieeInfo);

    public JsonResult updateExamnieeInfo(ExamnieeInfo examnieeInfo);

    public JsonResult findExamnieeInfoByEid(String eId);

    public JsonResult findExamnieeInfoById(String examnieeId);

    public JsonResult findExamnieeInfoByName(String examnieeName);
}

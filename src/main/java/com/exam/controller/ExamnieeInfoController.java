package com.exam.controller;


import com.exam.common.JsonResult;
import com.exam.entity.ExamSession;
import com.exam.entity.ExamnieeInfo;
import com.exam.entity.Subject;
import com.exam.service.ExamnieeInfoService;
import com.exam.service.SubjectService;
import com.exam.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/examnieeInfo")
public class ExamnieeInfoController {
    @Autowired
    private ExamnieeInfoService examnieeInfoService;

    @Autowired
    private SubjectService subjectService;

    @RequestMapping("/examnieeInfoList.do")
    public JsonResult examnieeInfoList(Integer page, Integer limit) {
        JsonResult allExamnieeList = examnieeInfoService.findAllExamnieeInfo(page, limit);
        return allExamnieeList;
    }

    @RequestMapping("/examnieeInfoDel.do")
    public JsonResult examnieeInfoDel(String examnieeId) {
        JsonResult jsonResult = examnieeInfoService.deleteExamnieeInfoById(examnieeId);
        return jsonResult;
    }

    @RequestMapping("/getSubject.do")
    public JsonResult goUpdate(String examnieeId) {
        List<Subject> subjectList = subjectService.findAll();
        if (subjectList!=null){
            return new JsonResult(0,"成功",null,subjectList);
        }
        return new JsonResult(1,"失败",null,null);
    }

    @RequestMapping("/examnieePhotoUpload.do")
    public JsonResult examnieePhotoUpload(MultipartFile file) {
        UploadUtil uploadUtil = new UploadUtil();
        String s = uploadUtil.ImgUpload(file);
        return  new JsonResult(0,"成功",null,s);
}

    @RequestMapping("/addOrUpdateExamniee.do")
    public JsonResult addOrUpdateExamniee(ExamnieeInfo examnieeInfo) {
        System.out.println(examnieeInfo);
        if (examnieeInfo.geteId() == null || examnieeInfo.geteId().equals("")){
            System.out.println("add");
            JsonResult jsonResult = examnieeInfoService.addExamnieeInfo(examnieeInfo);
            return jsonResult;
        }else if (examnieeInfo.geteId() != null || !examnieeInfo.geteId().equals("")) {
            System.out.println("update");
            JsonResult jsonResult = examnieeInfoService.updateExamnieeInfo(examnieeInfo);
            return jsonResult;
        }return new JsonResult(1,"失败",null,"操作失败");
    }
}

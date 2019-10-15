package com.exam.controller;


import com.exam.common.JsonResult;
import com.exam.entity.ExamSession;
import com.exam.service.ExamnieeInfoService;
import com.exam.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/examnieeInfo")
public class ExamnieeInfoController {
    @Autowired
    private ExamnieeInfoService examnieeInfoService;

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

    @RequestMapping("/goAdd.do")
    public JsonResult goUpdate(String examnieeId) {
        JsonResult jsonResult = examnieeInfoService.deleteExamnieeInfoById(examnieeId);
        return jsonResult;
    }

    @RequestMapping("/examnieePhotoUpload.do")
    public JsonResult examnieePhotoUpload(MultipartFile file) {
        UploadUtil uploadUtil = new UploadUtil();
        String s = uploadUtil.ImgUpload(file);
        return  new JsonResult(0,"成功",null,s);
}

}

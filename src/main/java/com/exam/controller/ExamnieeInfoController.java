package com.exam.controller;


import com.exam.common.JsonResult;
import com.exam.entity.ExamSession;
import com.exam.entity.ExamnieeInfo;
import com.exam.entity.Subject;
import com.exam.entity.User;
import com.exam.service.ExamnieeInfoService;
import com.exam.service.SubjectService;
import com.exam.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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
        JsonResult allExamnieeList = examnieeInfoService.findAllExamnieeInfoAndSubjectName(page, limit);
        return allExamnieeList;
    }

    @RequestMapping("/examnieeInfoDel.do")
    public JsonResult examnieeInfoDel(String examnieeId) {
        JsonResult jsonResult = examnieeInfoService.deleteExamnieeInfoById(examnieeId);
        return jsonResult;
    }

    @RequestMapping("/examnieeInfoDelAll.do")
    public JsonResult examnieeInfoDelAll(@RequestBody List<ExamnieeInfo> examnieeInfoList) {

        List<String> ids = new ArrayList<>();
        for (ExamnieeInfo e:examnieeInfoList
             ) {
            ids.add(e.geteId());
        }
        System.out.println(ids);
        if (ids.isEmpty()){
            return new JsonResult(1,"请至少选择一个人",null,"请重试");
        }
        JsonResult jsonResult = examnieeInfoService.deleteExamnieeInfoByIds(ids);
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
            JsonResult info = examnieeInfoService.findExamnieeInfoById(examnieeInfo.getExamnieeId());
            List<ExamnieeInfo> infos =  (List<ExamnieeInfo>)info.getData();
            if (infos.isEmpty()){
                JsonResult jsonResult = examnieeInfoService.addExamnieeInfo(examnieeInfo);
                return jsonResult;
            }
            ExamnieeInfo info1 = null;
            try {
                info1 = infos.get(0);
            } catch (Exception e) {
                e.printStackTrace();
                return new JsonResult(1,"数据异常",null,"");
            }
            if (!(info1.getExamnieeName().equals(examnieeInfo.getExamnieeName())&&info1.getExamnieeSex().equals(examnieeInfo.getExamnieeSex()))){
                return new JsonResult(1,"添加失败，该考生已报名或考生信息不符",null,"");
            }else if (examnieeInfo.getExamnieeName().equals(info1.getExamnieeName())&&examnieeInfo.getExamnieeId().equals(info1.getExamnieeId())
                    &&examnieeInfo.getExamnieeSex().equals(info1.getExamnieeSex())&&examnieeInfo.getExamnieeSubjectId().equals(info1.getExamnieeSubjectId())){
                return new JsonResult(1,"添加失败，该场次考生已经报名",null,"");
            }else {
                JsonResult jsonResult = examnieeInfoService.addExamnieeInfo(examnieeInfo);
                return new JsonResult(0,"成功",null,"");
            }

        }else if (examnieeInfo.geteId() != null || !examnieeInfo.geteId().equals("")) {
            System.out.println("update");
            JsonResult a = examnieeInfoService.findExamnieeInfoById(examnieeInfo.getExamnieeId());
            List<ExamnieeInfo> b = (List<ExamnieeInfo>) a.getData();
            ExamnieeInfo c = null;
            try {
                c = b.get(0);
            } catch (Exception e) {
                e.printStackTrace();
                return new JsonResult(1,"请勿修改身份证，如仍需修改，请联系管理员",null,"");
            }
            if (c.getExamnieeName().equals(examnieeInfo.getExamnieeName())){
                String examnieeId = c.getExamnieeId();
                char c1 = examnieeId.charAt(16);
                if (c1 % 2 == 1){
                    System.out.println(c1+":进来了男");
                    if (examnieeInfo.getExamnieeSex().equals("男")){
                        System.out.println("--------");
                        if (c.getExamnieeSubjectId().equals(examnieeInfo.getExamnieeSubjectId())){
                            return new JsonResult(1,"该考生已经有此学科的考试，请勿重复添加",null,"");
                        }
                        JsonResult jsonResult = examnieeInfoService.updateExamnieeInfo(examnieeInfo);
                        return new JsonResult(0,"成功",null,"");
                    }else {
                        return new JsonResult(1,"性别修改错误，请核实后重试",null,"");
                    }
                }else if (c1 % 2 == 0){
                    System.out.println(c1+":进来了女");
                    if (examnieeInfo.getExamnieeSex().equals("女")){
                    JsonResult jsonResult = examnieeInfoService.updateExamnieeInfo(examnieeInfo);
                    return jsonResult;
                    }else {
                        return new JsonResult(1,"性别修改错误，请核实后重试",null,"");
                     }
                }else {
                    return new JsonResult(1,"数据异常",null,"");
                }
            }else {
                return new JsonResult(1,"修改失败，请重新核实信息,请勿修改身份证号和密码，如需修改请联系管理员",null,"");
            }
        }return new JsonResult(1,"异常操作",null,"操作失败");
    }

    /**/
    @RequestMapping("/selectExamnieeInfo.do")
    public JsonResult selectExamnieeInfo(String selectType,String condition) {
        //selectType 1是身份证，2是姓名，3是学科
        System.out.println(selectType+"+"+condition);
        String info = "";
        switch (selectType){
            case "1":
                info = condition;
                JsonResult jsonResult = examnieeInfoService.findExamnieeInfoById(info);
                System.out.println(jsonResult.getData());
                return jsonResult;
            case "2":
                info = condition;
                JsonResult jsonResult1 = examnieeInfoService.findExamnieeInfoByNames(info);
                return jsonResult1;
            case "3":
                info = condition;
                JsonResult jsonResult2 = examnieeInfoService.findExamnieeInfoBySubjectName(info);
                return jsonResult2;
        }
       return null;
    }

    @RequestMapping("/userRegister.do")
    public JsonResult userRegister(User user) {

        return new JsonResult(0,"哈哈",null,"");
    }

    @RequestMapping("/userLogin.do")
    public JsonResult userLogin(User user) {

        return new JsonResult(0,"哈哈",null,"");
    }
}

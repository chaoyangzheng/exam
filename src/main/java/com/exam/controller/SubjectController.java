package com.exam.controller;

import com.exam.common.JsonResult;
import com.exam.entity.Subject;
import com.exam.entity.User;
import com.exam.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 题库管理
 *
 * @author RongJing
 * @date 2019/10/16
 */

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;


    @PostMapping("/findAllSubject.do")
    public JsonResult findAllSubject(){

        List<Subject> allSubject = subjectService.findAllSubject();
        if(allSubject == null || "".equals(allSubject)){
            return new JsonResult(1,"查询失败",null,null);
        }
        else {
            return new JsonResult(0,"查询成功",null,allSubject);
        }

    }



    @PostMapping("/findSon.do")
    public JsonResult findSon(Integer parentId){

        if(parentId == null || "".equals(parentId)){
            return new JsonResult(1,"请先选择科目",null,null);
        }else{
            List<Subject> allSecond = subjectService.findAllSecond(parentId);
            return  new JsonResult(0,"查询成功",null,allSecond);
        }
    }



    @PostMapping("/judgeSubject.do")
    public JsonResult findByUser(@RequestBody User user){


        List<Subject> byUser = subjectService.findByUser(user);

        String subject = null;

        for(int i = 0;i < byUser.size();i ++){

            Integer parent = byUser.get(i).getParentId();
            if(parent == null){
                 subject = byUser.get(i).getSubjectName();
            }else if(parent != null){
                Subject subjectId = subjectService.findBySubjectId(parent);
                subject = subjectId.getSubjectName();
            }

        }

        return  new JsonResult(0,"查询成功",null,subject);

    }



}
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

    /**
     * 查询所有一级标题
     * 使用方法findAllsubject
     * @author RongJing
     * @date 2019/10/16
     */
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


    /**
     * 根据一级标题subjectid查询对应的所有二级标题
     * 使用方法findAllSecond
     * @author RongJing
     * @date 2019/10/16
     */
    @PostMapping("/findSon.do")
    public JsonResult findSon(Integer subjectId){
        System.out.println(subjectId);
        List<Subject> allSecond = subjectService.findAllSecond(subjectId);
        for(Subject subject1 : allSecond){
            System.out.println(subject1);
        }
        return new JsonResult(0,"",null,allSecond);
    }



    /**
     * 判断用户的subjectId（相当于权限，英语老师只能上传英语试题）
     * 不管是英语四级老师，还是英语六级老师，都返回为英语老师
     * 使用方法，findBySubjectId(parentId作为subjectId进行查找)
     * @return subjectName(一级标题)
     * @author RongJing
     * @date 2019/10/16
     */
    @PostMapping("/judgeSubject.do")
    public JsonResult findByUser(@RequestBody User user){

        List<Subject> byUser = subjectService.findByUser(user);

        String subject = null;

        for(int i = 0;i < byUser.size();i ++){

            Integer parent = byUser.get(i).getParentId();

            Integer judgeSubject = byUser.get(i).getSubjectId();

            if(judgeSubject == null){
                return new JsonResult(1,"您没有该权限",null,null);
            }

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

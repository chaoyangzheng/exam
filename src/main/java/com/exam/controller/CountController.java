package com.exam.controller;

import com.exam.common.JsonResult;
import com.exam.entity.Subject;
import com.exam.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 统计管理
 *
 * @author SHIGUANGYI
 * @date 2019/10/19
 */
@RestController
@RequestMapping("/count")
public class CountController {
    @Autowired
    private SubjectService subjectService;

    /**
     * 查询所有科目的平均分
     *
     * @return code=0,msg="查询成功",count=null，data=包含平均分的科目list
     * @author SHIGUANGYI
     * @date 2019/10/19
     */
    @RequestMapping("/allSubjectAverageScore.do")
    public JsonResult allSubjectAverageScore() {
        List<Subject> subjectList = subjectService.findAllAverageScore();
        return new JsonResult(0, "查询成功", null, subjectList);
    }

    /**
     * 查询所有科目的最高分
     *
     * @return code=0,msg="查询成功",count=null，data=包含最高分的科目list
     * @author SHIGUANGYI
     * @date 2019/10/19
     */
    @RequestMapping("/allSubjectMaxScore.do")
    public JsonResult allSubjectMaxScore() {
        List<Subject> subjectList = subjectService.findAllMaxScore();
        return new JsonResult(0, "查询成功", null, subjectList);
    }
}

package com.exam.controller;

import com.exam.common.JsonResult;
import com.exam.entity.ExamSession;
import com.exam.entity.ExamnieeInfo;
import com.exam.service.ExamSessionService;
import com.exam.service.ExamnieeInfoService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 考试场次管理
 *
 * @author SHIGUANGYI
 * @date 2019/10/14
 */
@RestController
@RequestMapping("/examSession")
public class ExamSessionController {
    @Autowired
    private ExamSessionService examSessionService;
    @Autowired
    private ExamnieeInfoService examnieeInfoService;

    /**
     * 分页查询所有考试场次
     *
     * @param page  当前页码
     * @param limit 每页条数
     * @return code=0,msg="查询成功",count=总考试场次数，data=当前页所有考试场次的list
     * @author SHIGUANGYI
     * @date 2019/10/14
     */
    @RequestMapping("/examSessionList.do")
    public JsonResult examSessionList(Integer page, Integer limit) {
        List<ExamSession> examSessionList = examSessionService.selectAll(page, limit);
        Long count = ((Page) examSessionList).getTotal();
        return new JsonResult(0, "查询成功", count, examSessionList);
    }

    /**
     * 根据考试场次id查询考试场次
     *
     * @param id 考试场次id
     * @return code=0,msg="查询成功",count=null，data=考试场次
     * @author SHIGUANGYI
     * @date 2019/10/14
     */
    @RequestMapping("/selectExamSessionById.do")
    public JsonResult selectExamSessionById(String id) {
        ExamSession examSession = examSessionService.selectById(id);
        return new JsonResult(0, "查询成功", null, examSession);
    }

    /**
     * 修改指定考试场次
     *
     * @param examSession 被修改的考试场次
     * @return code=0,msg="修改成功",count=null，data=null
     * @author SHIGUANGYI
     * @date 2019/10/14
     */
    @RequestMapping("/updateExamSession.do")
    public JsonResult updateExamSession(ExamSession examSession) {
        examSessionService.updateExamSession(examSession);
        return new JsonResult(0, "修改成功", null, null);
    }

    /**
     * 添加考试场次
     *
     * @param examSession 新添加的考试场次
     * @return code=0,msg="修改成功",count=null，data=null
     * @author SHIGUANGYI
     * @date 2019/10/14
     */
    @RequestMapping("/insertExamSession.do")
    public JsonResult insertExamSession(ExamSession examSession) {
        examSessionService.insertExamSession(examSession);
        return new JsonResult(0, "新建成功", null, null);
    }

    /**
     * 删除考试场次
     *
     * @param id 考试场次id
     * @return code=0,msg="删除成功",count=null，data=null
     * @author SHIGUANGYI
     * @date 2019/10/14
     */
    @RequestMapping("/deleteExamSession.do")
    public JsonResult deleteExamSession(String id) {
        examSessionService.deleteById(id);
        return new JsonResult(0, "删除成功", null, null);
    }

    /**
     * 批量删除考试场次
     *
     * @param idList 考试场次id的list
     * @return code=0,msg="删除成功",count=null，data=null
     * @author SHIGUANGYI
     * @date 2019/10/15
     */
    @RequestMapping("/deleteAllExamSession.do")
    public JsonResult deleteAllExamSession(@RequestBody List<String> idList) {
        examSessionService.deleteAllById(idList);
        return new JsonResult(0, "删除成功", null, null);
    }

    /**
     * 查询报考该考试场次的学生
     *
     * @param page  当前页码
     * @param limit 每页条数
     * @param id    考试场次id
     * @return code=0,msg="删除成功",count=该考试场次学生总数，data=当前页学生列表
     * @author SHIGUANGYI
     * @date 2019/10/15
     */
    @RequestMapping("/selectStudentsOfExamSession.do")
    public JsonResult selectStudentsOfExamSession(Integer page, Integer limit, String id) {
        List<ExamnieeInfo> examnieeInfoList = examnieeInfoService.findExamnieeInfoByExamSessionId(page, limit, id);
        Long count = ((Page) examnieeInfoList).getTotal();
        return new JsonResult(0, "查询成功", count, examnieeInfoList);
    }
}

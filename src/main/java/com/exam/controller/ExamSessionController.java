package com.exam.controller;

import com.exam.common.JsonResult;
import com.exam.entity.ExamSession;
import com.exam.entity.ExamnieeInfo;
import com.exam.service.ExamSessionService;
import com.exam.service.ExamnieeInfoService;
import com.exam.service.PapersService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private PapersService papersService;

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

    /**
     * 检测考生是否能进行本场考试
     * 学生是否已报名本场考试
     * 当前时间考试是否正在进行
     *
     * @param examSessionId 考试场次id
     * @param studentId     考生id
     * @return code=0,msg="可以考试",count=null，data=null
     * @author SHIGUANGYI
     * @date 2019/10/16
     */
    @RequestMapping("/checkStudentCanExam.do")
    public JsonResult checkStudentCanExam(String examSessionId, String studentId) {
        examSessionService.checkStudentCanExam(examSessionId, studentId);
        return new JsonResult(0, "可以考试", null, null);
    }

    /**
     * 根据考试场次和考生查询试卷
     *
     * @param examSessionId 考试场次id
     * @param studentId     考生id
     * @return code=0,msg="查询成功",count=null，data={remainingTime:考试剩余时间(s),papersList:试卷}
     * @author SHIGUANGYI
     * @date 2019/10/16
     */
    @RequestMapping("/selectPaper.do")
    public JsonResult selectPaper(String examSessionId, String studentId) {
        Map<String, Object> papersMap = papersService.selectPaper(examSessionId, studentId);

        //计算剩余时间
        Long endTime = (Long) papersMap.get("endTime");
        Long now = System.currentTimeMillis();
        Long remainingTime = (endTime - now) / 1000;

        Map<String, Object> map = new HashMap<>();
        map.put("remainingTime", remainingTime);
        map.put("papersList", papersMap.get("papersList"));

        return new JsonResult(0, "查询成功", null, map);
    }

    /**
     * 缓存学生刚答的题目答案
     *
     * @param examSessionId 考试场次id
     * @param studentId     考生id
     * @param papersIndex   题目下标
     * @param answer        单选、判断、简答的答案
     * @param answerA       多选选项A
     * @param answerB       多选选项B
     * @param answerC       多选选项C
     * @param answerD       多选选项D
     * @return code=0,msg="更新成功",count=null，data=null
     * @author SHIGUANGYI
     * @date 2019/10/17
     */
    @RequestMapping("/cachePaper.do")
    public JsonResult cachePaper(String examSessionId, String studentId, Integer papersIndex, String answer, String answerA, String answerB, String answerC, String answerD) {
        //根据接到的答案判断是否是多选题，并生成存入缓存的答案
        if (null == answer) {
            answer = "";
            if (null != answerA) {
                answer += answerA;
            }
            if (null != answerB) {
                answer += answerB;
            }
            if (null != answerC) {
                answer += answerC;
            }
            if (null != answerD) {
                answer += answerD;
            }
        }

        //更新缓存
        papersService.updatePaperCache(examSessionId, studentId, papersIndex, answer);

        return new JsonResult(0, "更新成功", null, null);
    }

    /**
     * 缓存学生刚答的题目答案，并将整张试卷提交到数据库
     *
     * @param examSessionId 考试场次id
     * @param studentId     考生id
     * @param papersIndex   刚答的题目下标
     * @param answer        单选、判断、简答的答案
     * @param answerA       多选选项A
     * @param answerB       多选选项B
     * @param answerC       多选选项C
     * @param answerD       多选选项D
     * @return code=0,msg="提交成功",count=null，data=null
     * @author SHIGUANGYI
     * @date 2019/10/17
     */
    @RequestMapping("/submitPaper.do")
    public JsonResult submitPaper(String examSessionId, String studentId, Integer papersIndex, String answer, String answerA, String answerB, String answerC, String answerD) {
        //根据接到的答案判断是否是多选题，并生成存入缓存的答案
        if (null == answer) {
            answer = "";
            if (null != answerA) {
                answer += answerA;
            }
            if (null != answerB) {
                answer += answerB;
            }
            if (null != answerC) {
                answer += answerC;
            }
            if (null != answerD) {
                answer += answerD;
            }
        }

        //更新缓存
        papersService.updatePaperCache(examSessionId, studentId, papersIndex, answer);
        //自动打分
        papersService.autoScore(examSessionId, studentId);
        //提交到mysql
        papersService.submitPaperCache(examSessionId, studentId);
        return new JsonResult(0, "提交成功", null, null);
    }

    /**
     * 分页查询当前考生的所有考试场次
     *
     * @param page   当前页码
     * @param limit  每页条数
     * @return code=0,msg="查询成功",count=总考试场次数，data=当前页所有考试场次的list
     * @author SHIGUANGYI
     * @date 2019/10/17
     */
    @RequestMapping("/examSessionListOfStudent.do")
    public JsonResult examSessionListOfStudent(Integer page, Integer limit) {
        List<ExamSession> examSessionList = examSessionService.selectAllOfStudent(page, limit,"1");
        Long count = ((Page) examSessionList).getTotal();
        return new JsonResult(0, "查询成功", count, examSessionList);
    }
}

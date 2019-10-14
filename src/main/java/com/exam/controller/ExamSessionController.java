package com.exam.controller;

import com.exam.common.JsonResult;
import com.exam.entity.ExamSession;
import com.exam.service.ExamSessionService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/examSession")
public class ExamSessionController {
    @Autowired
    private ExamSessionService examSessionService;

    @PostMapping("/examSessionList.do")
    public JsonResult examSessionList(Integer page, Integer limit) {
        List<ExamSession> examSessionList = examSessionService.selectAll(page, limit);
        return new JsonResult(0, "", ((Page) examSessionList).getTotal(), examSessionList);
    }
}

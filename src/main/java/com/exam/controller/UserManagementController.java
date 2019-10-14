package com.exam.controller;

import com.exam.entity.User;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 用户管理
 * @author chaoyang
 * @date 2019/10/14
 */
@Controller
@RequestMapping("/system/userManagement")
public class UserManagementController {
    @Autowired
    private UserService userService;
    /**
     * 通过角色或老师的学科查询所有用户
     * @author chaoyang
     * @date 2019/10/14
     */
    @RequestMapping("/findAllUser")
    public void findUser(String roleId,String subjectId){


        List<User> users = userService.findAllUserByRole(roleId, subjectId);


    }
}

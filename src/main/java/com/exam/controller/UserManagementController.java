package com.exam.controller;

import com.exam.common.JsonResult;
import com.exam.entity.User;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户管理
 * @author chaoyang
 * @date 2019/10/14
 */
@RestController
@RequestMapping("/system/userManagement")
public class UserManagementController {
    @Autowired
    private UserService userService;
    /**
     * 通过角色或老师的学科查询所有用户,根据输入的名称搜索用户 使用模糊查询
     * @author chaoyang
     * @date 2019/10/14
     */
    @RequestMapping("/findUsersByRoleSubjectLikeName.do")
    public JsonResult findUser(String roleId, String subjectId,String name){
        System.out.println("roleId = " + roleId);
        System.out.println("subjectId = " + subjectId);
        System.out.println("name = " + name);
        if ("".equals(roleId)) {
            roleId=null;
        }
        if ("".equals(subjectId)) {
            subjectId=null;
        }
        if ("".equals(name)) {
            name=null;
        }

        List<User> users = userService.findAllUserByRole(roleId, subjectId,name);

        return new JsonResult(0,"success",Long.valueOf(users.size()),users);
    }
    /**
     *
     * @author chaoyang
     * @date 2019/10/14
     */
    public JsonResult findUserByName(String name){



        return null;
    }
}

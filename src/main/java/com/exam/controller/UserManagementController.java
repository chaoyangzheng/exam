package com.exam.controller;

import com.exam.common.JsonResult;
import com.exam.entity.User;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
    public JsonResult findUser(String roleId, String subjectId,String name,Integer page,Integer limit){
        /*System.out.println("page = " + page);
        System.out.println("limit = " + limit);
        System.out.println("roleId = " + roleId);
        System.out.println("subjectId = " + subjectId);
        System.out.println("name = " + name);*/
        if ("".equals(roleId)) {
            roleId=null;
        }
        if ("".equals(name)) {
            name=null;
        }
        if ("".equals(subjectId)) {
            subjectId=null;
        }
        Integer subject_id = null;
        if (subjectId!=null){
            subject_id = Integer.valueOf(subjectId);
        }

        List<User> users = userService.findAllUserByRole(roleId, subject_id,name,page,limit);

        return new JsonResult(0,"success",Long.valueOf(users.size()),users);
    }
    /**
     *
     * @author chaoyang
     * @date 2019/10/14
     */
    @RequestMapping("/findAllRolesSubjects.do")
    public JsonResult findAllRolesSubjects(){


        Map map = userService.findAllRolesSubjects();
        return new JsonResult(0,"success",Long.valueOf(map.size()),map);
    }
    // 修改角色 如果角色为教师的话还应该增加科目
    public JsonResult updateUserPerm(String userId,String roleId,String subject,String type){
        System.out.println("subject = " + subject);
        System.out.println("userId = " + userId);
        System.out.println("roleId = " + roleId);
        System.out.println("type = " + type);
        //当type的值为“add时，表示添加，为update时为修改
        if ("add".equals(type)) {
            return new JsonResult(null,null,null,null);
        }

        return new JsonResult(null,null,null,null);
    }
    //冻结/解冻
    public JsonResult updateUserState(String userId,String roleId,String type){
        System.out.println("userId = " + userId);
        System.out.println("roleId = " + roleId);
        System.out.println("type = " + type);
        //type值为block时为冻结该用户，unblock时解冻
        if ("block".equals(type)) {
            return new JsonResult(null,null,null,null);
        }
        return new JsonResult(null,null,null,null);
    }
}

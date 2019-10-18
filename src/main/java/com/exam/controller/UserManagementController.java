package com.exam.controller;

import com.exam.common.JsonResult;
import com.exam.entity.Role;
import com.exam.entity.Subject;
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
        if (page==null){
            page =1;
        }
        if (limit==null){
            limit=10;
        }
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
        for (int i = 0;i<users.size();i++){
            if (users.get(i).getRole()==null){
                users.get(i).setRole(new Role());
            }
            if (users.get(i).getSubject()==null){
                users.get(i).setSubject(new Subject());
            }
        }

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
    @RequestMapping("/updateUserPerm.do")
    public JsonResult updateUserRole(String userId,String roleId,String subjectId,String type,String oldRoleId,String oldSubjectId){
        System.out.println("subject = " + subjectId);
        System.out.println("userId = " + userId);
        System.out.println("roleId = " + roleId);
        System.out.println("type = " + type);
        System.out.println("oldRoleId = " + "%"+oldRoleId+"%");
        System.out.println("oldSubjectId = " + oldSubjectId);
        //当type的值为“add时，表示添加，为edit时为修改
      /*  if ("add".equals(type)) {
            if (subjectId==null){
                userService.addUserRoleSubject(userId,roleId,null);
            }else {
                userService.addUserRoleSubject(userId,roleId,Integer.valueOf(subjectId));
            }
            return new JsonResult(0,"",0L,"");
        }else {
            if (subjectId==null){
                userService.updateUserRoleSubject(userId,roleId,null,oldRoleId);
            }else {
                userService.updateUserRoleSubject(userId,roleId,Integer.valueOf(subjectId),oldRoleId);
            }
        }*/

        return new JsonResult(0,"",0L,"");
    }
    @RequestMapping("/deleteUserRoleSubject.do")
    public JsonResult deleteUserRoleSubject(String userId,String roleId){
        User user = userService.deleteUserRole(userId, roleId);
        return new JsonResult(0,"",0L,"");
    }

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

    @RequestMapping("/findUserByUserId.do")
    public JsonResult findUserByUserId(String userId){
        System.out.println(userId);
        User user = userService.findUserByUserId(userId);
        return new JsonResult(0,"success",null,user);
    }
}

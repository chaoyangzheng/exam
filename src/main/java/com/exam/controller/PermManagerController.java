package com.exam.controller;

import com.exam.common.JsonResult;
import com.exam.entity.Permission;
import com.exam.entity.Role;
import com.exam.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chaoyang
 * @date 2019/10/18
 */
@RestController
@RequestMapping("/system/permManagement")
public class PermManagerController {
    @Autowired
    private PermissionService permissionService;

    //增删改查，针对角色的权限管理 查询是zTree

    @RequestMapping("/findAllPerms.do")
    public JsonResult findAllPerms(){
        List<Permission> perms = permissionService.findAllPerms();
        return new JsonResult(0,"",0L,perms);
    }
    @RequestMapping("/findAllRolePerm.do")
    public JsonResult findAllRolePerm(){
        List<Role> roles = permissionService.findAllRolePerm();
        return new JsonResult(0,"",0L,roles);
    }
   /* @RequestMapping("/addPerm.do")
    public JsonResult addPerm(Permission permission){
        return new JsonResult(0,"",0L,"");
    }*/
    @RequestMapping("/updatePerm.do")
    public JsonResult updatePerm(Permission permission){
        return new JsonResult(0,"",0L,"");
    }
    @RequestMapping("/deletePerm.do")
    public JsonResult deletePerm(String roleId){
        return new JsonResult(0,"",0L,"");
    }
}

package com.exam.controller;

import com.exam.common.JsonResult;
import com.exam.entity.Role;
import com.exam.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chaoyang
 * @date 2019/10/17
 */
@RestController
@RequestMapping("/system/roleManagement")
public class RoleManagerController {
    @Autowired(required = false)
    private RoleService roleService;

    //角色的增删改查，增加，删除，修改，显示所有
    @RequestMapping("/findAllRoles.do")
    public JsonResult findAllRoles(){
        List<Role> roles = roleService.findAllRoles();
        return new JsonResult(0,"",Long.valueOf(roles.size()),roles);
    }
    @RequestMapping("/addRole.do")
    public JsonResult addRole(Role role){
        Integer num = roleService.addRole(role);
        return new JsonResult(num-1,"",0L,"");
    }
    @RequestMapping("/updateRole.do")
    public JsonResult updateRole(Role role){
        Integer num = roleService.updateRole(role);
        return new JsonResult(num-1,"",0L,"");
    }
    @RequestMapping("/deleteRole.do")
    public JsonResult deleteRole(String roleId){
        Integer num = roleService.deleteRole(roleId);
        return new JsonResult(num-1,"",0L,"");
    }
}

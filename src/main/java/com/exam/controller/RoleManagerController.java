package com.exam.controller;

import com.exam.common.JsonResult;
import com.exam.entity.Role;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chaoyang
 * @date 2019/10/17
 */
@RestController
@RequestMapping("/system/roleManagement")
public class RoleManagerController {

    //角色的增删改查，增加，删除，修改，显示所有
    @RequestMapping("/findAllRoles.do")
    public JsonResult findAllRoles(){
        return new JsonResult(0,"",0L,"");
    }
    @RequestMapping("/addRole.do")
    public JsonResult addRole(Role role){
        return new JsonResult(0,"",0L,"");
    }
    @RequestMapping("/updateRole.do")
    public JsonResult updateRole(Role role){
        return new JsonResult(0,"",0L,"");
    }
    @RequestMapping("/deleteRole.do")
    public JsonResult deleteRole(String roleId){
        return new JsonResult(0,"",0L,"");
    }
}

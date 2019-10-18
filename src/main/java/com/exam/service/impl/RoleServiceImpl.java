package com.exam.service.impl;

import com.exam.dao.RoleDao;
import com.exam.entity.Role;
import com.exam.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

/**
 * @author chaoyang
 * @date 2019/10/18
 */
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;
    @Override
    public  List<Role> findAllRoles () {
        List<Role> roles = roleDao.findAllRole();
        return roles;
    }

    @Override
    public Integer addRole (Role role) {
        role.setRoleId(UUID.randomUUID().toString().replace("-",""));
        int num = roleDao.addRole(role);
        return num;
    }

    @Override
    public Integer updateRole (Role role) {
        int num = roleDao.updateRole(role);
        return num;
    }

    @Override
    public Integer deleteRole (String roleId) {
        roleDao.deleteRoleUserPerm(roleId);
        int num = roleDao.deleteRole(roleId);
        return num;
    }
}

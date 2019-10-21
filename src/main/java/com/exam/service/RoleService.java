package com.exam.service;

import com.exam.entity.Role;

import java.util.List;

public interface RoleService {


    public List<Role> findAllRoles();
    public Integer addRole(Role role);
    public Integer updateRole(Role role);
    public Integer deleteRole(String roleId);
}

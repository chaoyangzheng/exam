package com.exam.service;


import com.exam.entity.Permission;
import com.exam.entity.Role;

import java.util.List;

public interface PermissionService {
    public List<Role> findAllRolePerm();
    public List<Permission> findAllPerms();
    public Integer updatePerm(Permission permission);
    public Integer deletePerm(String permId);
}

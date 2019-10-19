package com.exam.service.impl;

import com.exam.dao.PermissionDao;
import com.exam.entity.Permission;
import com.exam.entity.Role;
import com.exam.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chaoyang
 * @date 2019/10/18
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired(required = false)
    private PermissionDao permissionDao;
    @Override
    public List<Role> findAllRolePerm () {

        return permissionDao.findAllRolesPerms();
    }

    @Override
    public List<Permission> findAllPerms () {
        return permissionDao.findAllPerms();
    }

    @Override
    public Integer updatePerm (Permission permission) {
        return permissionDao.updatePerm(permission);
    }

    @Override
    public Integer deletePerm (String permId) {
        permissionDao.deleteRolePerm(permId);
        //permissionDao.deletePerm(permId);
        return permissionDao.deletePerm(permId);
    }
}

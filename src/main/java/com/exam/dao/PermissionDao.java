package com.exam.dao;
/*zxs*/

import com.exam.entity.Permission;

import java.util.List;

public interface PermissionDao {
    //判断当前用户的菜单栏显示什么
    List<Permission> findPermsByIdCard(String idCard);

    List<Permission> findPermsName(List<Integer> ids);
}

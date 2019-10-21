package com.exam.service.impl;

import com.exam.dao.PermissionDao;
import com.exam.entity.Permission;
import com.exam.entity.Role;
import com.exam.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author chaoyang
 * @date 2019/10/18
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired(required = false)
    private PermissionDao permissionDao;
    @Override
    //原生的需要title和children  有children的为有子树的，没标明的就没有下一级
    public List<Role> findAllRolePerm () {
        //所以先查角色，当作title
        //在查父节点 在查子节点 返回list
        //每一个都是一个map   “String,Object   "title",list
        //父子节点 "children",map

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


    @Override
    public Map<String, Object> findPermsByIdCard(String idCard) {
        //获取到所有该用户可以使用的所有二级菜单
        List<Permission> permsByIdCard = permissionDao.findPermsByIdCard(idCard);
        Set<Integer> idss = new HashSet<>();
        //转成set去重
        for (int i = 0; i < permsByIdCard.size(); i++) {
            idss.add(permsByIdCard.get(i).getParentId());
        }
        //再转存list
        List<Integer> ids = new ArrayList<>(idss);
//        for (Integer i: ids
//             ) {
//            System.out.println("测试"+i);
//        }
        //用去重后的菜单名的父菜单id查出父菜单名
        List<Permission> permsName = permissionDao.findPermsName(ids);
//        for (Permission s:permsName
//             ) {
//            System.out.println("findPermsName"+s);
//        }
        //让父菜单名与二级子菜单列表对应
        Map<String, Object> a = new HashMap<>();
        for (int i = 0; i < permsName.size(); i++) {
            List<Permission> res = new ArrayList<>();
            for (int j = 0; j < permsByIdCard.size(); j++) {
                if (permsName.get(i).getPermId() == permsByIdCard.get(j).getParentId()) {
//                    System.out.println("进来了循环");
                    res.add(permsByIdCard.get(j));
                    a.put(permsName.get(i).getPermName(), res);
                }
            }
        }
        if (a != null) {
            return a;
        }
        return null;
    }
}

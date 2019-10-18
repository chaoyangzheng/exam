package com.exam.service.impl;

import com.exam.dao.PermissionDao;
import com.exam.entity.Permission;
import com.exam.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Ids;

import java.util.*;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired(required = false)
    private PermissionDao permissionDao;

    @Override
    public Map<String, Object> findPermsByIdCard(String idCard) {
        //获取到所有该用户可以使用的所有二级菜单
        List<Permission> permsByIdCard = permissionDao.findPermsByIdCard(idCard);
        Set<Integer> idss = new HashSet<>();
        //转成set去重
        for (int i = 0; i <permsByIdCard.size() ; i++) {
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
        Map<String,Object> a = new HashMap<>();
        for (int i = 0; i < permsName.size(); i++) {
            List<Permission> res = new ArrayList<>();
            for (int j = 0; j <permsByIdCard.size() ; j++) {
                if (permsName.get(i).getPermId() == permsByIdCard.get(j).getParentId()){
//                    System.out.println("进来了循环");
                    res.add(permsByIdCard.get(j));
                    a.put(permsName.get(i).getPermName(),res);
                }
            }
        }
        if (a!=null){
            return a;
        }
        return null;
    }
}

package com.exam.service.impl;

import com.exam.dao.UserDao;
import com.exam.entity.User;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    private UserDao userDao;
    @Override
    public List<User> findAllUserByRole (String roleId, String subjectId,String name) {
        //使用动态sql，判断role的值，为角色，查看某个角色，为null，查看所有
        //先从user_role中通过roleId获取userId的集合，在从user表中查询
        //返回的是用户的简单信息，点击某一个用户查看详细信息
        //因为是管理员也有分类，使用not in
        //简单信息：用户名字，角色
        if (name == null) {
            name = "";
        }
        name = "%"+name+"%";
        List<User> users = userDao.findUsersByRoleLikeName(name,roleId, subjectId);
        return users;
    }

}

package com.exam.service.impl;

import com.exam.dao.RoleDao;
import com.exam.dao.SubjectDao;
import com.exam.dao.UserDao;
import com.exam.entity.Role;
import com.exam.entity.Subject;
import com.exam.entity.User;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    private UserDao userDao;
    @Autowired(required = false)
    private SubjectDao subjectDao;
    @Autowired(required = false)
    private RoleDao roleDao;
    @Override
    public List<User> findAllUserByRole (String roleId, Integer subjectId,String name) {
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

    @Override
    public Map findAllRolesSubjects () {
        List<Role> roles = roleDao.findAllRole();
        List<Subject> subjects = subjectDao.findAllFirst();
        HashMap<String, Object> map = new HashMap<>();
        map.put("roles",roles);
        map.put("subjects",subjects);
        return map;
    }

    /*zxs*/
    @Override
    public User userLogin(User user) {
        return null;
    }

    @Override
    public int userRegister(User user) {
        return 0;
    }
    /*end*/
}

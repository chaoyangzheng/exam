package com.exam.service;

import com.exam.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 管理员查看所有用户，根据角色查看用户实现名称模糊查询
     */
    public List<User> findAllUserByRole(String roleId,Integer subjectId,String name);

    /**
     * 查询所有的学科和
     * @author chaoyang
     * @date 2019/10/15
     * @return java.util.Map
     */
    public Map findAllRolesSubjects();

    /*zxs*/
     public User userLogin(User user);

     public User userRegister(User user);

     public User findUserByName(String username);
    /*end*/
}

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

    /**
     * 查找用户信息
     * @author chaoyang
     * @date 2019/10/16
     * @param  * @param userId
     * @return com.exam.entity.User
     */
    User findUserByUserId (String userId);

    //修改用户权限 学科  增加  删除
    Integer addUserRoleSubject(String userId,String roleId,Integer subjectId);

    void updateUserRoleSubject(String userId,String roleId,Integer subjectId,String oldRoleId);

    User deleteUserRole(String userId,String roleId);

    User deleteUserSubject(String userId,Integer subjectId);




    /*zxs*/
     public User userLogin(User user);

     public int userRegister(User user);
    /*end*/
}

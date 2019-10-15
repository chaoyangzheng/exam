package com.exam.service;

import com.exam.entity.User;

import java.util.List;

public interface UserService {
    /**
     * 管理员查看所有用户，根据角色查看用户实现名称模糊查询
     */
    public List<User> findAllUserByRole(String roleId,String subjectId,String name);
}

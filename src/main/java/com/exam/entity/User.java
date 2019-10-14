package com.exam.entity;

import java.util.List;

/**
 * @author chaoyang
 * @date 2019/10/14
 */
public class User {
    private String userId;
    private String username;
    private String password;
    private List<Role> roles;


    @Override
    public String toString () {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }

    public List<Role> getRoles () {
        return roles;
    }

    public void setRoles (List<Role> roles) {
        this.roles = roles;
    }

    public String getUserId () {
        return userId;
    }

    public void setUserId (String userId) {
        this.userId = userId;
    }

    public String getUsername () {
        return username;
    }

    public void setUsername (String username) {
        this.username = username;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }
}

package com.exam.entity;

/**
 * @author chaoyang
 * @date 2019/10/14
 */
public class User {
    private String userId;
    private String username;
    private String password;
    private Role role;
    private Subject subject;

    @Override
    public String toString () {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", subject=" + subject +
                '}';
    }

    public Subject getSubject () {
        return subject;
    }

    public void setSubject (Subject subject) {
        this.subject = subject;
    }

    public Role getRole () {
        return role;
    }

    public void setRole (Role role) {
        this.role = role;
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

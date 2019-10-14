package com.exam.entity;

import java.util.List;

/**
 * @author chaoyang
 * @date 2019/10/14
 */
public class Role {
    private String roleId;
    private String roleName;
    private String roleDesc;
    private List<Persimion> persimions;

    @Override
    public String toString () {
        return "Role{" +
                "roleId='" + roleId + '\'' +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                ", persimions=" + persimions +
                '}';
    }

    public List<Persimion> getPersimions () {
        return persimions;
    }

    public void setPersimions (List<Persimion> persimions) {
        this.persimions = persimions;
    }

    public String getRoleId () {
        return roleId;
    }

    public void setRoleId (String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName () {
        return roleName;
    }

    public void setRoleName (String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc () {
        return roleDesc;
    }

    public void setRoleDesc (String roleDesc) {
        this.roleDesc = roleDesc;
    }
}

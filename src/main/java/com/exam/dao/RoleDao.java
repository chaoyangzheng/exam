package com.exam.dao;

import com.exam.entity.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author chaoyang
 * @date 2019/10/15
 */
public interface RoleDao {
    /**
     * 查询所有的角色
     * @author chaoyang
     * @date 2019/10/15
     * @return java.util.List<com.exam.entity.Role>
     */
    @Select("select role_id,role_name,role_desc from t_role")
    public List<Role> findAllRole();

    @Insert("insert into t_role (role_id,role_name,role_desc) values(#{roleId},#{roleName},#{roleDesc})")
    public int addRole(Role role);

    @Update("update t_role set role_name = #{roleName},role_desc = #{roleDesc} where role_id = #{roleId}")
    public int updateRole(Role role);

    @Delete("delete from t_role where role_id = #{roleId}")
    public int deleteRole(String roleId);

    //删除这个角色下所有的用户关联
    @Delete("delete from t_user_role,t_user_subject where role_id = #{roleId}")
    public  int deleteRoleUserPerm(String roleId);
}

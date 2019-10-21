package com.exam.dao;

import com.exam.entity.Permission;
import com.exam.entity.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/*zxs*/

public interface PermissionDao {
    //判断当前用户的菜单栏显示什么
    List<Permission> findPermsByIdCard(String idCard);

    List<Permission> findPermsName(List<Integer> ids);




    public List<Role> findAllRolesPerms();

    public List<Permission> findAllPerms();

    @Update("update t_permission set perm_name = #{permName}," +
            "perm_desc = #{permDesc},url = #{url}," +
            "parent_id = #{parentId} where perm_id = #{permId}")
    public int updatePerm(Permission permission);

    @Delete("delete from t_permission where perm_id = #{permId}")
    public int deletePerm(String permId);

    @Delete("delete from t_role_perm where perm_id = #{permId}")
    public int deleteRolePerm(String permId);
    //通过fuid查子菜单

    @Select("select * from t_permission where parent_id = #{parentId}")
    public List<Permission> findPermsByParentId(String parentId);


    //////////////////////////

    public List<Role> findAllRole();

    //角色查询一级菜单
    public List<Permission> findParentPerm(@Param("roleId") String roleId);

    //角色查询二级菜单

    public List<Permission> findChildPerm(@Param("roleId") String roleId,@Param("parentId") String parentId);


}

package com.exam.dao;

import com.exam.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserDao {
    /**
     * 动态sql查看角色的人或者全部人,模糊查询用户
     */
    public List<User> findUsersByRoleLikeName(@Param("name") String name,@Param("roleId") String roleId, @Param("subjectId") Integer subjectId);

    /**
     * 根据用户id查询用户
     *
     * @param userId 用户id
     * @return 用户
     * @author SHIGUANGYI
     * @date 2019/10/15
     */
    User findUserByUserId(String userId);


    /**
     * 修改用户的角色
     * @author chaoyang
     * @date 2019/10/16
     * @param roleId 新的角色id
     * @param userId 用户id
     * @param oldRoleId 旧的角色id
     * @return java.lang.Integer
     */
    @Update("update t_user_role set role_id = #{roleId} where user_id=#{userId} and role_id = #{oldRoleId}")
    Integer updateUserRole(@Param("roleId")String roleId,@Param("userId")String userId,@Param("oldRoleId")String oldRoleId);

    /**
     * 新增用户和角色对应
     * @author chaoyang
     * @date 2019/10/16
     * @param userId 用户id
     * @param roleId 角色id
     * @param userRoleId 主键/uuid
     * @return java.lang.Integer
     */
    @Insert("insert into t_user_role (user_role_id,role_id,user_id) values(#{userRoleId},#{roleId},#{userId})")
    Integer addUserRole(@Param("userId")String userId,@Param("roleId")String roleId,@Param("userRoleId")String userRoleId);

    /**
     * 删除用户的角色
     * @author chaoyang
     * @date 2019/10/16
     * @param userId 用户id
     * @param roleId 角色id
     * @return java.lang.Integer
     */
    @Delete("delete from t_user_role  where user_id=#{userId} and role_id = #{roleId}")
    Integer deleteUserRole(@Param("userId")String userId,@Param("roleId")String roleId);

    /*zxs*/
    User userLogin(User user);

    int userRegister(User user);
    /*end:zxs*/
}

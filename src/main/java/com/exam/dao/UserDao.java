package com.exam.dao;

import com.exam.entity.User;
import org.apache.ibatis.annotations.Param;

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

    /*zxs*/
    User userLogin(User user);

    int userRegister(User user);
    /*end:zxs*/
}

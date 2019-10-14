package com.exam.dao;

import com.exam.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    /**
     * 动态sql查看角色的人或者全部人
     */
    public List<User> findUsersByRole(@Param("roleId") String roleId, @Param("subjectId") String subjectId);

}

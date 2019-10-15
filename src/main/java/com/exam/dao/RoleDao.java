package com.exam.dao;

import com.exam.entity.Role;
import org.apache.ibatis.annotations.Select;

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
}

package com.exam.service.impl;

import com.exam.common.JsonResult;
import com.exam.dao.RoleDao;
import com.exam.dao.SubjectDao;
import com.exam.dao.UserDao;
import com.exam.entity.ExamnieeInfo;
import com.exam.entity.Role;
import com.exam.entity.Subject;
import com.exam.entity.User;
import com.exam.service.ExamnieeInfoService;
import com.exam.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private String flag = "";
    @Autowired(required = false)
    private UserDao userDao;
    @Autowired(required = false)
    private SubjectDao subjectDao;
    @Autowired(required = false)
    private RoleDao roleDao;
    @Autowired
    private ExamnieeInfoService examnieeInfoService;
    @Override
    public List<User> findAllUserByRole (String roleId, Integer subjectId,String name,Integer page,Integer limit) {
        //使用动态sql，判断role的值，为角色，查看某个角色，为null，查看所有
        //先从user_role中通过roleId获取userId的集合，在从user表中查询
        //返回的是用户的简单信息，点击某一个用户查看详细信息
        //因为是管理员也有分类，使用not in
        //简单信息：用户名字，角色
        if (name == null) {
            name = "";
        }
        name = "%"+name+"%";

        //分页工具，传的参数为当前页，每页显示个数
        PageHelper.startPage(page,limit);
        List<User> users = userDao.findUsersByRoleLikeName(name,roleId, subjectId);
        return users;
    }

    @Override
    public Map findAllRolesSubjects () {
        List<Role> roles = roleDao.findAllRole();
        List<Subject> subjects = subjectDao.findAllFirst();
        HashMap<String, Object> map = new HashMap<>();
        map.put("roles",roles);
        map.put("subjects",subjects);
        return map;
    }

    @Override
    public User findUserByUserId (String userId) {
        User user = userDao.findUserByUserId(userId);
        return user;
    }

    @Override
    public Integer addUserRoleSubject (String userId, String roleId, Integer subjectId) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        Integer integer = userDao.addUserRole(userId, roleId, uuid);
        if (subjectId!=null){
            Integer integer1 = subjectDao.addUserUserSubject(userId, subjectId, uuid);
            if (integer==integer1){
                return integer;
            }
            throw new RuntimeException("数据异常");
        }
        return integer;
    }

    @Override
    public void updateUserRoleSubject (String userId, String roleId, Integer subjectId, String oldRoleId) {
        if (roleId!=null&&oldRoleId!=null){
            if (oldRoleId==roleId){
                if ("2".equals(roleId)&&subjectId!=null){
                    //更新学科
                    throw new RuntimeException("请删除用户角色，再添加为教师");
                }else {
                    throw new RuntimeException("更改前后相同");
                }
            }else if ("2".equals(roleId)){
                //更新角色，添加学科
                userDao.updateUserRole(roleId, userId, oldRoleId);
                String uuid = UUID.randomUUID().toString().replace("-", "");
                subjectDao.addUserUserSubject(userId, subjectId, uuid);

            }else{
                //更新角色
                userDao.updateUserRole(roleId, userId, oldRoleId);
                if ("2".equals(oldRoleId)){
                    //删除学科
                    Integer sub = subjectDao.deleteUserSubject(userId);
                }
            }
        }else {
            throw new RuntimeException("请传输正确数据");
        }
    }

    @Override
    public User deleteUserRole (String userId, String roleId) {
        System.out.println(userId+"?"+roleId);
        if ("2".equals(roleId)) {
            //删除科目
            Integer integer = subjectDao.deleteUserSubject(userId);
        }
        Integer integer = userDao.deleteUserRole(userId, roleId);
        return new User();
    }

    @Override
    public User deleteUserSubject (String userId, Integer subjectId) {
        return null;
    }

    /*zxs*/
    @Override
    public User userLogin(User user) {
        User user1 = userDao.userLogin(user);
        System.out.println("service"+user1);
        return user1;
    }

    @Override
    @Transactional
    public User userRegister(User user) {
        JsonResult jsonResult = examnieeInfoService.findExamnieeInfoById(user.getIdCard());
        List<ExamnieeInfo> data = (List<ExamnieeInfo>)jsonResult.getData();
        if (data.isEmpty()){
                String s = UUID.randomUUID().toString().replace("-","");
                user.setUserId(s);
                int i = userDao.userRegister(user);
                if (i != 0) {
                    int i1 = userDao.addUserAndRole(s);
                    if (i1!=0) {
                        return user;
                    }else return null;
                }
        }
        return null;
    }

    @Override
    public User findUserByName(String username) {
        return userDao.findByName(username);
    }
    /*end*/
}

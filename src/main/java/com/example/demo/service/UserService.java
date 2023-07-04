package com.example.db_design_service.service;

import com.example.db_design_service.bean.User;
import com.example.db_design_service.bean.UserLogin;
import com.example.db_design_service.dao.UserDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 对应userdao的service层
 * 调用userdao关于数据库的操作
 */
@Service
public class UserService {
    @Resource
    private UserDao userDao;

    /**
     * 查找所有用户
     * @return
     */
    public List<User> selectAllUser() {
        return userDao.findAllUser();
    }

    /**
     * 查找所有用户的登陆信息
     * @return
     */
    public List<UserLogin> selectAllUserLogin() {
        return userDao.findAllUserLogin();
    }
    
    /**
     * 查询某用户信息(phoneNumber为主键)
     * @param phoneNumber
     * @return
     */
    public User selectUserInfo(String phoneNumber)
    {
        return userDao.findUserInfo(phoneNumber);
    }


     /**
     *
     * 插入用户  注册
     * @param user
     * @return
     */
   public boolean insertUser(User user)
    {
        userDao.insertUser(user);
        return true;
    }


    /**
     * 修改用户个人信息 (其中修改password和isAdmin的操作单独列出 phoneNumber作为主键不轻易修改)
     * @param idCard
     * @param userName
     * @param email
     */
    public void UpdateUserInfe(String idCard, String userName, String email)
    {
        userDao.UptateUser(idCard,userName,email);
    }


    /**
     * 修改用户类型(将用户身份修改为管理员)
     * @param isAdmin
     * @param phoneNumber
     */
    public void UpdateisAdmin(boolean isAdmin,String phoneNumber)
    {
        userDao.UptateisAdmin(isAdmin,phoneNumber);
    }


    /**
     * 修改密码
     * @param password
     * @param phoneNumber
     */
    public void UpdatePassword(String password,String phoneNumber)
    {
        userDao.UptatePassword(password, phoneNumber);
    }


    /**
     * 删除用户信息(phoneNumber为主键)
     * @param phoneNumber
     */
    public void deleteUser(String phoneNumber)
    {
        userDao.deleteUser(phoneNumber);
    }

}
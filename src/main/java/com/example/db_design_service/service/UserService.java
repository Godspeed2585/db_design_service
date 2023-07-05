package com.example.db_design_service.service;

import com.example.db_design_service.bean.UserInfo;
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
     *
     * 查询所有用户信息(仅管理员可用)
     * @return
     */
    public List<UserInfo> selectAllUser() {
        return userDao.findAllUser();
    }


    /**
     *
     * 查找所有用户的用户名与密码(仅管理员可用)
     * @return
     */
    public List<UserLogin> selectAllUserLogin() {
        return userDao.findAllUserLogin();
    }


    /**
     * 查询某用户信息(使用主键phoneNumber)
     * @param phoneNumber
     * @return
     */
    public UserInfo selectUserInfo(String phoneNumber)
    {
        return userDao.findUserInfo(phoneNumber);
    }


     /**
     *
     * 新用户注册(插入新的用户信息)
     * @param user
     * @return
     */
   public boolean insertUser(UserInfo user)
    {
        userDao.insertUser(user);
        return true;
    }


    /**
     *
     * 删除某一用户信息
     * @param phoneNumber
     * @return
     */
    public void deleteUser(String phoneNumber)
    {
        userDao.deleteUser(phoneNumber);
    }


    /**
     *
     * 修改密码
     * @param password
     * @param phoneNumber
     */
    public void UpdatePassword(String password,String phoneNumber)
    {
        userDao.UptatePassword(password, phoneNumber);
    }


    /**
     *
     *
     * 修改用户个人信息
     *
     * @param idCard
     * @param userName
     * @param email
     * @param phoneNumber
     */
    public void UpdateUserInfo(String idCard, String userName, String email, String phoneNumber)
    {
        userDao.UptateUser(idCard, userName, email, phoneNumber);
    }

    
    /**
     *
     *
     * 修改用户身份(仅超管可用)
     *
     * @param isAdmin
     * @param phoneNumber
     */
    public void UpdateisAdmin(int isAdmin, String phoneNumber)
    {
        userDao.UptateisAdmin(isAdmin, phoneNumber);
    }
}
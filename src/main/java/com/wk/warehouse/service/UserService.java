package com.wk.warehouse.service;

import com.wk.warehouse.entity.UserInfo;
import com.wk.warehouse.entity.UserLogin;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 对应userdao的service层
 * 调用userdao关于数据库的操作
 */

public interface UserService {

    /**
     * 查询所有用户信息(仅管理员可用)
     */
    public List<UserInfo> selectAllUser();


    /**
     * 查找所有用户的用户名与密码(仅管理员可用)
     */
    public List<UserLogin> selectAllUserLogin();


    /**
     * 查询某用户信息(使用主键phoneNumber)
     */
    public UserInfo selectUserInfo(String phoneNumber);


     /**
     * 新用户注册(插入新的用户信息)
     */
   public boolean insertUser(UserInfo user);


    /**
     * 删除某一用户信息
     */
    public void deleteUser(String phoneNumber);


    /**
     * 修改密码
     */
    public void UpdatePassword(String password,String phoneNumber);


    /**
     * 修改用户个人信息
     */
    public void UpdateUserInfo(String idCard, String userName, String email, String phoneNumber);

    /**
     * 修改用户email
     */
    public void UpdateUserEmail(String email, String phoneNumber);

    /**
     * 修改用户idCard
     */
    public void UpdateUseridCard(String idCard, String phoneNumber);

    /**
     * 修改用户userName
     */
    public void UpdateUserName(String userName, String phoneNumber);
    
    /**
     * 修改用户身份(仅超管可用)
     */
    public void UpdateisAdmin(int isAdmin, String phoneNumber);
}
package com.wk.warehouse.service.impl;
import com.wk.warehouse.entity.UserInfo;
import com.wk.warehouse.entity.UserLogin;
import com.wk.warehouse.mapper.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
@Service
public class UserServiceImpl {
    
    @Autowired
	private UserMapper userMapper;


    /**
     * 查询所有用户信息(仅管理员可用)
     * @return
     */
    public List<UserInfo> selectAllUser() {
        return userMapper.findAllUser();
    }


    /**
     * 查找所有用户的用户名与密码(仅管理员可用)
     * @return
     */
    public List<UserLogin> selectAllUserLogin() {
        return userMapper.findAllUserLogin();
    }


    /**
     * 查询某用户信息(使用主键phoneNumber)
     * @param phoneNumber
     * @return
     */
    public UserInfo selectUserInfo(String phoneNumber)
    {
        return userMapper.findUserInfo(phoneNumber);
    }


     /**
     * 新用户注册(插入新的用户信息)
     * @param user
     * @return
     */
   public boolean insertUser(UserInfo user)
    {
        userMapper.insertUser(user);
        return true;
    }


    /**
     * 删除某一用户信息
     * @param phoneNumber
     * @return
     */
    public void deleteUser(String phoneNumber)
    {
        userMapper.deleteUser(phoneNumber);
    }


    /**
     * 修改密码
     * @param password
     * @param phoneNumber
     */
    public void UpdatePassword(String password,String phoneNumber)
    {
        userMapper.UptatePassword(password, phoneNumber);
    }


    /**
     * 修改用户个人信息
     * @param idCard
     * @param userName
     * @param email
     * @param phoneNumber
     */
    public void UpdateUserInfo(String idCard, String userName, String email, String phoneNumber)
    {
        userMapper.UptateUser(idCard, userName, email, phoneNumber);
    }

    
    /**
     * 修改用户idCard
     * @param idCard
     * @param phoneNumber
     */
    public void UpdateUseridCard(String idCard, String phoneNumber)
    {
        userMapper.UptateUseridCard(idCard, phoneNumber);
    }


    /**
     * 修改用户email
     * @param email
     * @param phoneNumber
     */
    public void UpdateUserEmail(String email, String phoneNumber)
    {
        userMapper.UptateUserEmail(email, phoneNumber);
    }

    /**
     * 修改用户UserName
     * @param userName
     * @param phoneNumber
     */
    public void UpdateUserName(String userName, String phoneNumber)
    {
        userMapper.UptateUseridCard(userName, phoneNumber);
    }

    /**
     * 修改用户身份(仅超管可用)
     * @param isAdmin
     * @param phoneNumber
     */
    public void UpdateisAdmin(int isAdmin, String phoneNumber)
    {
        userMapper.UptateisAdmin(isAdmin, phoneNumber);
    }
}

package com.wk.warehouse.mapper;

import com.wk.warehouse.entity.UserInfo;
import com.wk.warehouse.entity.UserInfoReturnData;
import com.wk.warehouse.entity.UserReturnList;
import com.wk.warehouse.entity.UserLogin;
import org.apache.ibatis.annotations.*;

import java.util.List;
public interface UserMapper {
    /**
     * 查询所有用户信息(仅管理员可用)
     */
    public List<UserInfo> findAllUser();


    /**
     * 查询用户的登录信息
     * @return
     */
    public List<UserLogin> findAllUserLogin();


    /**
     * 查询某用户的个人信息(phoneNumber为主键)
     * @param phoneNumber
     * @return
     */
    public UserInfo findUserInfo(@Param("phoneNumber")String phoneNumber);
    

    /**
     * 插入新的用户信息
     */
    public void insertUser(@Param("UserInfo")UserInfo user);


    /**
     *修改用户信息(后续有单独修改的部分 phoneNumber作为user表的主键不轻易修改)
     */
    public void UptateUser(@Param("idCard")String idCard, @Param("userName")String userName, @Param("email")String email, @Param("phoneNumber")String phoneNumber);

    /**
     *修改用户信息-姓名
     */
    public void UptateUserName( @Param("userName")String userName, @Param("phoneNumber")String phoneNumber);

    /**
     *修改用户信息-email
     */
    public void UptateUserEmail( @Param("email")String email, @Param("phoneNumber")String phoneNumber);

    /**
     *修改用户信息-idCard
     */
    public void UptateUseridCard( @Param("idCard")String idCard, @Param("phoneNumber")String phoneNumber);

    
    /**
     * 修改用户类型(仅超管可用)
     */
    public void UptateisAdmin(@Param("isAdmin")int isAdmin, @Param("phoneNumber")String phoneNumber);


    /**
     * 修改密码
     */
    public void UptatePassword(@Param("password")String password, @Param("phoneNumber")String phoneNumber);


    /**
     * 删除用户信息(phoneNumber为主键)
     */
    public void deleteUser(@Param("phoneNumber")String phoneNumber);
}

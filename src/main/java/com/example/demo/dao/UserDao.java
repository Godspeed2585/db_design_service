package com.example.db_design_service.dao;

import com.example.db_design_service.bean.User;
import com.example.db_design_service.bean.UserLogin;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * mybatis对应数据库用户表的dao层
 * 作为针对用户表的各种业务(增删改查)
 */
@Mapper
public interface UserDao {

    /**
     * 查询所有用户信息
     */
    @Select("select * from user")
    List<User> findAllUser();


    /**
     * 查询用户的登陆信息
     * @return
     */
    @Select("select phoneNumber,password from user")
    List<UserLogin> findAllUserLogin();


    /**
     * 查询某用户的个人信息(phoneNumber为主键)
     * @param phoneNumber
     * @return
     */
    @Select("select * from user where phoneNumber=#{phoneNumber}")
    User findUserInfo(@Param("phoneNumber") String phoneNumber);
    

    /**
     * 插入新的用户信息
     */
    @Insert("insert into user (isAdmin,idCard,password,userName,email,phoneNumber) values ( #{user.isAdmin}, #{user.idCard}, #{user.password},#{user.userName},#{user.email},#{user.phoneNumber})")
    void insertUser(@Param("user") User user);


    /**
     *修改用户信息(其中修改password和isAdmin的操作单独列出 phoneNumber作为主键不轻易修改)
     * @param idCard
     * @param userName
     * @param email
     * @param phoneNumber
     */
    @Update("update user set userName = #{userName} , email = #{email} , idCard = #{idCard} where phoneNumber = #{phoneNumber}")
    void UptateUser(@Param("idCard") String idCard, @Param("userName") String userName, @Param("email") String email);


    /**
     * 修改用户类型(将用户身份修改为管理员)
     * @param isAdmin
     * @param phoneNumber
     */
    @Update("update user set isAdmin = #{isAdmin} where phoneNumber = #{phoneNumber}")
    void UptateisAdmin(@Param("isAdmin") boolean isAdmin,String phoneNumber);


    /**
     * 修改密码
     * @param password
     * @param phoneNumber
     */
    @Update("update user set password = #{password} where phoneNumber = #{phoneNumber}")
    void UptatePassword(@Param("password") String password, @Param("phoneNumber") String phoneNumber);


    /**
     * 删除用户信息(phoneNumber为主键)
     * @param phoneNumber
     */
    @Delete("delete from user where phoneNumber = #{phoneNumber}")
    void deleteUser(@Param("phoneNumber")String phoneNumber);

}
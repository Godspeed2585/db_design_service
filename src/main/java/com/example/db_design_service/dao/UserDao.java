package com.example.db_design_service.dao;

import com.example.db_design_service.bean.UserInfo;
import com.example.db_design_service.bean.UserInfoReturnData;
import com.example.db_design_service.bean.GetAllUserReturnData;
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
     * 查询所有用户信息(仅管理员可用)
     */
    @Select("select * from user")
    List<UserInfo> findAllUser();


    /**
     * 查询用户的登录信息
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
    UserInfo findUserInfo(@Param("phoneNumber") String phoneNumber);
    

    /**
     * 插入新的用户信息
     */
    @Insert("insert into user (isAdmin,idCard,password,userName,email,phoneNumber) values ( #{user.isAdmin}, #{user.idCard}, #{user.password},#{user.userName},#{user.email},#{user.phoneNumber})")
    void insertUser(@Param("user") UserInfo user);


    /**
     *修改用户信息(其中修改password和isAdmin的操作单独列出 phoneNumber作为user表的主键不轻易修改)
     * @param idCard
     * @param userName
     * @param email
     * @param phoneNumber
     */
    @Update("update user set userName = #{userName} , email = #{email} , idCard = #{idCard} where phoneNumber = #{phoneNumber}")
    void UptateUser(@Param("idCard") String idCard, @Param("userName") String userName, @Param("email") String email, @Param("phoneNumber") String phoneNumber);


    /**
     * 修改用户类型(仅超管可用)
     * @param isAdmin
     * @param phoneNumber
     */
    @Update("update user set isAdmin = #{isAdmin} where phoneNumber = #{phoneNumber}")
    void UptateisAdmin(@Param("isAdmin") int isAdmin, @Param("phoneNumber") String phoneNumber);


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
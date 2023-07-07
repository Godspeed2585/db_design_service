package com.wk.warehouse.mapper;

import com.wk.warehouse.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
public interface UserMapper {

    /**
     * 根据phoneNumber查询用户信息
     */
    public User findByphoneNumber(String phoneNumber);


    /**
     * 查询所有用户信息
     */
    public List<User> selectAll();
    

    /**
     * 加入新用户(用户注册)
     */
    public int insert(User user);


    /**
     * 删除用户信息(phoneNumber为主键)
     */
    public int delete(String phoneNumber);


    /**
     *修改用户信息
     */
    public int UpdateUser(User user);

    
    /**
     * 修改用户类型(仅超管可用)
     */
    public int UpdateisAdmin(User user);

    
    /**
     * 查询用户是否存在
     */
    public int isExist(String phoneNumber);

}

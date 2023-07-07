package com.wk.warehouse.service;

import com.wk.warehouse.entity.User;

import java.util.List;

public interface UserService {
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
     * 删除用户信息
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
     * 查询乘客是否存在
     */
    public int isExist(String idCard);

}
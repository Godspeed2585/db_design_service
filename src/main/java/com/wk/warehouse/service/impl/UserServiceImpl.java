package com.wk.warehouse.service.impl;

import com.wk.warehouse.entity.User;
import com.wk.warehouse.mapper.UserMapper;
import com.wk.warehouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * service实现类
 */
@Service
public class UserServiceImpl implements UserService {

    // 注入Mapper对象
    @Autowired
    private UserMapper UserMapper;

   /**
    * 根据phoneNumber查询用户信息
    */
    @Override
    public User findByphoneNumber(String phoneNumber){
        return UserMapper.findByphoneNumber(phoneNumber);
    }
   

    /**
     * 查询所有用户信息
     */
    public List<User> selectAll(){
        return UserMapper.selectAll();
    }

    /**
    * 加入新用户(用户注册)
    */
    @Override
    public int insert(User user){
        return UserMapper.insert(user);
    }


    /**
    * 删除用户信息
    */
    @Override
    public int delete(String phoneNumber){
        return UserMapper.delete(phoneNumber);
    }


    /**
    *修改用户信息
    */
    @Override
    public int UpdateUser(User user){
        return UserMapper.UpdateUser(user);
    }

   
   /**
    * 修改用户类型(仅超管可用)
    */
    @Override
    public int UpdateisAdmin(User user){
        return UserMapper.UpdateisAdmin(user);
    }

    
    /**
     * 查询乘客是否存在
     */
    @Override
    public int isExist(String idCard){
        return UserMapper.isExist(idCard);
    }
}

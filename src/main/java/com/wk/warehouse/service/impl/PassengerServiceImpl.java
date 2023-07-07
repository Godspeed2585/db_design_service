package com.wk.warehouse.service.impl;

import com.wk.warehouse.entity.Passenger;
import com.wk.warehouse.mapper.PassengerMapper;
import com.wk.warehouse.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * service实现类
 */
@Service
public class PassengerServiceImpl implements PassengerService {

    // 注入Mapper对象
    @Autowired
    private PassengerMapper PassengerMapper;

    /**
     * 查询某用户名下的所有乘客(使用phoneNumber)
     */
    @Override
    public List<Passenger> findByPphoneNumber(String passengerPhoneNumber){
        return PassengerMapper.findByPphoneNumber(passengerPhoneNumber);
    }

    /**
     * 使用idCard查询乘客的信息
     */
    @Override
    public Passenger findByidCard(String idCard){
        return PassengerMapper.findByidCard(idCard);
    }

    /**
     * 查询所有乘客的信息
     */
    public List<Passenger> selectAll(){
        return PassengerMapper.selectAll();
    }

    /**
     * 添加乘客
     */
    @Override
    public int insert(Passenger passenger){
        return PassengerMapper.insert(passenger);
    }

    /**
     *修改乘客信息(其实这个部分没什么用)
     */
    @Override
    public int update(Passenger passenger){
        return PassengerMapper.update(passenger);
    }

    /**
     * 删除乘客信息
     */
    @Override
    public int delete(String idCard){
        return PassengerMapper.delete(idCard);
    }

    /**
     * 查询乘客是否存在
     */
    @Override
    public int isExist(String idCard){
        return PassengerMapper.isExist(idCard);
    }
}

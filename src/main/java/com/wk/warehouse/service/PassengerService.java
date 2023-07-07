package com.wk.warehouse.service;

import com.wk.warehouse.entity.Passenger;

import java.util.List;

public interface PassengerService {
    /**
     * 查询某用户名下的所有乘客(使用phoneNumber)
     */
    public List<Passenger> findByPphoneNumber(String passengerPhoneNumber);

    /**
     * 使用idCard查询乘客的信息
     */
    public Passenger findByidCard(String idCard);

    /**
     * 查询所有乘客的信息
     */
    public List<Passenger> selectAll();

    /**
     * 添加乘客
     */
    public int insert(Passenger passenger);

    /**
     *修改乘客信息
     */
    public int update(Passenger passenger);

    /**
     * 删除乘客信息
     */
    public int delete(String idCard);

    /**
     * 查询乘客是否存在
     */
    public int isExist(String idCard);
}

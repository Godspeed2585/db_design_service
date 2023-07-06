package com.wk.warehouse.service;

import com.wk.warehouse.entity.PassengerReturnList;
import com.wk.warehouse.entity.PassengerInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public interface PassengerService {

    /**
     * 查询所有乘客信息
     */
    public List<PassengerInfo> selectAllPassenger();


    /**
     * 查询某用户下的所有乘客信息(phoneNumber为User表的主键)
     */
    public List<PassengerInfo> selectPassengerInfo(String phoneNumber);


    /**
     * 查询某用户下的所有乘客信息(phoneNumber为User表的主键)
     */
    public List<PassengerInfo> selectPassengerInfoByidCard(String idCard);

    
    /**
     * 插入新的乘客信息
     */
   public boolean insertPassenger(String passengerName, String passengerPhoneNumber, int age, String idCard);

    
     /**
     * 删除某一乘客信息
     */
    public void deletePassenger(String idCard);
}

package com.wk.warehouse.mapper;

import com.wk.warehouse.entity.PassengerInfo;
import com.wk.warehouse.entity.PassengerInfoReturnData;
import com.wk.warehouse.entity.PassengerReturnList;
import org.apache.ibatis.annotations.*;
import java.util.List;
public interface PassengerMapper {
    /**
     * 查询所有乘客的信息(仅管理员/超管可用)
     */
    public List<PassengerInfo> findAllPassenger();


    /**
     * 查询某用户名下的所有乘客的信息(phoneNumber为user表的主键,所以使用passengerPhoneNumber进行查询)
     */
    public List<PassengerInfo> findPassengerInfo(@Param("passengerPhoneNumber") String passengerPhoneNumber);


    /**
     * 使用idCard查询乘客的信息(仅管理员/超管可用)
     */
    public List<PassengerInfo> findPassengerInfoByidCard(@Param("idCard")String idCard);

    /**
     * 添加乘客信息
     */
    public void insertPassenger(@Param("passengerName")String passengerName, @Param("passengerPhoneNumber")String passengerPhoneNumber,  @Param("int")int age,  @Param("idCard")String idCard);


    /**
     * 删除乘客信息(idCard为passenger表的主键)
     */
    public void deletePassenger(@Param("idCard")String idCard);


    /**
     *修改乘客信息(其实没什么用,因为phoneNumber必须对应user里的phoneNumber,idCard不能乱改,passengerName与age一般没有更改的需求)
     */
    public void uptatePassenger(String passengerName, String passengerPhoneNumber, int age, String idCard);
}

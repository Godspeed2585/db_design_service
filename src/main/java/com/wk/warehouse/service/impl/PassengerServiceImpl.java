package com.wk.warehouse.service.impl;
import com.wk.warehouse.entity.PassengerReturnList;
import com.wk.warehouse.mapper.PassengerMapper;
import com.wk.warehouse.mapper.UserMapper;
import com.wk.warehouse.entity.PassengerInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
@Service
public class PassengerServiceImpl {

    @Autowired
	private PassengerMapper passengerMapper;


    /**
     *
     * 查询所有乘客信息
     * @return
     */
    public List<PassengerInfo> selectAllPassenger() {
        return passengerMapper.findAllPassenger();
    }


    /**
     *
     * 查询某用户下的所有乘客信息(phoneNumber为User表的主键)
     * @param phoneNumber
     * @return
     */
    public List<PassengerInfo> selectPassengerInfo(String phoneNumber)
    {
        return passengerMapper.findPassengerInfo(phoneNumber);
    }


    /**
     *
     * 查询某用户下的所有乘客信息(phoneNumber为User表的主键)
     * @param idCard
     * @return
     */
    public List<PassengerInfo> selectPassengerInfoByidCard(String idCard)
    {
        return passengerMapper.findPassengerInfoByidCard(idCard);
    }


    /**
     *
     * 插入新的乘客信息
     * @param passengerName
     * @param passengerPhoneNumber
     * @param age
     * @param idCard
     * @return
     */
   public boolean insertPassenger(String passengerName, String passengerPhoneNumber, int age, String idCard)
    {
        passengerMapper.insertPassenger(passengerName, passengerPhoneNumber, age, idCard);
        return true;
    }

    //passenger表的属性一般不做改动

     /**
     *
     * 删除某一乘客信息
     * @param idCard
     * @return
     */
    public void deletePassenger(String idCard)
    {
        passengerMapper.deletePassenger(idCard);
    }
}

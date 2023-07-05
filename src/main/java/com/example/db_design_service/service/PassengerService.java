package com.example.db_design_service.service;

import com.example.db_design_service.bean.GetAllPassengerReturnData;
import com.example.db_design_service.bean.PassengerInfo;
import com.example.db_design_service.dao.PassengerDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PassengerService {

    @Resource
    private PassengerDao passengerDao;

    /**
     *
     * 查询所有乘客信息
     * @return
     */
    public List<PassengerInfo> selectAllPassenger() {
        return passengerDao.findAllPassenger();
    }


    /**
     *
     * 查询某用户下的所有乘客信息(phoneNumber为User表的主键)
     * @param phoneNumber
     * @return
     */
    public List<PassengerInfo> selectPassengerInfo(String phoneNumber)
    {
        return passengerDao.findPassengerInfo(phoneNumber);
    }


    /**
     *
     * 查询某用户下的所有乘客信息(phoneNumber为User表的主键)
     * @param idCard
     * @return
     */
    public List<PassengerInfo> selectPassengerInfoByidCard(String idCard)
    {
        return passengerDao.findPassengerInfoByidCard(idCard);
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
        passengerDao.insertPassenger(passengerName, passengerPhoneNumber, age, idCard);
        return true;
    }

    
     /**
     *
     * 删除某一乘客信息
     * @param idCard
     * @return
     */
    public void deletePassenger(String idCard)
    {
        passengerDao.deletePassenger(idCard);
    }
}

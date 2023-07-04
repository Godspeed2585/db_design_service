package com.example.db_design_service.service;

import com.example.db_design_service.bean.PassengerInfo;
import com.example.db_design_service.dao.PassengerDao;
import com.example.db_design_service.dao.UserDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PassengerService {

    @Resource
    private PassengerDao passengerDao;

     /**
     * 查询所有乘客的信息
     */
    public List<PassengerInfo> searchAllPassenger()
    {
        return passengerDao.searchAllPassenger();
    }


    /**
     * 查询某乘客的信息(idCard为主键)
     * @param idCard
     * @return
     */
    public List<PassengerInfo>  selectPassenger(String idCard)
    {
        return passengerDao.findPassenger(idCard);
    }


    /**
     * 添加乘客信息
     * @param passengerName
     * @param passengerPhoneNumber
     * @param age
     * @param idCard
     */
    public void insertPassenger(String passengerName, String passengerPhoneNumber, int age, String idCard)
    {
        passengerDao.insertPassenger(passengerName, passengerPhoneNumber, age, idCard);
    }


    /**
     * 删除乘客信息(idCard为主键)
     * @param idCard
     */
    public void deletePassenger(String idCard)
    {
        passengerDao.deletePassenger(idCard);
    }

}

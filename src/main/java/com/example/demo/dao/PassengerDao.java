package com.example.db_design_service.dao;

import com.example.db_design_service.bean.PassengerInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 乘客的dao层
 * 在数据库的passenger上进行操作
 */
@Mapper
public interface PassengerDao {

    /**
     * 查询所有乘客的信息
     */
    @Select("select * from passenger")
    List<PassengerInfo> searchAllPassenger();


    /**
     * 查询某乘客的信息(idCard为主键)
     * @param idCard
     * @return
     */
    @Select("selcet * from passenger where idCard = #{idCard}")
    List<PassengerInfo> findPassenger(@Param("idCard") String idCard);


    /**
     * 添加乘客信息
     * @param passengerName
     * @param passengerPhoneNumber
     * @param age
     * @param idCard
     */
    @Insert("insert into  passenger (passengerName, passengerPhoneNumber, age, idCard) values ( #{passengerName}, #{passengerPhoneNumber}, #{age}, #{idCard})")
    void insertPassenger(@Param("passengerName") String passengerName, @Param("passengerPhoneNumber") String passengerPhoneNumber, 
                         @Param("age") int age, @Param("idCard") String idCard);


    /**
     * 删除乘客信息
     * @param idCard
     */
    @Delete("delete from passenger where idCard = #{ idCard } ")
    void deletePassenger(@Param("idCard") String idCard);

}
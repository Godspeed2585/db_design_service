package com.example.db_design_service.dao;

import com.example.db_design_service.bean.PassengerInfo;
import com.example.db_design_service.bean.PassengerInfoReturnData;
import com.example.db_design_service.bean.GetAllPassengerReturnData;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 乘客的dao层
 * 在数据库的passenger上进行操作
 */
@Mapper
public interface PassengerDao {

    /**
     * 查询所有乘客的信息(仅管理员身份可用)
     */
    @Select("select * from passenger")
    List<PassengerInfo> findAllPassenger();


    /**
     * 查询某用户名下的所有乘客的信息(phoneNumber为user表的主键,所以使用passengerPhoneNumber进行查询)
     * @param passengerPhoneNumber
     * @return
     */
    @Select("selcet * from passenger where passengerPhoneNumber = #{passengerPhoneNumber}")
    List<PassengerInfo> findPassengerInfo(@Param("passengerPhoneNumber") String passengerPhoneNumber);


    /**
     * 使用idCard查询乘客的信息(仅管理员/超管可用)
     * @param idCard
     * @return
     */
    @Select("selcet * from passenger where idCard = #{idCard}")
    List<PassengerInfo> findPassengerInfoByidCard(@Param("idCard") String idCard);

    /**
     * 添加乘客信息
     * @param passengerName
     * @param passengerPhoneNumber
     * @param age
     * @param idCard
     */
    @Insert("insert into passenger (passengerName, passengerPhoneNumber, age, idCard) values ( #{passengerName}, #{passengerPhoneNumber}, #{age}, #{idCard})")
    void insertPassenger(@Param("passengerName") String passengerName, @Param("passengerPhoneNumber") String passengerPhoneNumber, @Param("age") int age, @Param("idCard") String idCard);


    /**
     * 删除乘客信息(idCard为passenger表的主键)
     * @param idCard
     */
    @Delete("delete from passenger where idCard = #{ idCard } ")
    void deletePassenger(@Param("idCard") String idCard);


    /**
     *修改乘客信息
     (其实这部分没什么用,因为passengerPhoneNumber必须对应某个phoneNumber,idCard为主键不能轻易修改,而age与passengerName一般没有修改的需求)
     * @param passengerName
     * @param passengerPhoneNumber
     * @param age
     * @param idCard
     */
    @Update("update passenger set passengerName = #{passengerName} , passengerPhoneNumber = #{passengerPhoneNumber} , age = #{age} where idCard = #{idCard}")
    void uptatePassenger(@Param("passengerName") String passengerName, @Param("passengerPhoneNumber") String passengerPhoneNumber, @Param("age") int age, @Param("idCard") String idCard);

}
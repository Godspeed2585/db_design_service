package com.example.db_design_service.dao;

import com.example.db_design_service.bean.Driver;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.List;

/**
 * mybatis对应数据库司机表的dao层
 * 作为针对用户表的各种业务(增删改查)
 */
@Mapper
public interface DriverDao {

    /**
     * 查询所有司机信息
     */
    @Select("select * from driver")
    List<Driver> findAllDriver();


    /**
     * 查询某司机的信息(jobId为主键)
     * @param jobId
     * @return
     */
    @Select("select * from driver where jobId=#{jobId}")
    Driver findDriverInfo(@Param("jobId") String jobId);
    

    /**
     * 插入司机信息
     * @param dirverName
     * @param entryTime
     * @param gender
     * @param gender
     */
    @Insert("insert into driver (driverName,entryTime,gender,jobId) values ( #{driver.driverName}, #{driver.entryTime}, #{driver.gender}, #{driver.jobId})")
    void insertDriver(@Param("driver") String driverName, @Param("driver") Date entryTime,
                      @Param("driver") String gender, @Param("driver") int jobId);


    /**
     *修改司机信息(jobId作为主键不轻易修改)
     * @param driverName
     * @param entryTime
     * @param gender
     */
    @Update("update driver set driverName = #{driverName} , entryTime = #{entryTime} , gender = #{gender} where jobId = #{jobId}")
    void UptateDriver(@Param("driverName") String driverName, @Param("entryTime") Date entryTime, @Param("gender") String gender);


    /**
     * 删除司机信息(jobId为主键)
     * @param jobId
     */
    @Delete("delete from driver where jobId = #{jobId}")
    void deleteDriver(@Param("jobId")int jobId);

}
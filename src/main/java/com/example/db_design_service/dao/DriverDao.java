package com.example.db_design_service.dao;

import com.example.db_design_service.bean.DriverInfo;
import com.example.db_design_service.bean.DriverInfoReturnData;
import com.example.db_design_service.bean.GetAllDriverReturnData;

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
    List<DriverInfo> findAllDriver();


    /**
     * 用jobId查询某司机的信息
     * @param jobId
     * @return
     */
    @Select("select * from driver where jobId=#{jobId}")
    DriverInfo findDriverInfo(@Param("jobId") int jobId);

    
    /**
     * 用driverName查询某司机的信息
     * @param driverName
     * @return
     */
    @Select("select * from driver where driverName=#{driverName}")
    DriverInfo findDriverInfoByName(@Param("jobId") String driverName);


    /**
     * 插入新的司机信息
     */
    @Insert("insert into driver (driverName, entryTime, gender, jobId) values ( #{driver.driverName}, #{driver.entryTime}, #{driver.gender},#{driver.jobId})")
    void insertDriver(@Param("driverName") String driverName, @Param("entryTime") Date entryTime, @Param("gender") String gender, @Param("jobId") int jobId);


    /**
     *修改司机信息(jobId作为driver表的主键不轻易修改)
     * @param driverName
     * @param entryTime
     * @param gender
     * @param jobId
     */
    @Update("update driver set driverName = #{driverName} , entryTime = #{entryTime} , gender = #{gender} where jobId = #{jobId}")
    void uptateDriver(@Param("driverName") String driverName, @Param("entryTime") Date entryTime, @Param("gender") String gender, @Param("jobId") int jobId);


    /**
     * 删除司机信息(jobId为driver表的主键)
     * @param jobId
     */
    @Delete("delete from driver where jobId = #{jobId}")
    void deleteDriver(@Param("jobId")int jobId);

}
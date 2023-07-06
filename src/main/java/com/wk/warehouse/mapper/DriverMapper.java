package com.wk.warehouse.mapper;
import com.wk.warehouse.entity.DriverInfo;
import com.wk.warehouse.entity.DriverInfoReturnData;
import com.wk.warehouse.entity.DriverReturnList;

import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;
import java.sql.Date;

public interface DriverMapper {
    /**
     * 查询所有司机信息
     */
    public List<DriverInfo> findAllDriver();

    /**
     * 用jobId查询某司机的信息
     */
    public DriverInfo findDriverInfo(@Param("jobId")int jobId);

    /**
     * 用driverName查询某司机的信息
     */
    public DriverInfo findDriverInfoByName(@Param("driverName")String driverName);

    /**
     * 插入新的司机信息
     */
    public void insertDriver(@Param("driverName")String driverName, @Param("entryTime")Date entryTime, @Param("gender")String gender, @Param("jobId")int jobId);

    /**
     *修改司机信息(jobId作为driver表的主键不轻易修改)
     */
    public void uptateDriver(@Param("driverName")String driverName, @Param("entryTime")Date entryTime, @Param("gender")String gender, @Param("jobId")int jobId);

    /**
     *修改司机信息-司机姓名
     */
    public void uptateDriverName(@Param("driverName")String driverName, @Param("jobId")int jobId);

    /**
     *修改司机信息-入职时间
     */
    public void uptateDriverTime(@Param("entryTime")Date entryTime, @Param("jobId")int jobId);

    /**
     *修改司机信息-司机性别
     */
    public void uptateDriverGender(@Param("gender")String gender, @Param("jobId")int jobId);

    /**
     * 使用jobId删除司机信息
     */
    public void deleteDriver(@Param("jobId")int jobId);

}

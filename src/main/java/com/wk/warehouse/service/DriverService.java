package com.wk.warehouse.service;

import com.wk.warehouse.entity.DriverInfo;
import com.wk.warehouse.entity.DriverInfoReturnData;
import com.wk.warehouse.entity.DriverReturnList;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

public interface DriverService {
    
    /**
     * 查询所有司机信息
     */
    public List<DriverInfo> selectAllDriver();


    /**
     * 查询某一司机d信息(jobId为主键)
     */
    public DriverInfo selectDriverInfo(int jobId);


    /**
     * 插入新的司机信息
     */
   public boolean insertDriver(String driverName, Date entryTime, String gender, int jobId);

    
    /**
     * 修改司机相关信息
     */
    public void updateDriver(String driverName, Date entryTime, String gender, int jobId);

    /**
     * 修改司机姓名
     */
    public void updateDriverName(String driverName, int jobId);

    /**
     * 修改司机性别
     */
    public void updateDriverGender(String gender, int jobId);

    /**
     * 修改司机入职时间
     */
    public void updateDriverTime( Date entryTime, int jobId);

    /**
     * 删除某司机的信息
     */
    public void deleteDriver(int jobId);
}

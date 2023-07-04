package com.example.db_design_service.service;
import com.example.db_design_service.bean.Driver;
import com.example.db_design_service.bean.User;
import com.example.db_design_service.bean.UserLogin;
import com.example.db_design_service.dao.DriverDao;
import com.example.db_design_service.dao.UserDao;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.sql.Date;
import java.util.List;

/**
 * 对应driverdao的service层
 * 调用driverdao关于数据库的操作
 */
@Service
public class DriverService {
    @Resource
    private DriverDao driverDao;

    /**
     * 查询所有司机的信息
     * @return
     */
    public List<Driver> selectAllDriver() {
        return driverDao.findAllDriver();
    }


    /**
     * 查询某司机的信息(jobId为主键)
     * @param jobId
     * @return
     */
    public Driver selectDriverInfo(String jobId)
    {
        return driverDao.findDriverInfo(jobId);
    }


    /**
     * 插入司机信息
     * @param driverName
     * @param entryTime
     * @param gender
     * @param jobId
     * @return
     */
   public boolean insertDriver(String driverName, Date entryTime, String gender, int jobId)
    {
        driverDao.insertDriver(driverName, entryTime, gender, jobId);
        return true;
    }


    /**
     * 修改司机信息 (jobId作为主键不轻易修改)
     * @param driverName
     * @param entryTime
     * @param gender
     */
    public void UpdateDriverInfe(String driverName, Date entryTime, String gender)
    {
        driverDao.UptateDriver(driverName,entryTime,gender);
    }

    
    /**
     * 删除司机信息(jobId为主键)
     * @param jobId
     */
    public void deleteDriver(int jobId)
    {
        driverDao.deleteDriver(jobId);
    }
}

package com.example.db_design_service.service;

import com.example.db_design_service.bean.DriverInfo;
import com.example.db_design_service.bean.DriverInfoReturnData;
import com.example.db_design_service.bean.GetAllDriverReturnData;
import com.example.db_design_service.dao.DriverDao;

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
     *
     * 查询所有司机信息
     * @return
     */
    public List<DriverInfo> selectAllDriver() {
        return driverDao.findAllDriver();
    }


    /**
     *
     * 查询某一司机d信息(jobId为主键)
     * @param jobId
     * @return
     */
    public DriverInfo selectDriverInfo(int jobId)
    {
        return driverDao.findDriverInfo(jobId);
    }


    /**
     *
     * 插入新的司机信息
     * @param driverName
     * @param entryTime
     * @param gender
     * @param jobId
     * @return
     */
   public boolean insertDriver(String driverName, Date entryTime, String gender, int jobId)
    {
        driverDao.insertDriver(driverName, entryTime, gender, jobId);//注意dao和service层各自的insertDriver函数类型不同
        return true;
    }

    
    /**
     *
     *
     * 修改司机相关信息
     *
     * @param driverName
     * @param entryTime
     * @param gender
     * @param jobId
     */
    public void updateDriver(String driverName, Date entryTime, String gender, int jobId)
    {
        driverDao.uptateDriver(driverName, entryTime, gender, jobId);
    }


    /**
     *
     * 删除某司机的信息
     * @param jobId
     * @return
     */
    public void deleteDriver(int jobId)
    {
        driverDao.deleteDriver(jobId);
    }
}

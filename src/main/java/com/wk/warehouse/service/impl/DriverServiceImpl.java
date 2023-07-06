package com.wk.warehouse.service.impl;
import com.wk.warehouse.entity.DriverInfo;
import com.wk.warehouse.entity.DriverInfoReturnData;
import com.wk.warehouse.entity.DriverReturnList;
import com.wk.warehouse.mapper.DriverMapper;
import com.wk.warehouse.mapper.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;
@Service
public class DriverServiceImpl {

    @Autowired
	private DriverMapper driverMapper;


    /**
     *
     * 查询所有司机信息
     * @return
     */
    public List<DriverInfo> selectAllDriver() {
        return driverMapper.findAllDriver();
    }


    /**
     *
     * 查询某一司机d信息(jobId为主键)
     * @param jobId
     * @return
     */
    public DriverInfo selectDriverInfo(int jobId)
    {
        return driverMapper.findDriverInfo(jobId);
    }


    /**
     * 插入新的司机信息
     * @param driverName
     * @param entryTime
     * @param gender
     * @param jobId
     * @return
     */
   public boolean insertDriver(String driverName, Date entryTime, String gender, int jobId)
    {
        driverMapper.insertDriver(driverName, entryTime, gender, jobId);//mapper和service层各自的insertDriver函数类型不同
        return true;
    }

    
    /**
     * 修改司机相关信息
     * @param driverName
     * @param entryTime
     * @param gender
     * @param jobId
     */
    public void updateDriver(String driverName, Date entryTime, String gender, int jobId)
    {
        driverMapper.uptateDriver(driverName, entryTime, gender, jobId);
    }


    /**
     * 修改司机姓名
     * @param driverName
     * @param jobId
     */
    public void updateDriverName(String driverName, int jobId)
    {
        driverMapper.uptateDriverName(driverName, jobId);
    }

    
    /**
     * 修改司机性别
     * @param gender
     * @param jobId
     */
    public void updateDriverGender(String gender, int jobId)
    {
        driverMapper.uptateDriverGender(gender, jobId);
    }


    /**
     * 修改司机入职时间
     * @param entryTime
     * @param jobId
     */
    public void updateDriverTime(Date entryTime, int jobId)
    {
        driverMapper.uptateDriverTime(entryTime, jobId);
    }


    /**
     *
     * 删除某司机的信息
     * @param jobId
     * @return
     */
    public void deleteDriver(int jobId)
    {
        driverMapper.deleteDriver(jobId);
    }
}

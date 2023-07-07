package com.wk.warehouse.service.impl;

import com.wk.warehouse.entity.Driver;
import com.wk.warehouse.mapper.DriverMapper;
import com.wk.warehouse.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * service实现类
 */
@Service
public class DriverServiceImpl  implements DriverService {

    // 注入Mapper对象
    @Autowired
    private DriverMapper DriverMapper;

    /**
     * 用jobId查询司机的信息
     */
    @Override
    public Driver findByjobId(int jobId){
        return DriverMapper.findByjobId(jobId);
    }

    /**
     * 查询所有司机的信息
     */
    public List<Driver> selectAll()
    {
        return DriverMapper.selectAll();
    }

    /**
     * 加入新司机
     */
    @Override
    public int insert(Driver driver){
        return DriverMapper.insert(driver);
    }

    /**
     *修改司机信息
     */
    public int update(Driver driver){
        return DriverMapper.update(driver);
    }
    
    /**
     * 删除司机
     */
    public int delete(int jobId){
        return DriverMapper.delete(jobId);
    }

    /**
     * 判断该司机是否已存在
     */
    public int isExist(int jobId){
        return DriverMapper.isExist(jobId);
    }
}

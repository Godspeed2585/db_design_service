package com.wk.warehouse.service;

import com.wk.warehouse.entity.Driver;

import java.util.List;

public interface DriverService {

    /**
     * 用jobId查询司机的信息
     */
    public Driver findByjobId(int jobId);

    /**
     * 查询所有司机的信息
     */
    public List<Driver> selectAll();

    /**
     * 加入新司机
     */
    public int insert(Driver driver);

    /**
     *修改司机信息
     */
    public int update(Driver driver);
    
    /**
     * 删除司机
     */
    public int delete(int jobId);

    /**
     * 判断该司机是否已存在
     */
    public int isExist(int jobId);
}

package com.wk.warehouse.service.impl;

import com.wk.warehouse.entity.Bus;
import com.wk.warehouse.mapper.BusMapper;
import com.wk.warehouse.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * service实现类
 */
@Service
public class BusServiceImpl implements BusService {

    // 注入Mapper对象
    @Autowired
    private BusMapper BusMapper;

    @Override
    public List<Bus> findBylicense(String license) {
        return BusMapper.findBylicense(license);
    }

    @Override
    public int insert(Bus bus) {
        return BusMapper.insert(bus);
    }

    @Override
    public int update(Bus bus) {
        return BusMapper.update(bus);
    }

    @Override
    public int delete(String license) {
        return BusMapper.delete(license);
    }

    public int isExist(String license) {
        return BusMapper.isExist(license);
    }
}

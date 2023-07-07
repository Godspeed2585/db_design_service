package com.wk.warehouse.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wk.warehouse.entity.IfStations;
import com.wk.warehouse.mapper.IfStationsMapper;
import com.wk.warehouse.service.IfStationsService;

@Service
public class IfStationsServiceImpl implements IfStationsService{
    @Autowired
    private IfStationsMapper ifStationsMapper;

    @Override
    public IfStations findByRouteId(String routeId) {
        return ifStationsMapper.findByRouteId(routeId);
    }

    @Override
    public int insert(IfStations ifStations) {
        return ifStationsMapper.insert(ifStations);
    }

    @Override
    public int update(IfStations ifStations) {
        return ifStationsMapper.update(ifStations);
    }

    @Override
    public int delete(String routeId) {
        return ifStationsMapper.delete(routeId);
    }
}

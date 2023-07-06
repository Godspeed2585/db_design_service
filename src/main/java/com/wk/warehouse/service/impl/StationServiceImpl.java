package com.wk.warehouse.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wk.warehouse.entity.Station;
import com.wk.warehouse.mapper.StationMapper;
import com.wk.warehouse.service.StationService;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationMapper stationMapper;

    @Override
    public List<Station> findALLStation() {
        return stationMapper.selectAll();
    }

    @Override
    public boolean isExist(String stationName) {
        Station a=stationMapper.selectByName(stationName);
        if(null == a)return false;
        else return true;
    }

    @Override
    public int addStation(Station station) {
        stationMapper.insert(station);
        return 0;
    }

    @Override
    public int deleteStation(String stationName) {
        stationMapper.delete(stationName);
        return 0;
    }
}
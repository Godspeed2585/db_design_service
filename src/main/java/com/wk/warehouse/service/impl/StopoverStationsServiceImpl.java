package com.wk.warehouse.service.impl;

import com.wk.warehouse.mapper.StopoverStationsMapper;
import com.wk.warehouse.service.StopoverStationsService;
import com.wk.warehouse.entity.StopoverStations;
import com.wk.warehouse.mapper.StopoverStationsMapper;
import com.wk.warehouse.service.StopoverStationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StopoverStationsServiceImpl implements StopoverStationsService {

    @Autowired
    private StopoverStationsMapper stopoverStationsMapper;

    @Override
    public int insert(StopoverStations stopoverStations) {
        return stopoverStationsMapper.insert(stopoverStations);
    }

    @Override
    public int update(StopoverStations stopoverStations) {
        return stopoverStationsMapper.update(stopoverStations);
    }

    @Override
    public int delete(int id, String nb) {
        return stopoverStationsMapper.delete(id, nb);
    }

    @Override
    public List< StopoverStations> findByrouteId(int routeId) {
        return stopoverStationsMapper.findByrouteId(routeId);
    }

    @Override
    public List<StopoverStations> findAll() {
        return stopoverStationsMapper.findAll();
    }
}

package com.wk.warehouse.service.impl;

import java.util.List;

import com.wk.warehouse.entity.IfStations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wk.warehouse.entity.StopoverStations;
import com.wk.warehouse.mapper.StopoverStationsMapper;
import com.wk.warehouse.mapper.IfStationsMapper;
import com.wk.warehouse.service.RouteService;


@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private StopoverStationsMapper stopoverStationsMapper;
    @Autowired
    private IfStationsMapper ifStationsMapper;

    @Override
    public List<StopoverStations> getRoute(String begin, String end, int routeId) {
        int beginIndex=0,endIndex=0;
        List<StopoverStations> stopoverStationsList=stopoverStationsMapper.findByrouteId(routeId);
        for (StopoverStations stopoverStations : stopoverStationsList) {
            if(stopoverStations.getStationName() == begin)  beginIndex=stopoverStationsList.indexOf(stopoverStations);
            if(stopoverStations.getStationName() == end)  endIndex=stopoverStationsList.indexOf(stopoverStations);
        }
        return stopoverStationsList.subList(beginIndex,endIndex);
    }

    @Override
    public int deleteRoute(int routeId) {
        stopoverStationsMapper.deleteByRouteId(routeId);
        ifStationsMapper.delete(routeId);
        return 1;
    }

    @Override
    public int updateRoute(List<StopoverStations> stopoverStationsList, IfStations ifstations) {
        int routeId=ifstations.getRouteId();
        this.deleteRoute(routeId);
        this.addRoute(stopoverStationsList, ifstations);
        return 1;
    }

    @Override
    public boolean isExist(int routeId) {
        IfStations ifStations=ifStationsMapper.findByRouteId(routeId);
        if (null == ifStations ) return false;
        else return true;
    }

    @Override
    public int addRoute(List<StopoverStations> stopoverStationsList, IfStations ifstations) {
        for (StopoverStations stopoverStations : stopoverStationsList) {
            stopoverStationsMapper.insert(stopoverStations);
        }
        ifStationsMapper.insert(ifstations);
        return 1;
    }
}
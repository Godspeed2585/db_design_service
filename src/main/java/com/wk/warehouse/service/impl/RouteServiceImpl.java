package com.wk.warehouse.service.impl;

import java.util.List;

import com.wk.warehouse.entity.IfStations;
import com.wk.warehouse.entity.StopoverStations;

public class RouteServiceImpl {
    // Create or Update IfStations
    public void saveIfStations(IfStations ifStations) {
        if (ifStations.getRouteId() != 0 && ifStations.getTotalLength() > 0 &&
            ifStations.getInitialStation() != null && ifStations.getFinalStation() != null) {
            ifStationsMapper.save(ifStations);
        } else {
            throw new IllegalArgumentException("Invalid IfStations data provided.");
        }
    }

    // Delete IfStations by routeId
    public void deleteIfStations(int routeId) {
        ifStationsMapper.delete(routeId);
    }

    // Get IfStations by routeId
    public IfStations getIfStations(int routeId) {
        return ifStationsMapper.get(routeId);
    }

    // Get all IfStations
    public List<IfStations> getAllIfStations() {
        return ifStationsMapper.getAll();
    }
}

public class StopoverStationsService {
    private StopoverStationsMapper stopoverStationsMapper;

    public StopoverStationsService(StopoverStationsMapper stopoverStationsMapper) {
        this.stopoverStationsMapper = stopoverStationsMapper;
    }

    // Create or Update StopoverStations
    public void saveStopoverStations(StopoverStations stopoverStations) {
        if (stopoverStations.getRouteId() != 0 && stopoverStations.getLength() > 0 &&
            stopoverStations.getStationName() != null &&
            stopoverStations.getArriveTime() != null && stopoverStations.getDepartureTime() != null) {
            stopoverStationsMapper.save(stopoverStations);
        } else {
            throw new IllegalArgumentException("Invalid StopoverStations data provided.");
        }
    }

    // Delete StopoverStations by routeId
    public void deleteStopoverStations(int routeId) {
        stopoverStationsMapper.delete(routeId);
    }

    // Get StopoverStations by routeId
    public StopoverStations getStopoverStations(int routeId) {
        return stopoverStationsMapper.get(routeId);
    }

    // Get all StopoverStations
    public List<StopoverStations> getAllStopoverStations() {
        return stopoverStationsMapper.getAll();
    }
}

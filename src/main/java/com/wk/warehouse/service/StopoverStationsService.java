package com.wk.warehouse.service;

import java.util.List;

import com.wk.warehouse.entity.StopoverStations;

public interface StopoverStationsService {
    public int insert(StopoverStations stopoverStations);

    public int update(StopoverStations stopoverStations);

    public int delete(int id, String nb);

    public List<StopoverStations> findByrouteId(int routeId);

    public List<StopoverStations> findAll();
}

package com.wk.warehouse.service;

import java.util.List;

import com.wk.warehouse.entity.StopoverStations;

public interface StopoverStationsService {
    void insert(StopoverStations stopoverStations);

    void update(StopoverStations stopoverStations);

    void delete(int id, String nb);

    StopoverStations findByrouteId(int routeId);

    List<StopoverStations> findAll();
}

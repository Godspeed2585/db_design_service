package com.wk.warehouse.service;

import java.util.List;

import com.wk.warehouse.entity.IfStations;

public interface IfStationsService {
    List<IfStations> findByRouteId(int routeId);

    void insert(IfStations ifStations);

    void update(IfStations ifStations);

    void delete(int routeId);
}

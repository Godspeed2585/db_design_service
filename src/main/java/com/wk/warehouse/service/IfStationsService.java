package com.wk.warehouse.service;

import java.util.List;

import com.wk.warehouse.entity.IfStations;

public interface IfStationsService {
    IfStations findByRouteId(String routeId);

    int insert(IfStations ifStations);

    int update(IfStations ifStations);

    int delete(String routeId);
}

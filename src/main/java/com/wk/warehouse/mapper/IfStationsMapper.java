package com.wk.warehouse.mapper;

import com.wk.warehouse.entity.IfStations;

public interface IfStationsMapper {
    // 根据routeId查询车站信息
    IfStations findByRouteId(String routeId);

    // // 根据initialStation和finalStation查询车站信息
    // List<IfStations> findByStations(@Param("initialStation") String initialStation, @Param("finalStation") String finalStation);

    // 新增车站信息
    int insert(IfStations ifStations);

    // 更新车站信息
    int update(IfStations ifStations);

    // 删除车站信息
    int delete(String routeId);
}

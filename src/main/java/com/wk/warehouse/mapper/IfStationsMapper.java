package com.wk.warehouse.mapper;

import com.wk.warehouse.entity.IfStations;
import java.util.List;

public interface IfStationsMapper {
    // 根据routeId查询车站信息
    List<IfStations> findByRouteId(int routeId);

    // // 根据initialStation和finalStation查询车站信息
    // List<IfStations> findByStations(@Param("initialStation") String initialStation, @Param("finalStation") String finalStation);

    // 新增车站信息
    void insert(IfStations ifStations);

    // 更新车站信息
    void update(IfStations ifStations);

    // 删除车站信息
    void delete(int routeId);
}

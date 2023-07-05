package com.wk.warehouse.mapper;

import com.wk.warehouse.entity.StopoverStations;
import java.util.List;

import org.apache.ibatis.annotations.Param;
public interface StopoverStationsMapper {
    void insert(StopoverStations stopoverStations);

    void update(StopoverStations stopoverStations);

    void delete(@Param("userId") int id, @Param("number") String nb);

    StopoverStations findByrouteId(int routeId);

    List<StopoverStations> findAll();
}

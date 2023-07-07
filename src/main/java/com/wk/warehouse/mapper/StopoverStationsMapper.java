package com.wk.warehouse.mapper;

import com.wk.warehouse.entity.StopoverStations;
import java.util.List;

import org.apache.ibatis.annotations.Param;
public interface StopoverStationsMapper {
    int insert(StopoverStations stopoverStations);

    int update(StopoverStations stopoverStations);

    int delete(@Param("routeId") String id, @Param("number") String nb);

    List<StopoverStations> findByrouteId(String routeId);

    List<StopoverStations> findAll();

    int deleteByRouteId(@Param("routeId") String id);

    List<String> getRouteIdByBE(@Param("begin") String begin, @Param("end") String end);
}

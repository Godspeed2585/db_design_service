package com.wk.warehouse.mapper;

import com.wk.warehouse.entity.StopoverStations;
import java.util.List;

import org.apache.ibatis.annotations.Param;
public interface StopoverStationsMapper {
    int insert(StopoverStations stopoverStations);

    int update(StopoverStations stopoverStations);

    int delete(@Param("userId") int id, @Param("number") String nb);

    List<StopoverStations> findByrouteId(int routeId);

    List<StopoverStations> findAll();

    int deleteByRouteId(@Param("routeId") int id);

    List<Integer> getRouteIdByBE(@Param("begin") String begin, @Param("end") String end);
}

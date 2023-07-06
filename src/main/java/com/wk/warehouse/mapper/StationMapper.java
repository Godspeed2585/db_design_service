package com.wk.warehouse.mapper;

import java.util.List;

import com.wk.warehouse.entity.Station;

public interface StationMapper {

    int insert(Station station);

    int delete(String stationName);

    int update(Station station);

    Station selectByName(String stationName);

    List<Station> selectAll();


}

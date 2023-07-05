package com.wk.warehouse.mapper;

import java.util.List;

import com.wk.warehouse.entity.Station;

public interface StationMapper {
    // 查询车站信息
    List<Station> findALL();
    // 新增车站信息
    void insertStation(Station station);

    // 删除车站信息
    void deleteStation(Station station);
}

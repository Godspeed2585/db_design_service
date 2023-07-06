package com.wk.warehouse.service;

import java.util.List;

import com.wk.warehouse.entity.Station;
public interface StationService {
    /** 查询所有站点 */
    public List<Station> findALLStation();
    /** 判断站点是否存在*/
    public boolean isExist(String stationName);
    /** 添加站点*/
    public int addStation(Station station);
    /** 删除站点*/
    public int deleteStation(String stationName);
}

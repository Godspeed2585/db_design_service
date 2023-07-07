package com.wk.warehouse.service;

import java.util.List;


import com.wk.warehouse.entity.IfStations;
import com.wk.warehouse.entity.StopoverStations;

/**
 * RouteService接口
 */
public interface RouteService {

    /** 根据routeId和出发站begin和目的站查找路线*/
    public List<StopoverStations> getRoute(String begin,String end,String routeId);

    /** 删除路线 */
    public int deleteRoute(String routeId);

    /** 更新路线,即改途径，也改首发站*/
    public int updateRoute(List<StopoverStations> stopoverStationsList,IfStations ifstations);

    /**判断路线是否存在 */
    public boolean isExist(String routeID);
    /** 添加一条新路线 */
    public int addRoute(List<StopoverStations> stopoverStationsList,IfStations ifstations);
}
package com.wk.warehouse.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.wk.warehouse.entity.StopoverStations;
import com.wk.warehouse.entity.Class;

public interface ClassAndRouteService {
    /** 根据给出的首末站查询班次 */
    public List<Class> getClassByBE(String begin,String end,LocalDate date);


    /** 根据给出的首末站查询符合的车辆以及车辆对应的余票量和票价 */
    public List<Map<String, Object>> getClassAndRemainByBE(String begin,String end,LocalDate date);

    /** 根据给出的车辆编号查询路线 */
    public List<StopoverStations> getRouteBywagonId(String wagonId);
}

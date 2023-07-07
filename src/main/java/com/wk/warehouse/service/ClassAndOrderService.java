package com.wk.warehouse.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.wk.warehouse.entity.StopoverStations;
import com.wk.warehouse.entity.Class;

public interface ClassAndOrderService {
    Map<String,Integer> getRemainByBeginEndClass(String begin,String end,Class classobj);
}
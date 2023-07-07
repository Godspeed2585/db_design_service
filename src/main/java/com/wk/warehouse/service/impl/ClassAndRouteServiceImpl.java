package com.wk.warehouse.service.impl;

import com.wk.warehouse.entity.Class;
import com.wk.warehouse.entity.StopoverStations;
import com.wk.warehouse.mapper.ClassMapper;
import com.wk.warehouse.mapper.StopoverStationsMapper;
import com.wk.warehouse.service.ClassAndRouteService;
import com.wk.warehouse.service.ClassAndOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class ClassAndRouteServiceImpl implements ClassAndRouteService {

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private StopoverStationsMapper stopoverStationsMapper;

    @Autowired
    private ClassAndOrderService classAndOrderService;

    @Override
    public List<Class> getClassByBE(String begin, String end, LocalDate date) {
        List<String> routeIdList = stopoverStationsMapper.getRouteIdByBE(begin,end);
        List<Class> classList =new ArrayList<>();
        for (String routeId : routeIdList) {
            //得到要求的发车时间
            StopoverStations stopoverStations=stopoverStationsMapper.findByrouteId_Stations(routeId,begin);
            LocalTime ctime=stopoverStations.getDepartureTime();
            LocalDateTime dateTime=date.atStartOfDay();
            dateTime=dateTime.minusHours(ctime.getHour());
            dateTime=dateTime.minusMinutes(ctime.getMinute());
            dateTime=dateTime.minusSeconds(ctime.getSecond());
            classList.addAll(classMapper.getClassByRouteIdDate(routeId,dateTime,dateTime.plusDays(1)));
        }
        return classList;
    }

    @Override
    public List<Map<String, Object>> getClassAndRemainByBE(String begin, String end, LocalDate date) {
        List<Class> classList = this.getClassByBE(begin, end, date);
        List<Map<String, Object>> list1=new ArrayList<>();
        Map<String, Object> map1= new HashMap<>();
        map1.put("Class",null);
        map1.put("FirstRemain",null);
        map1.put("SecondRemain",null);
        for (Class class1 : classList) {
            Map<String,Integer> map2=classAndOrderService.getRemainByBeginEndClass(begin, end, class1);
            Integer remain1=map2.get("firstRemain");
            Integer remain2=map2.get("secondRemain");
            map1.put("Class",class1);
            map1.put("FirstRemain",remain1);
            map1.put("SecondRemain",remain2);
            list1.add(map1);
        }
        return list1;
    }

    @Override
    public List<StopoverStations> getRouteBywagonId(String wagonId) {
        Class classObj=classMapper.findByWagonId(wagonId);
        return stopoverStationsMapper.findByrouteId(classObj.getRouteId());
    }
}

package com.wk.warehouse.service.impl;

import com.wk.warehouse.entity.Class;
import com.wk.warehouse.entity.StopoverStations;
import com.wk.warehouse.mapper.ClassMapper;
import com.wk.warehouse.mapper.StopoverStationsMapper;
import com.wk.warehouse.service.ClassAndRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

@Service
public class ClassAndRouteServiceImpl implements ClassAndRouteService {

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private StopoverStationsMapper stopoverStationsMapper;

    @Override
    public List<Class> getClassByBE(String begin, String end, LocalDate date) {
        List<String> routeIdList = stopoverStationsMapper.getRouteIdByBE(begin,end);
        List<Class> classList =new ArrayList<>();
        for (String routeId : routeIdList) {
            classList.addAll(classMapper.getClassByRouteIdDate(routeId,date.atStartOfDay(),date.atStartOfDay().plusDays(1)));
        }
        return classList;
    }

    // @Override
    // public List<Map<String, Object>> getClassAndRemainByBE(String begin, String end, LocalDate date) {
    //     List<Class> classList = this.getClassByBE(begin, end, date);
    //     List<Map<String, Object>> list1=new ArrayList<>();
    //     Map<String, Object> map1= new HashMap<>();
    //     map1.put("Class",null);
    //     map1.put("FiretRemain",null);
    //     map1.put("SecondRemain",null);
    //     for (Class class1 : classList) {
    //         Integer remain1=ClassAndOrderService.getFirstRemainByBeginEndClass(begin,end,class1);
    //         Integer remain2=ClassAndOrderService.getSecondRemainByBeginEndClass(begin,end,class1);
    //         map1.put("Class",class1);
    //         map1.put("FiretRemain",remain1);
    //         map1.put("SecondRemain",remain2);
    //         list1.add(map1);
    //     }
    // }

    @Override
    public List<StopoverStations> getRouteBywagonId(String wagonId) {
        Class classObj=classMapper.findByWagonId(wagonId);
        return stopoverStationsMapper.findByrouteId(classObj.getRouteId());
    }
}

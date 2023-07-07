package com.wk.warehouse.service.impl;

import com.wk.warehouse.entity.Class;
import com.wk.warehouse.entity.Order;
import com.wk.warehouse.entity.StopoverStations;
import com.wk.warehouse.entity.Bus;
import com.wk.warehouse.mapper.ClassMapper;
import com.wk.warehouse.mapper.OrderMapper;
import com.wk.warehouse.mapper.BusMapper;
import com.wk.warehouse.mapper.StopoverStationsMapper;
import com.wk.warehouse.service.ClassAndOrderService;
import com.wk.warehouse.service.ClassAndRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.time.LocalDate;

@Service
public class ClassAndOrderServiceImpl implements ClassAndOrderService {

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private BusMapper BusMapper;

    @Autowired
    private StopoverStationsMapper stopoverStationsMapper;

    @Override
    public Map<String,Integer> getRemainByBeginEndClass(String begin,String end,Class classobj){
        Map<String,Integer> remainMap=new HashMap<>();
        Map<String,Integer> stationMap=new HashMap<>();
        List<Bus> busList=BusMapper.findBylicense(classobj.getLicense());
        int firstRemain=0;
        int secondRemain=0;
        for (Bus bus : busList) {
            firstRemain=bus.getTotalFirst();
            secondRemain=bus.getTotalSecond();
        }

        //1.根据wagon查询出来路线号
        String routeId = classobj.getRouteId();
        //2.再根据路线号查询出路线，将路线变成hashmap
        List<StopoverStations> stopoverStationsList=stopoverStationsMapper.findByrouteId(routeId);
        for (StopoverStations stopoverStations : stopoverStationsList) {
            stationMap.put(stopoverStations.getStationName(), stopoverStations.getnumber());
        }
        //3.根据map得到number,遍历订单，判断是否需要减少
        List<Order> orderList=orderMapper.findBywagonId(classobj.getWagonId());
        for (Order order : orderList) {
            if(stationMap.get(order.getStartStation())<=stationMap.get(begin)&&stationMap.get(order.getDestination())>=stationMap.get(begin)){
                if(order.getTicketType()==1) firstRemain--;
                else if(order.getTicketType()==2) secondRemain--;
                else;
            }
            else if(stationMap.get(order.getStartStation())<=stationMap.get(end)&&stationMap.get(order.getDestination())>=stationMap.get(end)){
                if(order.getTicketType()==1) firstRemain--;
                else if(order.getTicketType()==2) secondRemain--;
                else;
            }
            else if(stationMap.get(order.getStartStation())>stationMap.get(begin)&&stationMap.get(order.getDestination())>stationMap.get(end)){
                if(order.getTicketType()==1) firstRemain--;
                else if(order.getTicketType()==2) secondRemain--;
                else;
            }
            else ;
        }
        remainMap.put("firstRemain",firstRemain);
        remainMap.put("secondRemain", secondRemain);
        return remainMap;
    }

}

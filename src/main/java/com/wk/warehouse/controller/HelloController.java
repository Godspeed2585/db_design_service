package com.wk.warehouse.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// 表示该类是一个控制器，可以接收前端的请求并响应
// 响应的是json数据

import org.springframework.beans.factory.annotation.Autowired;
import com.wk.warehouse.mapper.BusMapper;
import com.wk.warehouse.entity.Bus;
import com.wk.warehouse.mapper.OrderMapper;
import com.wk.warehouse.entity.Order;


@RestController // 会自动生成一个类型首字母小写的对象
public class HelloController {
    @Autowired
//    private StationMapper stationMapper;
    private BusMapper busMapper;
    @Autowired
    private OrderMapper orderMapper;
//    @RequestMapping("/hello")
//    public Station sayHello(){
//        // List<Station> a=stationMapper.findALLStation("西安站");
//        Station b= stationMapper.findStation("西安站");
//        return b;
//    }

    @RequestMapping("/helloBus")
    public List<Bus> sayHelloBus(){
        // List<Station> a=stationMapper.findALLStation("西安站");
        List<Bus> b= busMapper.findBylicense("陕S0978");
        return b;
    }

    @RequestMapping("/helloOrder")
    public List<Order> sayHelloOrder(){
        // List<Station> a=stationMapper.findALLStation("西安站");
        List<Order> b= orderMapper.findByorderId(1);
        return b;
    }
}

package com.wk.warehouse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

// 表示该类是一个控制器，可以接收前端的请求并响应
// 响应的是json数据

import org.springframework.beans.factory.annotation.Autowired;
import com.wk.warehouse.entity.StopoverStations;
import com.wk.warehouse.entity.Result;
import com.wk.warehouse.service.ClassAndRouteService;


@RestController // 会自动生成一个类型首字母小写的对象
@RequestMapping("/ClassAndRoute")
public class ClassAndRouterController {
    // 注入service对象
    @Autowired
    private ClassAndRouteService classAndRouteService;

    /** 给出上车的起点begin和终点end和日期date，查询符合需求的班次*/
    // @GetMapping("/getClass/BE")
    // public Result getClassByBE(@RequestParam String begin,@RequestParam String end,@RequestParam LocalDate date){
    //     List< Map<String, Object>> classAndRemainList= classAndRouteService.getClassAndRemainByBE(begin, end, date);
    //     return Result.ok(classAndRemainList);
    // }

    //根据班次查询路线
    @PostMapping("/getRoute/wagonId")
    public Result getRouteBywagonId(@RequestParam String wagonId){
        List<StopoverStations> stopoverStationList=classAndRouteService.getRouteBywagonId(wagonId);
        return Result.ok(stopoverStationList);
    }
}

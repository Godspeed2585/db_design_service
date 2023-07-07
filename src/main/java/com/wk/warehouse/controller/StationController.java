package com.wk.warehouse.controller;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// 表示该类是一个控制器，可以接收前端的请求并响应
// 响应的是json数据

import org.springframework.beans.factory.annotation.Autowired;
import com.wk.warehouse.entity.Station;
import com.wk.warehouse.entity.Result;
import com.wk.warehouse.service.StationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController // 会自动生成一个类型首字母小写的对象
@RequestMapping("/Station")
@Api(tags = "站点")
public class StationController{
    @Autowired
    private StationService stationService;
    /** 添加站点*/
    @PostMapping("/addStation")
    @ApiOperation(value = "添加站点")
    public Result addStation(@RequestBody Station station){
        boolean isDuplicate = stationService.isExist(station.getStationName()); // 判断新添加的角色是否和现有角色重复
        if (isDuplicate) {
            return Result.err(Result.CODE_ERR_SYS, "站点已存在！");
        }
        stationService.addStation(station);
        return Result.ok("添加成功！");
    }
    /** 删除站点 */
    @DeleteMapping("/deleteStation")
    @ApiOperation(value = "删除站点")
    public Result deleteStation(@RequestParam String stationName){
        int updateRows = stationService.deleteStation(stationName);
        return Result.ok("删除成功！",updateRows);
    }
    /**查询所有站点*/
    @GetMapping("/FindALLStation")
    @ApiOperation(value = "查询所有站点")
    public Result FindALLStation(){
        List<Station> stationList=stationService.findALLStation();
        return Result.ok(stationList);
    }
}

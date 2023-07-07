package com.wk.warehouse.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// 表示该类是一个控制器，可以接收前端的请求并响应
// 响应的是json数据

import org.springframework.beans.factory.annotation.Autowired;
import com.wk.warehouse.entity.StopoverStations;
import com.wk.warehouse.entity.Result;
import com.wk.warehouse.entity.IfStations;
import com.wk.warehouse.service.RouteService;


@RestController // 会自动生成一个类型首字母小写的对象
@RequestMapping("/route")
@Api(tags = "路线")
public class routeController {

    @Autowired
    private RouteService routeService;

    /** 根据routeId和出发站begin和目的站查找路线*/
    @GetMapping("/getRoute/begin_end_routeId")
    @ApiOperation(value = "根据路线号,出发点,目的地查询路线")
    public Result getRoute(@RequestParam String begin,@RequestParam String end,@RequestParam String routeId){
        List<StopoverStations> stopoverStationsList=routeService.getRoute(begin,end,routeId);
        System.out.println(stopoverStationsList);
        if(stopoverStationsList.isEmpty()) return Result.err(Result.CODE_NOT_FIND, "找不到路线");
        else return Result.ok(stopoverStationsList);
    }
    /**
     * 删除路线
     */
    @DeleteMapping("/deleteRoute")
    @ApiOperation(value = "根据路线号删除路线")
    public Result deleteRoute(@RequestParam String routeId){
        int updateRows = routeService.deleteRoute(routeId);
        if(updateRows>0){
            return Result.ok("删除成功！");
        }else{
            return Result.err(Result.CODE_ERR_SYS, "删除失败！");
        }
    }
    /**
     * 更改路线,即改途经，也改首发站
     */
    @PutMapping("/updataRoute/stopoverStationsList_ifstatios_routeId")
    @ApiOperation(value = "给出经停站,首末站更新路线")
    public Result updateRoute(@RequestBody List<StopoverStations> stopoverStationsList,@RequestBody IfStations ifStations){
        int updateRows = routeService.updateRoute(stopoverStationsList,ifStations);
        if(updateRows>0){
            return Result.ok("修改成功！");
        }else{
            return Result.err(Result.CODE_ERR_SYS, "修改失败！");
        }
    }
    /**
     * 增加路线，一条全新的路线
     */
    @PostMapping("/addRoute")
    @ApiOperation(value = "给出经停站,首末站添加路线")
    public Result addRoute(@RequestBody List<StopoverStations> stopoverStationsList,@RequestParam IfStations ifStations){
        boolean isDuplicate = routeService.isExist(ifStations.getRouteId()); // 判断新添加的角色是否和现有角色重复
        if (isDuplicate) {
            return Result.err(Result.CODE_ERR_SYS, "路线已存在！");
        }
        int updateRows = routeService.addRoute(stopoverStationsList,ifStations);
        if (updateRows > 0) {
            return Result.ok("添加成功！");
        } else {
            return Result.err(Result.CODE_ERR_SYS, "添加失败！");
        }
    }
}

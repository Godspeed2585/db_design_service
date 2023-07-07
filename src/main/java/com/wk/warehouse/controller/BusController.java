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

// 表示该类是一个控制器，可以接收前端的请求并响应
// 响应的是json数据

import org.springframework.beans.factory.annotation.Autowired;
import com.wk.warehouse.entity.Result;
import com.wk.warehouse.entity.Bus;
import com.wk.warehouse.service.BusService;

import java.util.List;

@RestController // 会自动生成一个类型首字母小写的对象
@Api(tags = "车辆管理")
@RequestMapping("/Bus")
public class BusController {
    @Autowired()
    private BusService busService;
    /**
     * 根据license查询bus
     */
    @GetMapping("/getBus")
    @ApiOperation(value = "根据license查询bus")
    public Result FindBus(@RequestParam String license){
        List<Bus> BusList=busService.findBylicense(license);
        if(null == BusList) return Result.err(Result.CODE_NOT_FIND, "找不到车辆");
        else return Result.ok(BusList);
    }
    /**
     * 根据page查询总bus信息
     */
    @GetMapping("/getBusByPage")
    @ApiOperation(value = "根据page查询总bus信息")
    public Result FindBusByPage(@RequestParam int page){
        List<Bus> BusList=busService.findBypage(page*10);
        //if(null == BusList) return Result.err(Result.CODE_NOT_FIND, "找不到车辆");
        return Result.ok(BusList);
    }
    /**
     * 删除bus
     */
    @DeleteMapping("/deleteBus")
    @ApiOperation(value = "根据license删除bus")
    public Result deleteBus(@RequestParam String license){
        int updateRows = busService.delete(license);
        if(updateRows>0){
            return Result.ok("删除成功！");
        }else{
            return Result.err(Result.CODE_ERR_SYS, "删除失败！");
        }
    }
    /**
     * 更改bus
     */
    @PutMapping("/updataBus")
    @ApiOperation(value = "根据license更新bus")
    public Result updateBus(@RequestBody Bus busobj){
        int updateRows = busService.update(busobj);
        if(updateRows>0){
            return Result.ok("修改成功！");
        }else{
            return Result.err(Result.CODE_ERR_SYS, "修改失败！");
        }
    }
    /*
     * 增加bus
     */
    /** 添加站点*/
    @PostMapping("/addBus")
    @ApiOperation(value = "添加bus")
    public Result addBus(@RequestBody Bus busobj){
        int isDuplicate = busService.isExist(busobj.getLicense()); // 判断新添加的角色是否和现有角色重复
        if (isDuplicate != 0) {
            return Result.err(Result.CODE_ERR_SYS, "车辆已存在！");
        }
        int updateRows = busService.insert(busobj);
        if(updateRows>0){
            return Result.ok("添加成功！");
        }else{
            return Result.err(Result.CODE_ERR_SYS, "添加失败！");
        }

    }

    /*
     * 根据license查询bus所得的表的总数
     */
    /** 添加站点*/
    @GetMapping("/total_getBus")
    @ApiOperation(value = "查询bus表的项目总数")
    public Result TotalNum_FindBus(){
        int total= busService.total_bus();
        if(total < 0) return Result.err(Result.CODE_NOT_FIND, "错误:无法根据license查询bus所得的表的总数");
        else return Result.ok(total);
    }
}

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
import com.wk.warehouse.entity.Driver;
import com.wk.warehouse.service.DriverService;

import java.util.List;

@RestController // 会自动生成一个类型首字母小写的对象
@Api(tags = "司机管理")
@RequestMapping("/Driver")
public class DriverController {
    @Autowired()
    private DriverService driverService;

    /**
     * 根据jobId查询driver
     */
    @GetMapping("/getDriver/jobId")
    @ApiOperation(value = "根据jobId查询driver")
    public Result FindDriver(@RequestParam int jobId){
        Driver driver1=driverService.findByjobId(jobId);
        if(null == driver1) return Result.err(Result.CODE_NOT_FIND, "找不到车辆");
        else return Result.ok(driver1);
    }

    /**
     * 查询所有driver
     */
    @GetMapping("/getAllDriver")
    @ApiOperation(value = "查询所有driver")
    public Result FindAllDriver(){
        List<Driver> driverList= driverService.selectAll();
        return Result.ok(driverList);
    }

    /**
     * 删除driver
     */
    @DeleteMapping("/deleteDriver")
    @ApiOperation(value = "根据jobId删除driver")
    public Result deleteDriver(@RequestParam int jobId){
        int updateRows = driverService.delete(jobId);
        if(updateRows>0){
            return Result.ok("删除成功！");
        }else{
            return Result.err(Result.CODE_ERR_SYS, "删除失败！");
        }
    }


    /**
     * 更改driver
     */
    @PutMapping("/updatedriver")
    @ApiOperation(value = "根据jobId更新driver")
    public Result updateDriver(@RequestBody Driver driverobj){
        int updateRows = driverService.update(driverobj);
        if(updateRows>0){
            return Result.ok("修改成功！");
        }else{
            return Result.err(Result.CODE_ERR_SYS, "修改失败！");
        }
    }


    /*
     * 增加driver
     */
    @PostMapping("/addDriver")
    @ApiOperation(value = "添加driver")
    public Result addDriver(@RequestBody Driver driverobj){
        int isDuplicate = driverService.isExist(driverobj.getjobId()); // 判断新添加的司机是否和现有司机重复
        if (isDuplicate != 0) {
            return Result.err(Result.CODE_ERR_SYS, "司机已存在！");
        }
        int updateRows = driverService.insert(driverobj);
        if(updateRows>0){
            return Result.ok("添加成功！");
        }else{
            return Result.err(Result.CODE_ERR_SYS, "添加失败！");
        }

    }
}

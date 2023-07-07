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
import com.wk.warehouse.entity.Passenger;
import com.wk.warehouse.service.PassengerService;

import java.util.List;

@RestController // 会自动生成一个类型首字母小写的对象
@Api(tags = "乘客管理")
@RequestMapping("/Passenger")
public class PassengerController {
    @Autowired()
    private PassengerService passengerService;
    
    /**
     * 根据phoneNumber查询某用户的所有乘客
     */
    @GetMapping("/getPassenger/PphoneNumber")
    @ApiOperation(value = "根据PphoneNumber查询passenger")
    public Result FindPassenger1(@RequestParam String phoneNumber){
        List<Passenger> PassengerList=passengerService.findByPphoneNumber(phoneNumber);
        if(null == PassengerList) return Result.err(Result.CODE_NOT_FIND, "该用户下没有乘客");
        else return Result.ok(PassengerList);
    }


    /**
     * 根据idCard查询乘客
     */
    @GetMapping("/getPassenger/idCard")
    @ApiOperation(value = "根据idCard查询passenger")
    public Result FindPassenger2(@RequestParam String idCard){
        Passenger passenger1=passengerService.findByidCard(idCard);
        if(null == passenger1) return Result.err(Result.CODE_NOT_FIND, "该乘客不存在");
        else return Result.ok(passenger1);
    }


    /**
     * 查询所有乘客
     */
    @GetMapping("/getAllPassenger")
    @ApiOperation(value = "查询查询所有passenger")
    public Result FindAllPassenger(){
        List<Passenger> passengerlist=passengerService.selectAll();
        return Result.ok(passengerlist);
    }


    /**
     * 删除乘客
     */
    @DeleteMapping("/deletePassenger")
    @ApiOperation(value = "根据idCard删除passenger")
    public Result deletePassenger(@RequestParam String idCard){
        int updateRows = passengerService.delete(idCard);
        if(updateRows>0){
            return Result.ok("删除成功！");
        }else{
            return Result.err(Result.CODE_ERR_SYS, "删除失败！");
        }
    }


    /**
     * 更改乘客信息
     */
    @PutMapping("/updataPassenger")
    @ApiOperation(value = "根据idCard更新passenger")
    public Result updatePassenger(@RequestBody Passenger passengerobj){
        int updateRows = passengerService.update(passengerobj);
        if(updateRows>0){
            return Result.ok("修改成功！");
        }else{
            return Result.err(Result.CODE_ERR_SYS, "修改失败！");
        }
    }


    /*
     * 增加乘客
     */
    @PostMapping("/addPassenger")
    @ApiOperation(value = "添加passenger")
    public Result addPassenger(@RequestBody Passenger passengerobj){
        int isDuplicate = passengerService.isExist(passengerobj.getidCard()); // 判断新添加的角色是否和现有角色重复
        if (isDuplicate != 0) {
            return Result.err(Result.CODE_ERR_SYS, "该乘客已存在！");
        }

        int updateRows = passengerService.insert(passengerobj);
        if(updateRows>0){
            return Result.ok("添加成功！");
        }else{
            return Result.err(Result.CODE_ERR_SYS, "添加失败！");
        }

    }
}

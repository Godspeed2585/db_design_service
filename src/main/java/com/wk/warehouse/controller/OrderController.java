package com.wk.warehouse.controller;

import com.wk.warehouse.entity.Bus;
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
import com.wk.warehouse.entity.Order;
import com.wk.warehouse.service.OrderService;

import java.util.List;

@RestController // 会自动生成一个类型首字母小写的对象
@Api(tags = "订单管理")
@RequestMapping("/Order")
public class OrderController {
    @Autowired()
    private OrderService orderService;
    /**
     * 根据orderId查询order
     */
    @GetMapping("/getOrder")
    @ApiOperation(value = "根据orderId查询order")
    public Result FindOrder(@RequestParam int orderId){
        List<Order> OrderList=orderService.findByorderId(orderId);
        if(null == OrderList) return Result.err(Result.CODE_NOT_FIND, "找不到订单");
        else return Result.ok(OrderList);
    }
    /**
     * 根据page查询总order信息
     */
    @GetMapping("/getOrderByPage")
    @ApiOperation(value = "根据page查询总order信息")
    public Result FindOrderByPage(@RequestParam int page){
        List<Order> OrderList=orderService.findBypage(page*10);
        //if(null == BusList) return Result.err(Result.CODE_NOT_FIND, "找不到车辆");
        return Result.ok(OrderList);
    }
    /**
     * 删除order
     */
    @DeleteMapping("/deleteOrder")
    @ApiOperation(value = "根据orderId删除order")
    public Result deleteOrder(@RequestParam int orderId){
        int updateRows = orderService.delete(orderId);
        if(updateRows>0){
            return Result.ok("删除成功！");
        }else{
            return Result.err(Result.CODE_ERR_SYS, "删除失败！");
        }
    }
    /**
     * 更改order
     */
    @PutMapping("/updataOrder")
    @ApiOperation(value = "根据orderId更新order")
    public Result updateOrder(@RequestBody Order orderobj){
        int updateRows = orderService.update(orderobj);
        if(updateRows>0){
            return Result.ok("修改成功！");
        }else{
            return Result.err(Result.CODE_ERR_SYS, "修改失败！");
        }
    }
    /*
     * 增加order
     */
    /** 添加站点*/
    @PostMapping("/addOrder")
    @ApiOperation(value = "添加order")
    public Result addOrder(@RequestBody Order orderobj){
        int isDuplicate = orderService.isExist(orderobj.getOrderId()); // 判断新添加的角色是否和现有角色重复
        if (isDuplicate != 0) {
            return Result.err(Result.CODE_ERR_SYS, "车辆已存在！");
        }
        int updateRows = orderService.insert(orderobj);
        if(updateRows>0){
            return Result.ok("添加成功！");
        }else{
            return Result.err(Result.CODE_ERR_SYS, "添加失败！");
        }

    }

    /*
     * 根据orderId查询order所得的表的总数
     */
    /** 添加站点*/
    @GetMapping("/total_getOrder")
    @ApiOperation(value = "查询order表的项目总数")
    public Result TotalNum_FindOrder(){
        int total= orderService.total_order();
        if(total < 0) return Result.err(Result.CODE_NOT_FIND, "错误:无法根据orderId查询order所得的表的总数");
        else return Result.ok(total);
    }

}

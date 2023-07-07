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
import com.wk.warehouse.entity.User;
import com.wk.warehouse.service.UserService;

import java.util.List;

@RestController // 会自动生成一个类型首字母小写的对象
@Api(tags = "用户管理")
@RequestMapping("/User")
public class UserController {
    @Autowired()
    private UserService userService;

    /**
     * 根据phoneNumber查询user
     */
    @GetMapping("/getUser/phoneNumber")
    @ApiOperation(value = "根据phoneNumber查询user")
    public Result FindUser(@RequestParam String phoneNumber){
        User user1=userService.findByphoneNumber(phoneNumber);
        if(null == user1) return Result.err(Result.CODE_NOT_FIND, "找不到该用户");
        else return Result.ok(user1);
    }

    /**
     * 查询所有user
     */
    @GetMapping("/getAllUser")
    @ApiOperation(value = "查询所有user")
    public Result FindAllUser(){
        List<User> userlist=userService.selectAll();
        return Result.ok(userlist);
    }

    /**
     * 删除user
     */
    @DeleteMapping("/deleteUser")
    @ApiOperation(value = "根据phoneNumber删除User")
    public Result deleteUser(@RequestParam String phoneNumber){
        int updateRows = userService.delete(phoneNumber);
        if(updateRows>0){
            return Result.ok("删除成功！");
        }else{
            return Result.err(Result.CODE_ERR_SYS, "删除失败！");
        }
    }


    /**
     * 更改user信息
     */
    @PutMapping("/updateUser")
    @ApiOperation(value = "根据phoneNumber更新User")
    public Result updateUser(@RequestBody User userobj){
        int updateRows = userService.UpdateUser(userobj);
        if(updateRows>0){
            return Result.ok("修改成功！");
        }else{
            return Result.err(Result.CODE_ERR_SYS, "修改失败！");
        }
    }


    /*
     * 增加user(用户注册)
     */
    @PostMapping("/addUser")
    @ApiOperation(value = "添加User")
    public Result addUser(@RequestBody User userobj){
        int isDuplicate = userService.isExist(userobj.getphoneNumber()); // 判断新添加的角色是否和现有角色重复
        if (isDuplicate != 0) {
            return Result.err(Result.CODE_ERR_SYS, "用户已存在！");
        }
        int updateRows = userService.insert(userobj);
        if(updateRows>0){
            return Result.ok("添加成功！");
        }else{
            return Result.err(Result.CODE_ERR_SYS, "添加失败！");
        }

    }
}

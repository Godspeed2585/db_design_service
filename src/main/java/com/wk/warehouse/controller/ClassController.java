package com.wk.warehouse.controller;


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
import com.wk.warehouse.entity.Class;
import com.wk.warehouse.service.ClassService;


@RestController // 会自动生成一个类型首字母小写的对象
@RequestMapping("/Class")
public class ClassController {
    @Autowired
    private ClassService classService;
    /**
     * 根据wagonId查询班次
     */
    @GetMapping("/getClass/wagonId")
    public Result FindClass(@RequestParam String wagonId){
        Class Class =classService.getClassByWagenId(wagonId);
        return Result.ok(Class);
    }
    /**
     * 删除班次
     */
    @DeleteMapping("/deleteClass")
    public Result deleteClass(@RequestParam String wagonId){
        int updateRows = classService.deleteClass(wagonId);
        if(updateRows>0){
            return Result.ok("删除成功！");
        }else{
            return Result.err(Result.CODE_ERR_SYS, "删除失败！");
        }
    }
    /**
     * 更改班次
     */
    @PutMapping("/updataClass")
    public Result updateClass(@RequestBody Class classobj){
        int updateRows = classService.updateClass(classobj);
        if(updateRows>0){
            return Result.ok("修改成功！");
        }else{
            return Result.err(Result.CODE_ERR_SYS, "修改失败！");
        }
    }
    /**
     * 增加班次
     */
    /** 添加站点*/
    @PostMapping("/addClass")
    public Result addClass(@RequestBody Class classobj){
        boolean isDuplicate = classService.isExist(classobj.getWagonId()); // 判断新添加的角色是否和现有角色重复
        if (isDuplicate) {
            return Result.err(Result.CODE_ERR_SYS, "站点已存在！");
        }
        int updateRows = classService.addClass(classobj);
        if(updateRows>0){
            return Result.ok("删除成功！");
        }else{
            return Result.err(Result.CODE_ERR_SYS, "删除失败！");
        }

    }

}

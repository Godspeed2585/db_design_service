package com.example.db_design_service.controller;
import com.example.db_design_service.service.RedisUtils;
import com.example.db_design_service.bean.*;
import com.example.db_design_service.service.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.sql.Date;
/***
 * 有关司机信息管理的业务处理层
 */
@RestController
@RequestMapping("/driver")
public class DriverManagerController {
    @Resource
    private DriverService driverService;

    @Resource
    private RedisUtils redisUtils;
    private static final Logger logger = LoggerFactory.getLogger(DriverManagerController.class);


     /**
     * 使用driver表的主键joId获取司机信息
     * 对应前端的getDriverInfo请求
     * @param token
     * @param jobId
     * @return
     */
    @RequestMapping(value ="/getDriverInfo",method = RequestMethod.GET)
    public DriverInfoReturnData searchDriverByjobId( @RequestParam int jobId ) {

            DriverInfo driverInfo = driverService.selectDriverInfo(jobId);
            return new DriverInfoReturnData(1,driverInfo);
    }


    /**
     * 使用driver表的主键jobId删除司机信息
     * 对应前端的deleteDriver请求
     * @param token
     * @param jobId
     * @return
     */
    @RequestMapping(value ="/deleteDriver",method = RequestMethod.GET)
    public RespBean deleteDriver(@Valid @RequestBody Map<String,Object> request, BindingResult bindingResult) {

        String token = (String) request.get("token");
        String a = redisUtils.get(token);//从Token获取当前登录的用户的信息
        String data [] = a.split(",");
        int isAdmin = Integer.parseInt(data[0]);//data[]是String,而isAdmin是int,需要先转化再使用
        int jobId = (int)request.get("jobId");

        if(isAdmin==1 || isAdmin ==2){//检查是否为管理员/超管
        try {
            driverService.deleteDriver(jobId);
            return new RespBean(1,"删除成功");
        }
        catch (Exception e)
        {
            return new RespBean(405,"删除失败");
        }
    }
        return new RespBean(405,"删除失败");
    }


    /**
     * 添加司机(仅管理员/超管可用)
     * 对应前端的addDriverInfo请求
     * @param request
     * @param bindingResult
     * @return
     */
    @RequestMapping(value ="/addDriverInfo",method = RequestMethod.POST)
    public RespBean addDriver(@Valid @RequestBody Map<String,Object> request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) { System.out.println(bindingResult.getFieldError().getDefaultMessage()); }

        String token = (String) request.get("token");
        String a = redisUtils.get(token);//从Token获取当前登录的用户的信息
        String data [] = a.split(",");
        int isAdmin = Integer.parseInt(data[0]);//data[]是String,而isAdmin是int,需要先转化再使用

        String driverName = (String) request.get("driverName");
        Date entryTime = (Date) request.get("entryTime");
        String  gender = (String)request.get("gender");
        int  jobId = (int)request.get("jobId");

        if(isAdmin==1 || isAdmin ==2){//检查是否为管理员/超管
        try {
            driverService.insertDriver(driverName, entryTime, gender, jobId);
            return new RespBean(1,"添加成功");
        }
        catch (Exception e)
        {
            logger.info(e.getMessage());
            return new RespBean(405,"添加失败");
        }
    }
        return new RespBean(405,"添加失败");
    }


    /**
     * 修改司机信息(仅管理员/超管可用)
     * 对应前端的changedriver请求
     * @param request
     * @param bindingResult
     * @return
     */
    @RequestMapping(value ="/changeDriver",method = RequestMethod.POST)
    public RespBean changeDriver(@Valid @RequestBody Map<String,Object> request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) { System.out.println(bindingResult.getFieldError().getDefaultMessage()); }
        
        String token = (String) request.get("token");
        String a = redisUtils.get(token);//从Token获取当前登录的用户的信息
        String data [] = a.split(",");
        int isAdmin = Integer.parseInt(data[0]);//data[]是String,而isAdmin是int,需要先转化再使用

        String dirverName = (String)request.get("driverName");
        Date entryTime = (Date)request.get("entryTime");
        String gender = (String)request.get("gender");
        int jobId = (int)request.get("jobId");
 
        if(isAdmin==1 || isAdmin ==2){//检查是否为管理员/超管
        try
            {
                driverService.updateDriver(dirverName, entryTime, gender, jobId);
                return new RespBean(1,"修改成功");
            }
        catch (Exception e)
        {
            logger.info(e.getMessage());
            return new RespBean(405,"修改失败");
        }
        }
        return new RespBean(405,"修改失败");
    }
    
}

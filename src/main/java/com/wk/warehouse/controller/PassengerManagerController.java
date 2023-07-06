package com.wk.warehouse.controller;
import com.wk.warehouse.service.RedisUtils;
import com.wk.warehouse.entity.*;
import com.wk.warehouse.service.PassengerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/***
 * 有关乘客信息管理的业务处理层
 */
@RestController
@RequestMapping("/passenger")
public class PassengerManagerController {

    @Resource
    private PassengerService passengerService;

    @Resource
    private RedisUtils redisUtils;

    private static final Logger logger = LoggerFactory.getLogger(PassengerManagerController.class);


    /**
     * 获取当前登录用户(user.phoneNumber)名下所有的乘客信息
     * 对应前端的getPassengerInfo请求
     * @param token
     * @param phoneNumber
     * @return
     */
    @RequestMapping(value ="/getPassengerInfo",method = RequestMethod.GET)
    public PassengerReturnList searchPassengerByphoneNumber(@RequestParam String token) {

            logger.info(redisUtils.get(token));
            String user = redisUtils.get(token);
            String data [] = user.split(",");
            String phoneNumber = data[5];//通过Token获取当前登录用户的phoneNumber,并作为selectPassengerInfo的参数
            List<PassengerInfo> passengerInfoList = passengerService.selectPassengerInfo(phoneNumber);
            return new PassengerReturnList(1,passengerInfoList);
    }



    /**
     * 使用idCard查询某乘客信息(仅管理员/超管可用)
     * 对应前端的getPassengerByidCard请求
     * @param token
     * @param idCard
     * @return
     */
    @RequestMapping(value ="/getPassengerInfoByidCard",method = RequestMethod.GET)
    public PassengerReturnList getPassengerByidCard(@RequestParam String token, String idCard) {

            String user = redisUtils.get(token);
            String data [] = user.split(",");
            int isAdmin=Integer.parseInt(data[0]);
    
            logger.info(idCard);
            if(isAdmin == 1 ||isAdmin ==2){
                try
            {List<PassengerInfo> passengerInfoList = passengerService.selectPassengerInfoByidCard(idCard);
            return new PassengerReturnList(1,passengerInfoList);}
            catch(Exception e)
        {
            logger.info("查找失败");
            return new PassengerReturnList(404, null);
        }    
        }
            return new PassengerReturnList(404,null);
    }


    
    /**
     *
     * 使用passenger表的主键idCard删除乘客信息
     * (这里考虑到一个phoneNumber可以有多个passenger,所以删除时使用passenger表的主键idCard)
     * 对应前端的deletePassenger请求
     * @param token
     * @param idCard
     * @return
     */
    @RequestMapping(value ="/deletePassenger",method = RequestMethod.GET)
    public RespBean deletePassenger( @RequestParam String idCard ) {

        try {
            passengerService.deletePassenger(idCard);
            return new RespBean(1,"删除成功");
        }
        catch (Exception e)
        {
            return new RespBean(405,"删除失败");
        }

    }


    /**
     *
     * 为当前用户(phoneNumber)添加乘客
     * 对应前端的addPassengerInfo请求
     * @param request
     * @param bindingResult
     * @return
     */
    @RequestMapping(value ="/addPassengerInfo",method = RequestMethod.POST)
    public RespBean addPassenger( @Valid @RequestBody Map<String,Object> request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) { System.out.println(bindingResult.getFieldError().getDefaultMessage()); }

        String token = (String) request.get("token");
        String user = redisUtils.get(token);
        String data [] = user.split(",");
        String phoneNumber = data[5];//通过Token获取当前登录的用户的信息
        
        String passengerName = (String) request.get("passengerName");//不允许出现passengerPhoneNumber与任意user.phoneNumber都不对应的情况
        int  age = (int)request.get("age");
        String  idCard = (String)request.get("idCard");

        logger.info(idCard);
        
        try {
            passengerService.insertPassenger(passengerName, phoneNumber, age, idCard);
            return new RespBean(1,"添加成功");
        }
        catch (Exception e)
        {
            logger.info(e.getMessage());
            return new RespBean(405,"添加失败");
        }
    
    }


}

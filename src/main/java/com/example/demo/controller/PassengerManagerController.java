package com.example.db_design_service.controller;
import com.example.db_design_service.service.RedisUtils;
import com.example.db_design_service.bean.*;
import com.example.db_design_service.service.DriverService;
import com.example.db_design_service.service.PassengerService;
import com.example.db_design_service.service.UserService;
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
 * 有关乘客信息管理的业务处理层
 */
@RestController
@RequestMapping("/passenger")
public class PassengerManagerController {
    @Resource
    private UserService userService;

    @Resource
    private PassengerService passengerService;

    @Resource
    private DriverService driverService;

    @Resource
    private RedisUtils redisUtils;

    private static final Logger logger = LoggerFactory.getLogger(PassengerManagerController.class);

}

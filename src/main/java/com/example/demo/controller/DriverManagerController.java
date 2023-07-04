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
 * 有关司机信息管理的业务处理层
 */
@RestController
@RequestMapping("/driver")
public class DriverManagerController {
    @Resource
    private DriverService driverService;

    @Resource
    private RedisUtils redisUtils;
    private static final Logger logger = LoggerFactory.getLogger(UserManagerController.class);


    /**
     * 查询所有司机信息
     * 对应前端的getAllDriver请求
     *@param offset
     *@param limit
     *@return
     */
    @RequestMapping(value ="/getAlldriver",method = RequestMethod.GET)
    public GetAllDriverReturnData driverInfo(int status, DriverInfo driverinfos)
    {

        List<Driver>  driver = driverService.selectAllDriver();

        if(!driver.isEmpty())
        {
            return new GetAllDriverReturnData(1,driver);
        }

        return new GetAllDriverReturnData(404,driver);
    }


    /**
     * 根据工号查询某一司机的信息
     * 对应前端的getdriverinfo请求
     * @param jobId
     * @return
     */
     @RequestMapping(value ="/driverinfo",method = RequestMethod.GET)
     public Driver getdriverinfo()
}

package com.example.db_design_service.controller;

import com.example.db_design_service.service.RedisUtils;
import com.example.db_design_service.service.DriverService;
import com.example.db_design_service.service.PassengerService;
import com.example.db_design_service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.db_design_service.bean.*;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.sql.Date;

//新添加 - 验证码
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/***
 * 有关用户信息管理的业务处理层
 */
@RestController
@RequestMapping("/user")
public class UserManagerController {
    @Resource
    private UserService userService;

    @Resource
    private PassengerService passengerService;

    @Resource
    private DriverService driverService;

    @Resource
    private RedisUtils redisUtils;
    private static final Logger logger = LoggerFactory.getLogger(UserManagerController.class);

    //新添加 - 验证码
	@Autowired
	// Springboot提供的针对redis操作字符串的便捷工具对象
	private static StringRedisTemplate stringRedisTemplate;
    

    /***
     * 用户登录
     * 对应前端的login请求
     * @param request
     * @param bindingResult
     * @return
     */
    @RequestMapping(value ="/login", method = RequestMethod.POST)
    public RespBean UserLogin(@Valid @RequestBody Map<String,Object> request, BindingResult bindingResult) {

        //新添加 - 验证码
        String serverCode = stringRedisTemplate.opsForValue().get(loginUser.getVerificationKey());
        

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
        }
        String phoneNumber = (String) request.get("phoneNumber");
        String password = (String) request.get("password");

        try
        {
            /***
             * 查找是否有此用户
            */

            List<UserLogin> userlogins = userService.selectAllUserLogin();

            for (UserLogin userlogin : userlogins) {
                if (userlogin.getphoneNumber().equals(phoneNumber)  && userlogin.getpassword().equals(password))
                {
                    logger.info("登录信息如下");
                    logger.info(phoneNumber);
                    logger.info(password);
                    logger.info("登录成功");

                    //token生成  用户信息redis缓存
                    User user  = userService.selectUserInfo(userlogin.getphoneNumber());
                    String token =user.getisAdmin()+","+user.getidCard()+","+user.getpassword()+","+user.getuserName()+","+user.getemail()+","+user.getphoneNumber();
//                  中间用逗号隔开，后续使用user.split(",")来获取特定元素  
//                  Token token =new Token(userlogin.getUser_phone_number()+"+++"+userlogin.getUser_password());
//                  String [] roles= new String[1];
//                  roles[0] = "admin";
//                  User user = userService.selectUserInfo(userlogin.getUser_phone_number());
//                  UserInfoReturnData data = new UserInfoReturnData(roles,user.getUser_phone_number(),user.getUser_id_number(),user.getUser_real_name());


                    //将用户登陆信息存入token中
                    redisUtils.set(userlogin.getphoneNumber()+"msbfajshbadsmnfbasmfa"+userlogin.getpassword(),token);
//                  redisUtils.get(token.getToken());

                    return new RespBean(1,userlogin.getphoneNumber()+"msbfajshbadsmnfbasmfa"+userlogin.getpassword());

                }
            }

        }
        catch(Exception e)
        {
            logger.info("登录失败");
            return new RespBean(404, "失败");
        }
        return new RespBean(404, "失败");
    }


    /***
     * 登录成功后，向前端返回cookie中的内容 作为用户登录的标记
     * 并且将用户登陆信息存入token中
     * 对应前端的getAdminInfo请求
     * @param token
     * @return
     */
    @RequestMapping(value ="/info",method = RequestMethod.GET)
    public UserInfoReturnData GetUserInfo(@RequestParam String token) {
        try {
            String [] roles = new String[1];
            String a = redisUtils.get(token);
            String data [] = a.split(",");
            return new UserInfoReturnData(1,new UserInfo(false,data[1],data[1],data[2],data[3],data[4]));
            //6个Data依次是:isAdmin idCard password userName email phoneNumber  默认新用户的身份为非管理员
        }
        catch (Exception e)
        {
            return new UserInfoReturnData(404,new UserInfo(false,"null","null","null","null","null"));
        }

    }

    /**
     * 用户注册(添加新用户)
     * 对应前端的register请求
     * @param request
     * @param bindingResult
     * @return
     */
    @RequestMapping(value ="/register",method = RequestMethod.POST)
    public RespBean UserRegister(@Valid @RequestBody Map<String,Object> request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
        }

        String idCard = (String)request.get("idCard");
        String password = (String) request.get("password");
        String password2 = (String)request.get("password2");
        String userName = (String)request.get("userName");
        String email = (String) request.get("email");
        String phoneNumber = (String) request.get("phoneNumber");
        
        try {
            /**
             * 查询此用户是否已经注册
             */
            List<UserLogin> userlogins = userService.selectAllUserLogin();
            for (UserLogin userlogin : userlogins) {
                if(userlogin.getphoneNumber().equals(phoneNumber))
                {
                    logger.info("phoneNumber重复");
                    return new RespBean(403,"phoneNumber重复");
                }

                else if(!password2.equals(password))
                {
                    logger.info("两次输入的password不同");
                    return new RespBean(404,"两次输入的password不同");
                }

                else
                {
                    /**
                     * 如果没有重复且两次输入密码相同则注册成功
                     */

                    try {
                        User user  = new User(false,idCard,password,userName,email,phoneNumber); //新注册的用户默认身份为非管理员
                        boolean flag = userService.insertUser(user);
                        if(flag)
                        {
                            logger.info("注册成功");
                            return new RespBean(1,userlogin.getphoneNumber()+"msbfajshbadsmnfbasmfa"+userlogin.getpassword());
                        }
                        else
                        {
                            logger.info("注册失败");
                            return new RespBean(405,"注册失败");
                        }
                    }
                    catch (Exception e)
                    {
                        logger.info("注册失败");
                        return new RespBean(403,"用户名重复");
                    }

                }
            }
        }
        catch (Exception e)
        {
            return new RespBean(405,"注册失败");
        }

        return new RespBean(405,"注册失败");

    }

     /**
     * 用户退出登录的接口
     * 对应前端的signout请求
     * @param token
     * @return
     */
    @RequestMapping(value ="/signout",method = RequestMethod.GET)
    public RespBean signout() {
        try {

            return new RespBean(1,"退出成功");
        }
        catch (Exception e)
        {
            return new RespBean(404,"退出失败");
        }

    }

    /**
     * 查询用户信息的接口
     * 对应前端的getUserInfo请求
     * @param token
     * @return
     */
    @RequestMapping(value ="/userinfo",method = RequestMethod.GET)
    public UserInfoReturnData getUserInfo(@RequestParam String token) {
        String user = redisUtils.get(token);
        String data [] = user.split(",");
        return new UserInfoReturnData(1,new UserInfo(false,data[1],data[2],data[3],data[4],data[5]));
    }


    /**
     * 修改密码的接口
     * 对应前端的changePassword请求
     * @param request
     * @param bindingResult
     * @return
     */
    @RequestMapping(value ="/changepassword",method = RequestMethod.POST)
    public RespBean ChangePassword(@Valid @RequestBody Map<String,Object> request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
        }
        String token = (String)request.get("token");
        String oldpassword = (String)request.get("oldpassword");
        String newpassword = (String)request.get("newpassword");
        String user = redisUtils.get(token);
        String data [] = user.split(",");
        String phoneNumber = data[5];

        User userInfo = userService.selectUserInfo(phoneNumber);
            if(userInfo.getpassword().equals(oldpassword))//修改前必须输入旧密码
            {
                userService.UpdatePassword(newpassword,phoneNumber);
                return new RespBean(1,"密码修改成功");
            }
        return new RespBean(405,"密码修改失败");
    }



    /**
     * 以管理员身份登录
     * 对应前端的adminLogin请求
     * @param request
     * @param bindingResult
     * @return
     */
    @RequestMapping(value ="/adminLogin",method = RequestMethod.POST)
    public RespBean AdminLogin(@Valid @RequestBody Map<String,Object> request, BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
        }
        String phoneNumber = (String) request.get("phoneNumber");
        String password = (String) request.get("password");

        try
        {
            List<User> users = userService.selectAllUser();

            for (User user : users) {
                if (user.getphoneNumber().equals(phoneNumber)  && user.getpassword().equals(password) && user.isAdmin == true )//账号必须匹配且需验证管理员身份
                {

                    //token生成  用户信息redis缓存
                    String token =user.getisAdmin()+","+user.getidCard()+","+user.getpassword()+","+user.getuserName()+","+user.getemail()+","+user.getphoneNumber();

                    /**
                     * 将用户登陆信息存入token中
                     */
                    redisUtils.set(user.getphoneNumber()+"msbfajshbadsmnfbasmfa"+user.getpassword(),token);
//                  redisUtils.get(token.getToken());

                    return new RespBean(1,user.getphoneNumber()+"msbfajshbadsmnfbasmfa"+user.getpassword());

                }
            }

        }
        catch(Exception e)
        {
            logger.info("登录失败");
            return new RespBean(404, "失败");
        }
        return new RespBean(404, "失败");
    }


    /***
     * 获取所有用户
     * 对应前端的getAllUser请求
     * @param token
     * @return
     */
    @RequestMapping(value ="/getAllUser",method = RequestMethod.GET)
    public GetAllUserReturnData getAllUser(@RequestParam String token) {
        try {
            String a = redisUtils.get(token);
            if(a != null)
            {
                List<User> userList = userService.selectAllUser();
                    return  new GetAllUserReturnData(1,userList);

            }

        }
        catch (Exception e)
        {
            return  new GetAllUserReturnData(404,null);

        }
        return  new GetAllUserReturnData(404,null);
    }


    /***
     * 删除用户
     * 对应前端的deleteUser请求
     * @param token
     * @return
     */
    @RequestMapping(value ="/deleteUser",method = RequestMethod.GET)
    public RespBean deleteUser(@RequestParam String phoneNumber) {
        try {
                userService.deleteUser(phoneNumber);
                return new RespBean(1,"删除成功");

        }
        catch (Exception e)
        {
            return new RespBean(404,"删除失败");
        }

    }

}
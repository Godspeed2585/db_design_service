package com.wk.warehouse.controller;

import com.wk.warehouse.service.RedisUtils;
import com.wk.warehouse.service.DriverService;
import com.wk.warehouse.service.PassengerService;
import com.wk.warehouse.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.wk.warehouse.entity.*;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.sql.Date;
import java.util.Scanner;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
     * 普通用户登录
     * 对应前端的userlogin请求
     * @param request
     * @param bindingResult
     * @return
     */
    @RequestMapping(value ="/userlogin", method = RequestMethod.POST)
    public RespBean UserLogin(@Valid @RequestBody Map<String,Object> request, BindingResult bindingResult) {

        //新添加 - 验证码
       //String serverCode = stringRedisTemplate.opsForValue().get(loginUser.getVerificationKey());
        
        if (bindingResult.hasErrors()) { System.out.println(bindingResult.getFieldError().getDefaultMessage()); }
        //如果有字段验证错误,则返回错误信息

        String phoneNumber = (String) request.get("phoneNumber");
        String password = (String) request.get("password");
        int isAdmin = (int) request.get("isAdmin");
        try
        {
            /***
             * 查找是否有此用户
            */

            List<UserLogin> userlogins = userService.selectAllUserLogin();

            for (UserLogin userlogin : userlogins) {
                if (userlogin.getphoneNumber().equals(phoneNumber)  && userlogin.getpassword().equals(password))
                {
                    logger.info("用户登录信息如下");
                    logger.info(phoneNumber);
                    logger.info(password);
                    logger.info("isAdmin==0");
                    logger.info("用户登录成功");

                    //token生成登录用户信息的redis缓存
                    UserInfo user  = userService.selectUserInfo(userlogin.getphoneNumber());
                    String token =user.getisAdmin()+","+user.getidCard()+","+user.getpassword()+","+user.getuserName()+","+user.getemail()+","+user.getphoneNumber();
//                  中间用逗号隔开，后续使用user.split(",")来获取特定元素  
//                  Token token =new Token(userlogin.getUser_phone_number()+"+++"+userlogin.getUser_password());
//                  String [] roles= new String[1];
//                  roles[0] = "admin";
//                  User user = userService.selectUserInfo(userlogin.getUser_phone_number());
//                  UserInfoReturnData data = new UserInfoReturnData(roles,user.getUser_phone_number(),user.getUser_id_number(),user.getUser_real_name());


                    //将用户登陆信息存入token中
                    redisUtils.set(userlogin.getphoneNumber()+"msbfajshbadsmnfbasmfa"+userlogin.getpassword()+"msbfajshbadsmnfbasmfa"+userlogin.getisAdmin(),token);
//                  redisUtils.get(token.getToken());

                    return new RespBean(1,userlogin.getphoneNumber()+"msbfajshbadsmnfbasmfa"+userlogin.getpassword()+"msbfajshbadsmnfbasmfa"+userlogin.getisAdmin());


                }
            }

        }
        catch(Exception e)
        {
            logger.info("用户登录失败");
            return new RespBean(404, "失败");
        }
        return new RespBean(404, "失败");
    }


     /**
     * 以管理员身份登录
     * 对应前端的admin1Login请求
     * @param request
     * @param bindingResult
     * @return
     */
    @RequestMapping(value ="/admin1Login",method = RequestMethod.POST)
    public RespBean admin1Login(@Valid @RequestBody Map<String,Object> request, BindingResult bindingResult) {

        //新添加 - 验证码
        //String serverCode = stringRedisTemplate.opsForValue().get(loginUser.getVerificationKey());
        if (bindingResult.hasErrors()) { System.out.println(bindingResult.getFieldError().getDefaultMessage()); }
        //如果有字段验证错误,则返回错误信息

        String phoneNumber = (String) request.get("phoneNumber");
        String password = (String) request.get("password");
        int isAdmin = (int) request.get("isAdmin");

        try
        {
            /**
             * 查找是否有此用户
             */

            List<UserLogin> userlogins = userService.selectAllUserLogin();

            for (UserLogin userlogin : userlogins) {
                if (userlogin.getphoneNumber().equals(phoneNumber)  && userlogin.getpassword().equals(password) && isAdmin==1)
                {
                    logger.info("管理员登录信息如下");
                    logger.info(phoneNumber);
                    logger.info(password);
                    logger.info("isAdmin==1");
                    logger.info("管理员登录成功");

                    //token生成登录用户信息redis缓存
                    UserInfo user  = userService.selectUserInfo(userlogin.getphoneNumber());
                    String token =user.getisAdmin()+","+user.getidCard()+","+user.getpassword()+","+user.getuserName()+","+user.getemail()
                            +","+user.getphoneNumber();
//                Token token =new Token(userlogin.getUser_phone_number()+"+++"+userlogin.getUser_password());
//                String [] roles= new String[1];
//                roles[0] = "admin";
//                User user = userService.selectUserInfo(userlogin.getUser_phone_number());
//                UserInfoReturnData data = new UserInfoReturnData(roles,user.getUser_phone_number(),user.getUser_id_number(),user.getUser_real_name());

                    /**
                     * 将用户登陆信息存入token中
                     */
                   redisUtils.set(userlogin.getphoneNumber()+"msbfajshbadsmnfbasmfa"+userlogin.getpassword()+"msbfajshbadsmnfbasmfa"+userlogin.getisAdmin(),token);
//                  redisUtils.get(token.getToken());

                    return new RespBean(1,userlogin.getphoneNumber()+"msbfajshbadsmnfbasmfa"+userlogin.getisAdmin()+userlogin.getpassword());


                }
            }

        }
        catch(Exception e)
        {
            logger.info("管理员登录失败");
            return new RespBean(404, "失败");
        }
        return new RespBean(404, "失败");
    }


    /**
     * 以超管身份登录
     * 对应前端的admin2Login请求
     * @param request
     * @param bindingResult
     * @return
     */
    @RequestMapping(value ="/admin2Login",method = RequestMethod.POST)
    public RespBean admin2Login(@Valid @RequestBody Map<String,Object> request, BindingResult bindingResult) {

        //新添加 - 验证码
        //String serverCode = stringRedisTemplate.opsForValue().get(loginUser.getVerificationKey());

        if (bindingResult.hasErrors()) { System.out.println(bindingResult.getFieldError().getDefaultMessage()); }
        //如果有字段验证错误,则返回错误信息

        String phoneNumber = (String) request.get("phoneNumber");
        String password = (String) request.get("password");
        int isAdmin = (int) request.get("isAdmin");


        try
        {
            /**
             * 查找是否有此用户
             */

            List<UserLogin> userlogins = userService.selectAllUserLogin();

            for (UserLogin userlogin : userlogins) {
                if (userlogin.getphoneNumber().equals(phoneNumber)  && userlogin.getpassword().equals(password) && isAdmin==2)
                {
                    logger.info("超管登录信息如下");
                    logger.info(phoneNumber);
                    logger.info(password);
                    logger.info("isAmin==2");
                    logger.info("超管登录成功");

                    //token生成登录用户信息redis缓存
                    UserInfo user  = userService.selectUserInfo(userlogin.getphoneNumber());
                    String token =user.getisAdmin()+","+user.getidCard()+","+user.getpassword()+","+user.getuserName()+","+user.getemail()
                            +","+user.getphoneNumber();
//                Token token =new Token(userlogin.getUser_phone_number()+"+++"+userlogin.getUser_password());
//                String [] roles= new String[1];
//                roles[0] = "admin";
//                User user = userService.selectUserInfo(userlogin.getUser_phone_number());
//                UserInfoReturnData data = new UserInfoReturnData(roles,user.getUser_phone_number(),user.getUser_id_number(),user.getUser_real_name());

                    /**
                     * 将用户登陆信息存入token中
                    */
                    redisUtils.set(userlogin.getphoneNumber()+"msbfajshbadsmnfbasmfa"+userlogin.getpassword()+"msbfajshbadsmnfbasmfa"+userlogin.getisAdmin(),token);
//                  redisUtils.get(token.getToken());

                    return new RespBean(1,userlogin.getphoneNumber()+"msbfajshbadsmnfbasmfa"+userlogin.getisAdmin()+userlogin.getpassword());

                }
            }

        }
        catch(Exception e)
        {
            logger.info("超管登录失败");
            return new RespBean(404, "失败");
        }
        return new RespBean(404, "失败");
    }


    /***
     * 登录成功后，向前端返回cookie中的内容作为用户登录的标记
     * 并且将用户登陆信息存入token中
     * 对应前端的getAdminInfo请求
     * @param token
     * @return
     */
    @RequestMapping(value ="/info",method = RequestMethod.GET)
    public UserInfoReturnData GetUserInfo(@RequestParam String token) {
        try {
            String [] roles = new String[1];
            String a = redisUtils.get(token);//从Token获取当前登录的用户的信息
            String data [] = a.split(",");
            int isAdmin = Integer.parseInt(data[0]);//data[]是String,而isAdmin是int,需要先转化再使用
            return new UserInfoReturnData(1,new UserInfo(isAdmin,data[1],data[2],data[3],data[4],data[5]));
            //Data1~5依次是:idCard password userName email phoneNumber
        }
        catch (Exception e)
        {
            return new UserInfoReturnData(404,new UserInfo(0,"null","null","null","null","null"));
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

        if (bindingResult.hasErrors()) { System.out.println(bindingResult.getFieldError().getDefaultMessage()); }
        //如果有字段验证错误,则返回错误信息

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
                     * 如果phoneNumber没有重复且两次输入密码相同则注册成功
                     */
                    try {
                        UserInfo user  = new UserInfo(0,idCard,password,userName,email,phoneNumber);
                        //新注册的用户默认身份为非管理员
                        boolean flag = userService.insertUser(user);
                        if(flag)
                        {
                            logger.info("注册成功");
                            return new RespBean(1,userlogin.getphoneNumber()+"msbfajshbadsmnfbasmfa"+userlogin.getpassword()+"msbfajshbadsmnfbasmfa"+userlogin.getisAdmin());
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
     * 退出登录的接口
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
     * 查询当前登录的用户相关信息的接口
     * 对应前端的getUserInfo请求
     * @param token
     * @return
     */
    @RequestMapping(value ="/userinfo",method = RequestMethod.GET)
    public UserInfoReturnData getUserInfo( @RequestParam String token ) {
        String user = redisUtils.get(token);
        String data [] = user.split(",");
        int isAdmin=Integer.parseInt(data[0]);//这里把data[0](对应的isAdmin属性)从字符串转化为int型变量
        return new UserInfoReturnData(1,new UserInfo(isAdmin,data[1],data[2],data[3],data[4],data[5]));

    }


    /**
     * 修改当前登录用户的信息的接口
     * 对应前端的changeUserInfo请求
     * @param request
     * @param bindingResult
     * @return
     */
    @RequestMapping(value ="/changeuserinfo",method = RequestMethod.POST)
    public RespBean changeUserInfo(@Valid @RequestBody Map<String,Object> request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) { System.out.println(bindingResult.getFieldError().getDefaultMessage()); }
        //如果有字段验证错误,则返回错误信息

        String token = (String)request.get("token");
        String userName = (String)request.get("userName");
        String email = (String) request.get("email");
        String idCard = (String)request.get("idCard");
        
        logger.info(token);
        String user = redisUtils.get(token);
        String data [] = user.split(",");//这里不涉及isAdmin的修改，所以不进行String转int
        String phoneNumber = data[5];//phoneNumber用于标识用户
        logger.info(phoneNumber);

            try {
               userService.UpdateUserInfo(idCard, userName, email, phoneNumber);
                return new RespBean(1, "修改成功");
            } 
            catch (Exception e) {

                return new RespBean(403, "修改失败");
            }

    }

    /**
     * 修改当前登录用户的UserName的接口
     * 对应前端的changeUserInfo请求
     * @param request
     * @param bindingResult
     * @return
     */
    @RequestMapping(value ="/changeuserinfo",method = RequestMethod.POST)
    public RespBean changeUserName(@Valid @RequestBody Map<String,Object> request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) { System.out.println(bindingResult.getFieldError().getDefaultMessage()); }
        //如果有字段验证错误,则返回错误信息

        String token = (String)request.get("token");
        String userName = (String)request.get("userName");
        
        logger.info(token);
        String user = redisUtils.get(token);
        String data [] = user.split(",");//这里不涉及isAdmin的修改，所以不进行String转int
        String phoneNumber = data[5];//phoneNumber用于标识用户
        logger.info(phoneNumber);

            try {
               userService.UpdateUserName(userName, phoneNumber);
                return new RespBean(1, "修改成功");
            } 
            catch (Exception e) {

                return new RespBean(403, "修改失败");
            }

    }

    /**
     * 修改当前登录用户的idCard的接口
     * 对应前端的changeUserInfo请求
     * @param request
     * @param bindingResult
     * @return
     */
    @RequestMapping(value ="/changeuseridCard",method = RequestMethod.POST)
    public RespBean changeUseridCard(@Valid @RequestBody Map<String,Object> request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) { System.out.println(bindingResult.getFieldError().getDefaultMessage()); }
        //如果有字段验证错误,则返回错误信息

        String token = (String)request.get("token");
        String idCard = (String)request.get("idCard");
        
        logger.info(token);
        String user = redisUtils.get(token);
        String data [] = user.split(",");//这里不涉及isAdmin的修改，所以不进行String转int
        String phoneNumber = data[5];//phoneNumber用于标识用户
        logger.info(phoneNumber);

            try {
               userService.UpdateUseridCard(idCard, phoneNumber);
                return new RespBean(1, "修改成功");
            } 
            catch (Exception e) {

                return new RespBean(403, "修改失败");
            }

    }

    /**
     * 修改当前登录用户的email的接口
     * 对应前端的changeUserInfo请求
     * @param request
     * @param bindingResult
     * @return
     */
    @RequestMapping(value ="/changeuserEmail",method = RequestMethod.POST)
    public RespBean changeUserEmail(@Valid @RequestBody Map<String,Object> request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) { System.out.println(bindingResult.getFieldError().getDefaultMessage()); }
        //如果有字段验证错误,则返回错误信息

        String token = (String)request.get("token");
        String email = (String)request.get("email");
        
        logger.info(token);
        String user = redisUtils.get(token);
        String data [] = user.split(",");//这里不涉及isAdmin的修改，所以不进行String转int
        String phoneNumber = data[5];//phoneNumber用于标识用户
        logger.info(phoneNumber);

            try {
               userService.UpdateUserEmail(email, phoneNumber);
                return new RespBean(1, "修改成功");
            } 
            catch (Exception e) {

                return new RespBean(403, "修改失败");
            }

    }

    /**
     * 修改当前登录的用户的密码的接口
     * 对应前端的changePassword请求
     * @param request
     * @param bindingResult
     * @return
     */
    @RequestMapping(value ="/changepassword",method = RequestMethod.POST)
    public RespBean changePassword(@Valid @RequestBody Map<String,Object> request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) { System.out.println(bindingResult.getFieldError().getDefaultMessage()); }
        //如果有字段验证错误,则返回错误信息

        String token = (String)request.get("token");
        String oldpassword = (String)request.get("oldpassword");
        String newpassword = (String)request.get("newpassword");
        String user = redisUtils.get(token);
        String data [] = user.split(",");
        String phoneNumber = data[5];//phoneNumber需要统一

        UserInfo userInfo = userService.selectUserInfo(phoneNumber);
            if(userInfo.getpassword().equals(oldpassword))//修改前必须输入oldpassword
            {
                userService.UpdatePassword(newpassword,phoneNumber);
                return new RespBean(1,"密码修改成功");
            }
        return new RespBean(405,"密码修改失败");
    }


    /***
     * 获取所有用户信息(仅管理员和超管可用)
     * 对应前端的getAllUser请求
     * @param token
     * @return
     */
    @RequestMapping(value ="/getAllUser",method = RequestMethod.GET)
    public UserReturnList getAllUser(@RequestParam String token) {

        String user = redisUtils.get(token);
        String data [] = user.split(",");
        int isAdmin=Integer.parseInt(data[0]);//data[0]对应isAdmin属性，这里需要进行String转化为int的操作
        
        if(isAdmin==1 || isAdmin==2){//使用前先验证是否为管理员/超管身份
        try {
            String a = redisUtils.get(token);
            if(a != null)
            {
                List<UserInfo> userList = userService.selectAllUser();
                return  new UserReturnList(1,userList);
            }
        }
        
        catch (Exception e)
        {
            return  new UserReturnList(404,null);

        }
    }
        return  new UserReturnList(404,null);
    }



    /***
     * 修改用户权限为管理员(仅超管可用)
     * 对应前端的setAdmin请求
     * @param token
     * @return
     */
    @RequestMapping(value ="/getAllUser",method = RequestMethod.POST)
    public RespBean setAdmin( @Valid @RequestBody Map<String,Object> request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {System.out.println(bindingResult.getFieldError().getDefaultMessage()); }
        //如果有字段验证错误,则返回错误信息
        
        String token = (String) request.get("token");
        String user = redisUtils.get(token);
        String data [] = user.split(",");
        int isAdmin =Integer.parseInt(data[0]);//通过Token获取当前登录的用户的信息
        String phoneNumber = (String)request.get("phoneNumber");//需要被设置为管理员的用户(使用phoneNumber)
        
        if(isAdmin == 2)//判断当前用户是否为超管
        {
            try {userService.UpdateisAdmin(2, phoneNumber); 
                return new RespBean(1,"设置成功");
            }
            catch (Exception e)
        {
            return new RespBean(404,"设置失败");
        }
        }

        return new RespBean((404), "设置失败");
        
    }


    /***
     * 删除用户
     * 对应前端的deleteUser请求
     * (一般认为用户拥有自行注销账号的资格,所以没有设置检验idAdmin身份环节)
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
package com.wk.warehouse.controller;

import com.wk.warehouse.entity.UserLogin;
import com.wk.warehouse.entity.Result;
import com.wk.warehouse.entity.User;
import com.wk.warehouse.service.UserService;
import com.wk.warehouse.util.CurrentUser;
import com.wk.warehouse.util.DigestUtil;
import com.wk.warehouse.util.TokenUtils;
import com.wk.warehouse.util.WarehouseConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * 用户登录后台
 * @author wk
 * @version 1.0 2022/03/25
 */
@RestController // 会自动生成一个类型首字母小写的对象
@Api(tags = "用户登录")
@RequestMapping("/UserLogin")
public class UserLoginController {

	@Autowired
	private UserService userService;
	// Springboot提供的针对redis操作字符串的便捷工具对象
	private static StringRedisTemplate stringRedisTemplate;

	@Autowired
	public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) { 
		this.stringRedisTemplate = stringRedisTemplate; 
	}

	/**
	 * 用户登录
	 */
	@PostMapping("/userlogin")
	@ApiOperation(value = "普通用户登录")
	public Result userlogin(@RequestBody UserLogin userlogin) {
		// @RequestBody 解析post提交的请求体(json)，将json字符串转化为对象
		// 验证码判断
		String serverCode = stringRedisTemplate.opsForValue().get(userlogin.getverificationKey());
		if(!userlogin.getverificationCode().equals(serverCode)){
			return Result.err(Result.CODE_ERR_BUSINESS, "验证码不正确!");
		}

		// 通过phoneNumber获取当前用户
		User user = userService.findByphoneNumber(userlogin.getphoneNumber());
		if (null != user) {
			// 密码判断
			String password = DigestUtil.hmacSign(userlogin.getpassword()); // 用户输入密码
			
				if (password.equals(user.getpassword())) {
					CurrentUser currentUser = new CurrentUser(user.getisAdmin(), user.getphoneNumber(), user.getpassword());
					// 登录成功后，使用令牌工具类生成令牌（token），返回客户端
					String token = TokenUtils.loginSign(currentUser, user.getpassword());
					System.out.println(token);
					return Result.ok("登录成功!", token);
				} else {
					return Result.err(Result.CODE_ERR_BUSINESS, "密码不正确!");
				}

		}else{
			return Result.err(Result.CODE_ERR_BUSINESS, "该用户不存在!");
		}
	}

	/**
	 * 管理员登录
	 */
	@PostMapping("/admin1login")
	@ApiOperation(value = "管理员登录")
	public Result admin1login(@RequestBody UserLogin userlogin) {
		// @RequestBody 解析post提交的请求体(json)，将json字符串转化为对象
		// 验证码判断
		String serverCode = stringRedisTemplate.opsForValue().get(userlogin.getverificationKey());
		if(!userlogin.getverificationCode().equals(serverCode)){
			return Result.err(Result.CODE_ERR_BUSINESS, "验证码不正确!");
		}

		// 通过phoneNumber获取当前用户
		User user = userService.findByphoneNumber(userlogin.getphoneNumber());
		if (null != user) {//判断当前用户是否为空

			String password = DigestUtil.hmacSign(userlogin.getpassword()); // 用户输入密码
			
				if (password.equals(user.getpassword()) && (user.getisAdmin()==1)) {//判断密码与身份
					CurrentUser currentUser = new CurrentUser(user.getisAdmin(), user.getphoneNumber(), user.getpassword());
					// 登录成功后，使用令牌工具类生成令牌（token），返回客户端
					String token = TokenUtils.loginSign(currentUser, user.getpassword());
					System.out.println(token);
					return Result.ok("登录成功!", token);
				} else {
					return Result.err(Result.CODE_ERR_BUSINESS, "密码不正确!");
				}

		}else{
			return Result.err(Result.CODE_ERR_BUSINESS, "该用户不存在!");
		}
	}

	/**
	 * 超管登录————验证用户名、密码与isAdmin
	 */
	@PostMapping("/admin2login")
	@ApiOperation(value = "超管登录")
	public Result admin2login(@RequestBody UserLogin userlogin) {
		// @RequestBody 解析post提交的请求体(json)，将json字符串转化为对象
		// 验证码判断
		String serverCode = stringRedisTemplate.opsForValue().get(userlogin.getverificationKey());
		if(!userlogin.getverificationCode().equals(serverCode)){
			return Result.err(Result.CODE_ERR_BUSINESS, "验证码不正确!");
		}

		// 通过phoneNumber获取当前用户
		User user = userService.findByphoneNumber(userlogin.getphoneNumber());
		if (null != user) {//判断当前用户是否存在

			String password = DigestUtil.hmacSign(userlogin.getpassword()); // 用户输入密码
			
				if (password.equals(user.getpassword()) && (user.getisAdmin()==2)) {//判断密码与身份
					CurrentUser currentUser = new CurrentUser(user.getisAdmin(), user.getphoneNumber(), user.getpassword());
					// 登录成功后，使用令牌工具类生成令牌（token），返回客户端
					String token = TokenUtils.loginSign(currentUser, user.getpassword());
					System.out.println(token);
					return Result.ok("登录成功!", token);
				} else {
					return Result.err(Result.CODE_ERR_BUSINESS, "密码不正确!");
				}

		}else{
			return Result.err(Result.CODE_ERR_BUSINESS, "该用户不存在!");
		}
	}

	/**
	 * 根据前端发送的token解析当前用户
	 */
	@GetMapping("/curr-user")
	@ApiOperation(value = "解析当前用户")
	public Result currUser(@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String clientToken) {
		CurrentUser currentUser = TokenUtils.getCurrentUser(clientToken);
		return Result.ok(currentUser);
	}

	// 校验令牌
	@GetMapping("/token/verify")
	@ApiOperation(value = "校验令牌")
	public Result verifyToken(@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String clientToken){
		try {
			TokenUtils.verify(clientToken);
			return Result.ok();
		} catch (Exception e) {
			return Result.err(Result.CODE_ERR_UNLOGINED, "出错!");
		}
	}

	/**
	 * 用户退出
	 */
	@DeleteMapping("/logout")
	@ApiOperation(value = "退出登录")
	public Result logout(@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String clientToken) {
		// 从redis清除令牌
		TokenUtils.removeToken(clientToken);
		return Result.ok();
	}

}

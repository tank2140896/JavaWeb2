package com.javaweb.controller;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.javaweb.dataobject.eo.TokenData;
import com.javaweb.dataobject.eo.UserLogin;
import com.javaweb.dataobject.eo.UserRoleModule;
import com.javaweb.dataobject.po.User;
import com.javaweb.help.GsonHelp;
import com.javaweb.help.ResponseResult;
import com.javaweb.service.rbac.UserService;

@RestController
public class LoginController extends BaseController {
	
	@Autowired
	private DefaultKaptcha defaultKaptcha;
	
	@Autowired
	private UserService userService;
	
	/**
	@GetMapping("/kaptcha")
	public void captcha(HttpServletResponse response)throws ServletException,IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //生成文字验证码
        String text = defaultKaptcha.createText();
        //生成图片验证码
        BufferedImage image = defaultKaptcha.createImage(text);
        ShiroSession.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);//保存到shiro的session
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
	}
	*/
	
	@PostMapping("/login")
	public String login(@RequestBody UserLogin userLogin,HttpServletRequest request){
		ResponseResult responseResult = null;
		String token = null;
		try{
			//Object kaptchaValue = ShiroSession.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
			//String kaptcha = kaptchaValue==null?"":kaptchaValue.toString();
			String username = userLogin.getUsername();
			String password = userLogin.getPassword();
			if("superAdmin".equals(username)&&"superAdmin".equals(password)/*&&kaptcha.equals(kaptcha)*/){//超级管理员
				User user = new User();
				user.setLevel(1);
				user.setUserId("superAdmin");
				user.setUserName("superAdmin");
				user.setPassword(password);
				user.setPersonName("superAdmin");
				token = Base64.getEncoder().encodeToString((username+password).getBytes());
				TokenData tokenData = new TokenData();
				tokenData.setToken(token);
				tokenData.setUser(user);
				setCache(tokenData, valueOperations);
				responseResult = new ResponseResult(200,"登录成功",tokenData);
			}else{
				Map<String,String> map = new HashMap<>();
				map.put("username", username);
				map.put("password", Base64.getEncoder().encodeToString((password).getBytes()));
				User user = userService.getUserByUsernameAndPassword(map);
				if(user!=null){
					token = Base64.getEncoder().encodeToString((username+password).getBytes());
					List<UserRoleModule> list = userService.getUserRoleModule(user.getUserId());
					//获得菜单列表
					List<UserRoleModule> menuList = list.stream().filter(i->"1".equals(i.getModuletype())).collect(Collectors.toList());
					//获得操作权限列表
					List<UserRoleModule> authOperateList = list.stream().filter(i->"2".equals(i.getModuletype())).collect(Collectors.toList());
					TokenData tokenData = new TokenData();
					tokenData.setToken(token);
					tokenData.setUser(user);
					tokenData.setMenuList(menuList);
					tokenData.setAuthOperateList(authOperateList);
					setCache(tokenData, valueOperations);
					responseResult = new ResponseResult(200,"登录成功",tokenData);
				}else{
					responseResult = new ResponseResult(600,"用户名或密码错误",null);
				}
			}
		}catch(Exception e){
			responseResult = new ResponseResult(500,e.getMessage(),null);
		}
		return new GsonHelp().fromJsonDefault(responseResult);
	}
	
	@GetMapping("/unauthorized")
	public String unauthorized(){
		ResponseResult responseResult = new ResponseResult(401,"没有权限",null);
		GsonHelp gsonHelp = new GsonHelp();
		return gsonHelp.fromJsonDefault(responseResult);
	}
	
	@GetMapping("/internalServerError")
	public String internalServerError(){
		ResponseResult responseResult = new ResponseResult(500,"系统内部错误",null);
		GsonHelp gsonHelp = new GsonHelp();
		return gsonHelp.fromJsonDefault(responseResult);
	}
	
	@GetMapping("/notFound")
	public String notFound(){
		ResponseResult responseResult = new ResponseResult(404,"接口不存在",null);
		GsonHelp gsonHelp = new GsonHelp();
		return gsonHelp.fromJsonDefault(responseResult);
	}
	
	public static Map<Object,Object> tokenDataMap = new HashMap<>();
	
	private void setCache(TokenData tokenData,ValueOperations<Object,Object> valueOperations){
		try{
			valueOperations.set(tokenData.getToken(), new GsonHelp().fromJsonDefault(tokenData), 30, TimeUnit.MINUTES);
		}catch(Exception e){
			System.out.println("redis缓存设置失败，失败原因为："+e.getMessage());
			tokenDataMap.put(tokenData.getToken(), tokenData);
		}
	}
	
	public static TokenData getCache(String token,ValueOperations<Object,Object> valueOperations){
		TokenData tokenData = null;
		try{
			tokenData = (TokenData)valueOperations.get(token);
		}catch(Exception e){
			tokenData = (TokenData)tokenDataMap.get(token);
		}
		return tokenData;
	}
	
}
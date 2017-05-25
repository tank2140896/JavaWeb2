package com.javaweb.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.base.BaseController;
import com.javaweb.constant.SystemConstant;
import com.javaweb.dataobject.eo.CheckData;
import com.javaweb.dataobject.eo.TokenData;
import com.javaweb.dataobject.eo.UserLogin;
import com.javaweb.dataobject.eo.UserRoleModule;
import com.javaweb.dataobject.po.User;
import com.javaweb.help.GsonHelp;
import com.javaweb.help.ResponseResult;
import com.javaweb.service.rbac.UserService;

@RestController
public class LoginController extends BaseController {
	
	/**
	@Autowired
	private DefaultKaptcha defaultKaptcha;
	
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
	
	@Autowired
	private UserService userService;
	
	//用户登录接口
	@PostMapping("/web/login")
	public String login(@RequestBody UserLogin userLogin){
		ResponseResult responseResult = null;
		String token = null;
		try{
			//Object kaptchaValue = ShiroSession.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
			//String kaptcha = kaptchaValue==null?"":kaptchaValue.toString();
			String userName = userLogin.getUserName();
			String password = userLogin.getPassword();
			/*
			 1、如果要求同一用户只能有一个登录（一个登录后把另一个踢掉）：校验时需要判断header里的uniqueValue和缓存中tokenData里的uniqueValue是否一致
			 2、如果同一用户无法同时登录（一个登录后另一个无法再登录）：登录时需要判断该用户是否在缓存中
			 当然，更加推荐websocket处理（后期将补充）
			 */
			if(SystemConstant.SYSTEM_ADMIN_USERNAME.equals(userName)&&SystemConstant.SYSTEM_ADMIN_PASSWORD.equals(password)/*&&kaptcha.equals(kaptcha)*/){//超级管理员
				User user = getSystemAdmin();
				token = Base64.getEncoder().encodeToString((userName+password).getBytes());
				TokenData tokenData = getUserRoleModule(user, token, 1);
				setCache(tokenData, valueOperations);
				responseResult = new ResponseResult(SystemConstant.SUCCESS_CODE,"登录成功",tokenData);
			}else{
				Map<String,String> map = new HashMap<>();
				map.put("userName", userName);
				map.put("password", Base64.getEncoder().encodeToString((password).getBytes()));
				User user = userService.getUserByUsernameAndPassword(map);
				if(user!=null){
					token = Base64.getEncoder().encodeToString((userName+password).getBytes());
					TokenData tokenData = getUserRoleModule(user, token, 0);
					setCache(tokenData, valueOperations);
					responseResult = new ResponseResult(SystemConstant.SUCCESS_CODE,"登录成功",tokenData);
				}else{
					responseResult = new ResponseResult(SystemConstant.INTERNAL_ERROR_CODE,"用户名或密码错误",null);
				}
			}
		}catch(Exception e){
			responseResult = new ResponseResult(SystemConstant.INTERNAL_ERROR_CODE,e.getMessage(),null);
		}
		return new GsonHelp().fromJsonDefault(responseResult);
	}
	
	//用户退出接口
	@PostMapping("/web/logout")
	public String logout(HttpServletRequest request){
		ResponseResult responseResult = null;
		CheckData checkData = check(request);
		if(checkData.isCheckFlag()){
			String userId = checkData.getTokenData().getUser().getUserId();
			try{
				redisTemplate.delete(userId);
			}catch(Exception e){
				System.out.println("redis缓存设置失败，失败原因为："+e.getMessage());
				tokenDataMap.remove(userId);
			}
			responseResult = new ResponseResult(SystemConstant.SUCCESS_CODE,"退出成功",null);
		}else{
			responseResult = checkData.getResponseResult();
		}
		return new GsonHelp().fromJsonDefault(responseResult);
	}
	
	//获得系统管理员信息
	private User getSystemAdmin(){
		User user = new User();
		user.setUserId(SystemConstant.SYSTEM_ADMIN_USERID);
		user.setLevel(1);
		user.setUserName(SystemConstant.SYSTEM_ADMIN_USERNAME);
		user.setPassword(SystemConstant.SYSTEM_ADMIN_PASSWORD);
		return user;
	}
	
	//获得权限操作列表
	private TokenData getUserRoleModule(User user,String token,Integer adminFlag){
		Map<String,Object> map = new HashMap<>();
		map.put("adminFlag", adminFlag);
		map.put("userId", user.getUserId());
		List<UserRoleModule> list = userService.getUserRoleModule(map);
		//获得菜单列表
		List<UserRoleModule> menuList = list.stream().filter(i->"1".equals(i.getModuleType())).collect(Collectors.toList());
		menuList = setTreeList(menuList, null);
		//获得操作权限列表
		List<UserRoleModule> authOperateList = list.stream().filter(i->"2".equals(i.getModuleType())).collect(Collectors.toList());
		TokenData tokenData = new TokenData();
		tokenData.setToken(token);
		tokenData.setUser(user);
		tokenData.setUniqueValue(UUID.randomUUID().toString());
		tokenData.setModuleList(list);
		tokenData.setMenuList(menuList);
		tokenData.setAuthOperateList(authOperateList);
		return tokenData;
	}
	
	//封装成树形结构集合
	private List<UserRoleModule> setTreeList(List<UserRoleModule> originList,UserRoleModule module){
		List<UserRoleModule> moduleList = new ArrayList<>();
		for (int i = 0; i < originList.size(); i++) {
			UserRoleModule currentModule = originList.get(i);
			if((module!=null&&module.getModuleId().equals(currentModule.getParentId()))||(module==null&&currentModule.getParentId()==null)){
				currentModule.setList(setTreeList(originList, currentModule));
				moduleList.add(currentModule);
			}
		}
		return moduleList;
	}
	
	//没有权限
	@GetMapping("/unauthorized")
	public String unauthorized(){
		ResponseResult responseResult = new ResponseResult(SystemConstant.NO_AUTHORY_CODE,"没有权限",null);
		GsonHelp gsonHelp = new GsonHelp();
		return gsonHelp.fromJsonDefault(responseResult);
	}
	
	//系统内部错误
	@GetMapping("/internalServerError")
	public String internalServerError(){
		ResponseResult responseResult = new ResponseResult(SystemConstant.INTERNAL_ERROR_CODE,"系统内部错误",null);
		GsonHelp gsonHelp = new GsonHelp();
		return gsonHelp.fromJsonDefault(responseResult);
	}
	
	//接口不存在
	@GetMapping("/notFound")
	public String notFound(){
		ResponseResult responseResult = new ResponseResult(SystemConstant.NOT_FOUND_CODE,"接口不存在",null);
		GsonHelp gsonHelp = new GsonHelp();
		return gsonHelp.fromJsonDefault(responseResult);
	}
	
	//tokenDataMap是在没有缓存情况下的临时处理办法（仅仅看看项目演示效果），正式项目绝对不要使用
	private static Map<Object,Object> tokenDataMap = new HashMap<>();
	
	//设置缓存数据
	public static void setCache(TokenData tokenData,ValueOperations<Object,Object> valueOperations){
		//这里的key值设定就要看情况了，如果业务要求同一账号在页面端和手机端（原生）都能登录，那么key值可能还需要一个type来联合组成
		String userId = tokenData.getUser().getUserId();
		try{
			valueOperations.set(userId, tokenData, 30, TimeUnit.MINUTES);
		}catch(Exception e){
			System.out.println("redis缓存设置失败，失败原因为："+e.getMessage());
			tokenDataMap.put(userId, tokenData);
		}
	}
	
	//获得缓存数据
	public static TokenData getCache(String userId,ValueOperations<Object,Object> valueOperations){
		TokenData tokenData = null;
		try{
			tokenData = (TokenData)valueOperations.get(userId);
		}catch(Exception e){
			tokenData = (TokenData)tokenDataMap.get(userId);
		}
		return tokenData;
	}
	
}

package com.javaweb.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.base.BaseController;
import com.javaweb.constant.SystemConstant;
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
	@PostMapping("/login")
	public String login(@RequestBody UserLogin userLogin,HttpServletRequest request){
		ResponseResult responseResult = null;
		try{
			//Object kaptchaValue = ShiroSession.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
			//String kaptcha = kaptchaValue==null?"":kaptchaValue.toString();
			String userName = userLogin.getUserName();//得到用户名 
		    String password = userLogin.getPassword();//得到密码
		    User user = null;
		    Integer adminFlag = 0;
		    if(SystemConstant.SYSTEM_ADMIN_USERNAME.equals(userName)&&SystemConstant.SYSTEM_ADMIN_PASSWORD.equals(password)){
		    	user = new User();
				user.setUserId(SystemConstant.SYSTEM_ADMIN_USERID);
				user.setLevel(1);
				user.setUserName(SystemConstant.SYSTEM_ADMIN_USERNAME);
				user.setPassword(Base64.getEncoder().encodeToString(SystemConstant.SYSTEM_ADMIN_PASSWORD.getBytes()));
				adminFlag = 1;
		    }else{
		    	password = Base64.getEncoder().encodeToString(password.getBytes());
		    	Map<String,String> map = new HashMap<>();
		    	map.put("userName", userName);
		    	map.put("password", password);
		    	user = userService.getUserByUsernameAndPassword(map);
		    	adminFlag = 0;
		    }
		    if(user == null) {
		    	responseResult = new ResponseResult(SystemConstant.INTERNAL_ERROR_CODE,"用户名或密码错误",null);
		    }else{
		    	TokenData tokenData = getUserRoleModule(user, adminFlag);
		    	setSessionAttribute(request, SystemConstant.SESSION_KEY, tokenData);
		    	responseResult = new ResponseResult(SystemConstant.SUCCESS_CODE,"登录成功",tokenData);
		    }
		}catch(Exception e){
			responseResult = new ResponseResult(SystemConstant.INTERNAL_ERROR_CODE,e.getMessage(),null);
		}
		return new GsonHelp().fromJsonDefault(responseResult);
	}
	
	//用户退出接口
	@GetMapping("/logout")
	public String logout(HttpServletRequest request){
		ResponseResult responseResult = null;
		try{
			//TODO
			responseResult = new ResponseResult(SystemConstant.SUCCESS_CODE,"退出成功",null);
		}catch(Exception e){
			responseResult = new ResponseResult(SystemConstant.INTERNAL_ERROR_CODE,e.getMessage(),null);
		}
		return new GsonHelp().fromJsonDefault(responseResult);
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
	
	//获得权限操作列表
	private TokenData getUserRoleModule(User user,Integer adminFlag){
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
		tokenData.setUser(user);
		tokenData.setModuleList(list);
		tokenData.setMenuList(menuList);
		tokenData.setAuthOperateList(authOperateList);
		return tokenData;
	}
	
	//封装成树形结构集合
	private static List<UserRoleModule> setTreeList(List<UserRoleModule> originList,UserRoleModule module){
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
	
}

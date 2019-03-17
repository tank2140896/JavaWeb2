package com.javaweb.web.controller;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.constant.CommonConstant;
import com.javaweb.constant.HttpCodeEnum;
import com.javaweb.constant.SystemConstant;
import com.javaweb.util.core.DateUtil;
import com.javaweb.web.eo.TokenData;
import com.javaweb.web.eo.user.UserLoginRequest;
import com.javaweb.web.po.Module;
import com.javaweb.web.po.User;

@RestController
public class AllOpenController extends BaseController {
	
	//用户登录接口
	@PostMapping("/login")
	public BaseResponseResult login(@RequestBody @Validated UserLoginRequest userLogin,BindingResult bindingResult,HttpServletRequest request){
		if(bindingResult.hasErrors()){
			return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,bindingResult,CommonConstant.EMPTY_VALUE);
		}
		//验证码校验
		//if(kaptchaCheck(userLogin,request)){
		//	return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,"login.user.kaptcha",CommonConstant.EMPTY_VALUE);
		//}
		if((SystemConstant.SYSTEM_DEFAULT_USER_NAME+SystemConstant.SYSTEM_DEFAULT_USER_PASSWORD).equals(userLogin.getUsername()+userLogin.getPassword())){
			User user = SystemConstant.SYSTEM_DEFAULT_USER;
			TokenData token = getToken(true,user,userLogin.getType());
			String key = String.join(CommonConstant.COMMA,user.getUserId(),userLogin.getType(),secretToken(token.getToken()));
			setDefaultDataToRedis(key,token);//request.getSession().setAttribute(user.getUserId(),token);
			return getBaseResponseResult(HttpCodeEnum.SUCCESS,"login.user.loginSuccess",token);
		}
		User user = userService.userLogin(userLogin);
		if(user==null){
			return getBaseResponseResult(HttpCodeEnum.LOGIN_FAIL,"login.user.userNameOrPassword",CommonConstant.EMPTY_VALUE);
		}
		TokenData token = getToken(false,user,userLogin.getType());
		String key = String.join(CommonConstant.COMMA,user.getUserId(),userLogin.getType(),secretToken(token.getToken()));
		setDefaultDataToRedis(key,token);//request.getSession().setAttribute(user.getUserId(),token);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"login.user.loginSuccess",token);
	}
	
	@RequestMapping(value="/requestParameterLost",method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
	public BaseResponseResult requestParameterLost() {
		return getBaseResponseResult(HttpCodeEnum.REQUEST_PARAMETER_LOST,"validated.permission.requestParameterLost",CommonConstant.EMPTY_VALUE);
	}
	
	@RequestMapping(value="/invalidRequest",method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
	public BaseResponseResult invalidRequest(){
		return getBaseResponseResult(HttpCodeEnum.INVALID_REQUEST,"validated.permission.invalidRequest",CommonConstant.EMPTY_VALUE);
	}
	
	@RequestMapping(value="/requestParameterError",method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
	public BaseResponseResult requestParameterError(){
		return getBaseResponseResult(HttpCodeEnum.INVALID_REQUEST,"validated.permission.requestParameterError",CommonConstant.EMPTY_VALUE);
	}
	
	@RequestMapping(value="/noAuthory",method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
	public BaseResponseResult noAuthory(){
		return getBaseResponseResult(HttpCodeEnum.NO_AUTHORY,"validated.permission.noAuthory",CommonConstant.EMPTY_VALUE);
	}
	
	//验证码
	@GetMapping("/kaptcha/{uuid}")
	public void kaptcha(HttpServletRequest request,HttpServletResponse response,@PathVariable(name="uuid",required=true) String uuid) throws Exception {
		response.setHeader("Cache-Control", "no-store, no-cache");
	    response.setContentType("image/jpeg");
	    String text = defaultKaptcha.createText();
	    String sessionId = request.getSession().getId();
	    if(sessionId==null){
	    	sessionId = uuid;
	    }
	    setDataToRedis(sessionId,text,SystemConstant.SYSTEM_DEFAULT_KAPTCHA_TIME_OUT,TimeUnit.MINUTES);
	    BufferedImage image = defaultKaptcha.createImage(text);
	    ServletOutputStream out = response.getOutputStream();
	    ImageIO.write(image,"jpg",out);
	}
	
	//token数据封装
	protected TokenData getToken(boolean adminFlag,User user,String type){
		TokenData tokenData = new TokenData();
		Map<String,Object> map = new HashMap<>();
		map.put("adminFlag", adminFlag);
		map.put("userId", user.getUserId());
		List<Module> list = moduleService.getUserRoleModule(map);
		list=(list==null?new ArrayList<>():list);
		List<Module> menuList = list.stream().filter(i->1==i.getModuleType()).collect(Collectors.toList());//获得菜单列表
		List<Module> authOperateList = list.stream().filter(i->2==i.getModuleType()).collect(Collectors.toList());//获得操作权限列表
		tokenData.setToken(UUID.randomUUID().toString());
		tokenData.setUser(user);
		tokenData.setType(type);
		tokenData.setMenuList((menuList==null||menuList.size()==0)?null:menuList);
		tokenData.setModuleList((list==null||list.size()==0)?null:list);
		tokenData.setAuthOperateList((authOperateList==null||authOperateList.size()==0)?null:authOperateList);
		menuList = setTreeList(menuList,null);
		tokenData.setMenuListForTree((menuList==null||menuList.size()==0)?null:menuList);
		return tokenData;
	}
	
	//对token进行加密
	protected String secretToken(String token) {
		String date = DateUtil.getStringDate(DateUtil.DATETIME_PATTERN_TYPE2);
		String tempArray[] = new String[token.length()];
		for(int i=0;i<tempArray.length;i++){
			tempArray[i] = String.valueOf(token.charAt(i));
		}
		for(int i=0;i<date.length();i++){
			String str = String.valueOf(date.charAt(i));//获得日期字符串的每位数字
			int m = i;
			int n = Integer.parseInt(str);
			str = tempArray[m];
			tempArray[m] = tempArray[n];
			tempArray[n] = str;
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<tempArray.length;i++){
			sb.append(tempArray[i]);
		}
		String out = sb.toString();
		out = out.replaceAll(CommonConstant.BAR,CommonConstant.EMPTY_VALUE);
		out = out.toUpperCase();
		return out;
	}
	
	//封装成树形结构集合
	protected List<Module> setTreeList(List<Module> originList,Module module){
		List<Module> moduleList = new ArrayList<>();
		for (int i = 0; i < originList.size(); i++) {
			Module currentModule = originList.get(i);
			if((module!=null&&module.getModuleId().equals(currentModule.getParentId()))||(module==null&&currentModule.getParentId()==null)){
				currentModule.setList(setTreeList(originList, currentModule));
				moduleList.add(currentModule);
			}
		}
		return moduleList;
	}
	
	//验证码校验
	protected boolean kaptchaCheck(UserLoginRequest userLogin,HttpServletRequest request){
		boolean result = true;
		String sessionId = request.getSession().getId();
	    if(sessionId==null){
	    	sessionId = userLogin.getUuid();
	    }
		String kaptcha = (String)getDateFromRedis(sessionId);
		if(kaptcha!=null){
			if(kaptcha.equalsIgnoreCase(userLogin.getKaptcha())){//忽略大小写
				result = false;
			}
		}
		return result;
	}
	
}
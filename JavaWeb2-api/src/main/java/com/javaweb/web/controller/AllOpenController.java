package com.javaweb.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.constant.CommonConstant;
import com.javaweb.constant.SystemConstant;
import com.javaweb.web.eo.TokenData;
import com.javaweb.web.eo.user.UserLoginRequest;
import com.javaweb.web.po.Module;
import com.javaweb.web.po.User;
import com.javaweb.web.service.ModuleService;
import com.javaweb.web.service.UserService;

@RestController
public class AllOpenController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModuleService moduleService;
	
	//用户登录接口
	@PostMapping("/login")
	public BaseResponseResult login(@RequestBody @Validated/*({BaseValidatedGroup.add.class})*/ UserLoginRequest userLogin,BindingResult bindingResult){
		BaseResponseResult baseResponseResult = new BaseResponseResult();
		if(bindingResult.hasErrors()){
			baseResponseResult = new BaseResponseResult(SystemConstant.VALIDATE_ERROR,getValidateMessage(bindingResult),CommonConstant.EMPTY_VALUE);
		}else{
			//超级管理员(后门)
			if(SystemConstant.SYSTEM_DEFAULT_USER_NAME.equals(userLogin.getUsername())&&SystemConstant.SYSTEM_DEFAULT_USER_PASSWORD.equals(userLogin.getPassword())){
				User user = SystemConstant.SYSTEM_DEFAULT_USER;
				TokenData token = getToken(true,user);
				setDefaultDataToRedis(user.getUserId(),token);
				baseResponseResult = new BaseResponseResult(SystemConstant.SUCCESS,getMessage("login.User.loginSuccess"),token);
			}else{//非超级管理员
				User user = userService.userLogin(userLogin);
				if(user==null){
					baseResponseResult = new BaseResponseResult(SystemConstant.LOGIN_FAIL,getMessage("login.User.userNameOrPassword"),CommonConstant.EMPTY_VALUE);
				}else{
					TokenData token = getToken(false,user);
					setDefaultDataToRedis(user.getUserId(),token);
					baseResponseResult = new BaseResponseResult(SystemConstant.SUCCESS,getMessage("login.User.loginSuccess"),token);
				}
			}
		}
		return baseResponseResult;
	}
				  
	@RequestMapping(value="/requestParameterLost",method={RequestMethod.GET,RequestMethod.POST})
	public BaseResponseResult requestParameterLost(){
		return new BaseResponseResult(SystemConstant.REQUEST_PARAMETER_LOST,getMessage("validated.permission.requestParameterLost"),CommonConstant.EMPTY_VALUE);
	}
	
	@RequestMapping(value="/invalidRequest",method={RequestMethod.GET,RequestMethod.POST})
	public BaseResponseResult invalidRequest(){
		return new BaseResponseResult(SystemConstant.INVALID_REQUEST,getMessage("validated.permission.invalidRequest"),CommonConstant.EMPTY_VALUE);
	}
	
	@RequestMapping(value="/requestParameterError",method={RequestMethod.GET,RequestMethod.POST})
	public BaseResponseResult requestParameterError(){
		return new BaseResponseResult(SystemConstant.INVALID_REQUEST,getMessage("validated.permission.requestParameterError"),CommonConstant.EMPTY_VALUE);
	}
	
	@RequestMapping(value="/noAuthory",method={RequestMethod.GET,RequestMethod.POST})
	public BaseResponseResult noAuthory(){
		return new BaseResponseResult(SystemConstant.NO_AUTHORY,getMessage("validated.permission.noAuthory"),CommonConstant.EMPTY_VALUE);
	}
	
	private TokenData getToken(Boolean adminFlag,User user){
		Map<String,Object> map = new HashMap<>();
		map.put("adminFlag", adminFlag);
		map.put("userId", user.getUserId());
		List<Module> list = moduleService.getUserRoleModule(map);
		//获得菜单列表
		List<Module> menuList = list.stream().filter(i->1==i.getModuleType()).collect(Collectors.toList());
		menuList = setTreeList(menuList, null);
		//获得操作权限列表
		List<Module> authOperateList = list.stream().filter(i->2==i.getModuleType()).collect(Collectors.toList());
		TokenData tokenData = new TokenData();
		tokenData.setToken(UUID.randomUUID().toString());
		tokenData.setUser(user);
		tokenData.setModuleList(list);
		tokenData.setMenuList(menuList);
		tokenData.setAuthOperateList(authOperateList);
		return tokenData;
	}
	
	//封装成树形结构集合
	private List<Module> setTreeList(List<Module> originList,Module module){
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
	
}
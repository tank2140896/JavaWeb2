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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.constant.CommonConstant;
import com.javaweb.constant.SystemConstant;
import com.javaweb.web.eo.TokenData;
import com.javaweb.web.eo.UserLogin;
import com.javaweb.web.po.Module;
import com.javaweb.web.po.User;
import com.javaweb.web.service.UserService;

@RestController
public class AllOpenController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	//http://localhost:8888/javaweb-web/abc
	//http://localhost:4200/#/
	
	@GetMapping("/abc")
	public String a(){
		//setDefaultDataToRedis("a","12");
		return "9999";
	}

	//用户登录接口
	@PostMapping("/web/login")
	public BaseResponseResult login(@RequestBody @Validated/*({BaseValidatedGroup.add.class})*/ UserLogin userLogin,
							        BindingResult bindingResult){
		BaseResponseResult baseResponseResult = new BaseResponseResult();
		try{
			if(bindingResult.hasErrors()){
				baseResponseResult = new BaseResponseResult(SystemConstant.VALIDATE_ERROR_CODE,getValidateMessage(bindingResult),CommonConstant.EMPTY_VALUE);
			}else{
				//超级管理员(后门)
				if(SystemConstant.SYSTEM_DEFAULT_USER_NAME.equals(userLogin.getUserName())&&SystemConstant.SYSTEM_DEFAULT_USER_PASSWORD.equals(userLogin.getPassword())){
					User user = SystemConstant.SYSTEM_DEFAULT_USER;
					TokenData token = getToken(true,user);
					setDefaultDataToRedis(user.getUserId(),token);
					baseResponseResult = new BaseResponseResult(SystemConstant.SUCCESS_CODE,getMessage("login.User.loginSuccess"),token);
				}else{//非超级管理员
					User user = userService.userLogin(userLogin);
					if(user==null){
						baseResponseResult = new BaseResponseResult(SystemConstant.LOGIN_FAIL_CODE,getMessage("login.User.userNameOrPassword"),CommonConstant.EMPTY_VALUE);
					}else{
						TokenData token = getToken(false,user);
						setDefaultDataToRedis(user.getUserId(),token);
						baseResponseResult = new BaseResponseResult(SystemConstant.SUCCESS_CODE,getMessage("login.User.loginSuccess"),token);
					}
				}
			}
		}catch(Exception e){
			baseResponseResult = new BaseResponseResult(SystemConstant.INTERNAL_ERROR_CODE,getMessage("system.error"),CommonConstant.EMPTY_VALUE);
		}
		return baseResponseResult;
	}
	
	private TokenData getToken(Boolean adminFlag,User user){
		Map<String,Object> map = new HashMap<>();
		map.put("adminFlag", adminFlag);
		map.put("userId", user.getUserId());
		List<Module> list = userService.getUserRoleModule(map);
		//获得菜单列表
		List<Module> menuList = list.stream().filter(i->"1".equals(i.getModuleType())).collect(Collectors.toList());
		menuList = setTreeList(menuList, null);
		//获得操作权限列表
		List<Module> authOperateList = list.stream().filter(i->"2".equals(i.getModuleType())).collect(Collectors.toList());
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
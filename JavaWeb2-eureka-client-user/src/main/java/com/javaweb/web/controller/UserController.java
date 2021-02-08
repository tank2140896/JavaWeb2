package com.javaweb.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaweb.annotation.token.TokenDataAnnotation;
import com.javaweb.annotation.url.ControllerMethod;
import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.base.BaseValidatedGroup;
import com.javaweb.constant.ApiConstant;
import com.javaweb.constant.SystemConstant;
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.util.core.DateUtil;
import com.javaweb.util.core.SecretUtil;
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.TokenData;
import com.javaweb.web.eo.role.ModuleInfoResponse;
import com.javaweb.web.eo.role.RoleIdAndStrategyRequest;
import com.javaweb.web.eo.user.RoleInfoResponse;
import com.javaweb.web.eo.user.UserListRequest;
import com.javaweb.web.eo.user.UserListResponse;
import com.javaweb.web.po.User;

//登录且需要权限才可访问的用户管理模块
@RestController
@RequestMapping(ApiConstant.WEB_USER_PREFIX)
public class UserController extends BaseController {

	@PostMapping(ApiConstant.USER_ADD)
	@ControllerMethod(interfaceName="新增用户接口")
	public BaseResponseResult userAdd(@RequestBody @Validated({BaseValidatedGroup.add.class}) User user,BindingResult bindingResult,@TokenDataAnnotation TokenData tokenData){
		if(bindingResult.hasErrors()){
			return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,bindingResult);
		}else{
			User currentUser = tokenData.getUser();
			user.setUserId(SecretUtil.defaultGenUniqueStr(SystemConstant.SYSTEM_NO));
			try{user.setPassword(SecretUtil.getSecret(user.getPassword(),"SHA-256"));}catch(Exception e){}
			user.setParentId(currentUser.getUserId());
			user.setLevel(currentUser.getLevel()+1);//数字越大用户级别越低，这里默认新创建的用户都比创建它的用户低一级（数字是加1）
			user.setCreateDate(DateUtil.getDefaultDate());
			user.setCreator(currentUser.getUserId());
			userService.userAdd(user);
			return getBaseResponseResult(HttpCodeEnum.SUCCESS,"user.add.success",user.getUserId());
		}
	}
	
	@PostMapping(ApiConstant.USER_LIST)
	@ControllerMethod(interfaceName="查询用户接口",dataPermissionEntity=UserListResponse.class)
	public BaseResponseResult userList(@RequestBody UserListRequest userListRequest,@TokenDataAnnotation TokenData tokenData){
		Page page = userService.userList(userListRequest);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"user.list.success",page);
	}
	
	@PutMapping(ApiConstant.USER_MODIFY)
	@ControllerMethod(interfaceName="修改用户接口")
	public BaseResponseResult userModify(@RequestBody @Validated({BaseValidatedGroup.update.class}) User user,BindingResult bindingResult,@TokenDataAnnotation TokenData tokenData){
		if(bindingResult.hasErrors()){
			return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,bindingResult);
		}else{
			User currentUser = tokenData.getUser();
			user.setPassword(null);//密码不在此处修改
			user.setUpdateDate(DateUtil.getDefaultDate());
			user.setUpdater(currentUser.getUserId());
			userService.userModify(user);
			return getBaseResponseResult(HttpCodeEnum.SUCCESS,"user.modify.success",user.getUserId());
		}
	}
	
	@GetMapping(ApiConstant.USER_DETAIL)
	@ControllerMethod(interfaceName="用户详情接口")
	public BaseResponseResult userDetail(@PathVariable(name="userId",required=true) String userId){
		User user = userService.userDetail(userId);
		if(user!=null){
			user.setPassword(null);
		}
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"user.detail.success",user);
	}
	
	@DeleteMapping(ApiConstant.USER_DELETE)
	@ControllerMethod(interfaceName="删除用户接口（支持批量删除，用逗号隔开）")
	public BaseResponseResult userDelete(@PathVariable(name="userId",required=true) String userId){
		userService.userDelete(userId);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"user.delete.success",null);
	}
	
	//ApiConstant.USER_ROLE_INFO：获取指定用户的角色信息
	//ApiConstant.USER_ROLE_INFO2：获取所有角色信息
	@GetMapping(value={ApiConstant.USER_ROLE_INFO,ApiConstant.USER_ROLE_INFO2})
	@ControllerMethod(interfaceName="用户角色信息接口")
	public BaseResponseResult userRoleInfo(@PathVariable(name="userId",required=false) String userId){
		List<RoleInfoResponse> list = userService.userRoleInfo(userId);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"user.userRoleInfo.success",list);
	}

	@PostMapping(ApiConstant.USER_ROLE_ASSIGNMENT)
	@ControllerMethod(interfaceName="用户角色分配接口")
	public BaseResponseResult userRoleAssignment(@RequestBody List<RoleIdAndStrategyRequest> list,@PathVariable(name="userId",required=true) String userId){
		userService.userRoleAssignment(userId,list);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"user.userRoleAssignment.success",null);
	}
	
	//ApiConstant.USER_MODULE_INFO：获取指定用户的模块信息
	//ApiConstant.USER_ROLE_INFO2：获取所有模块信息
	@GetMapping(value={ApiConstant.USER_MODULE_INFO,ApiConstant.USER_MODULE_INFO2})
	@ControllerMethod(interfaceName="用户模块信息接口")
	public BaseResponseResult userModuleInfo(@PathVariable(name="userId",required=false) String userId){
		List<ModuleInfoResponse> list = userService.userModuleInfo(userId);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"user.userModuleInfo.success",list);
	}
	
	@PostMapping(ApiConstant.USER_MODULE_ASSIGNMENT)
	@ControllerMethod(interfaceName="用户模块分配接口")
	public BaseResponseResult userModuleAssignment(@RequestBody List<String> list,@PathVariable(name="userId",required=true) String userId){
		userService.userModuleAssignment(userId,list);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"user.userModuleAssignment.success",null);
	}
	
	@GetMapping(ApiConstant.USER_INIT_PASSWORD)
	@ControllerMethod(interfaceName="初始化密码接口")
	public BaseResponseResult userInitPassword(@PathVariable(name="userId",required=true) String userId,@TokenDataAnnotation TokenData tokenData){
		userService.userInitPassword(userId,tokenData);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"user.init.password.success",null);
	}
	
	@PostMapping(ApiConstant.USER_PORTRAIT_UPLOAD)
	@ControllerMethod(interfaceName="用户头像上传接口")
	public BaseResponseResult userPortraitUpload(@TokenDataAnnotation TokenData tokenData,@PathVariable(name="userId",required=true) String userId,@RequestParam(value="userPortraitFile") MultipartFile multipartFile){
		userService.userPortraitUpload(userId,multipartFile);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"user.portrait.upload.success",null);
	}
	
	@GetMapping(ApiConstant.USER_USER_PORTRAIT)
	@ControllerMethod(interfaceName="获取用户头像接口")
	public void userPortrait(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
		userService.userPortrait(httpServletRequest.getParameter(SystemConstant.HEAD_USERID),httpServletResponse);
	}
	
}

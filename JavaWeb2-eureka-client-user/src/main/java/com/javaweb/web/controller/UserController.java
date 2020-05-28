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
import com.javaweb.annotation.url.DataPermission;
import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.base.BaseValidatedGroup;
import com.javaweb.constant.ApiConstant;
import com.javaweb.constant.SwaggerConstant;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags=SwaggerConstant.SWAGGER_USER_CONTROLLER_TAGS)
@RestController
@RequestMapping(ApiConstant.WEB_USER_PREFIX)
public class UserController extends BaseController {

	@ApiOperation(value=SwaggerConstant.SWAGGER_USER_ADD)
	@PostMapping(ApiConstant.USER_ADD)
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
	
	@DataPermission(entity=UserListResponse.class)
	@ApiOperation(value=SwaggerConstant.SWAGGER_USER_LIST)
	@PostMapping(ApiConstant.USER_LIST)
	public BaseResponseResult userList(@RequestBody UserListRequest userListRequest,@TokenDataAnnotation TokenData tokenData){
		Page page = userService.userList(userListRequest);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"user.list.success",page);
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_USER_MODIFY)
	@PutMapping(ApiConstant.USER_MODIFY)
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
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_USER_DETAIL)
	@GetMapping(ApiConstant.USER_DETAIL)
	public BaseResponseResult userDetail(@PathVariable(name="userId",required=true) String userId){
		User user = userService.userDetail(userId);
		if(user!=null){
			user.setPassword(null);
		}
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"user.detail.success",user);
	}
	
    //支持批量删除，用逗号隔开
	@ApiOperation(value=SwaggerConstant.SWAGGER_USER_DELETE)
	@DeleteMapping(ApiConstant.USER_DELETE)
	public BaseResponseResult userDelete(@PathVariable(name="userId",required=true) String userId){
		userService.userDelete(userId);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"user.delete.success",null);
	}
	
	//ApiConstant.USER_ROLE_INFO：获取指定用户的角色信息
	//ApiConstant.USER_ROLE_INFO2：获取所有角色信息
	@ApiOperation(value=SwaggerConstant.SWAGGER_USER_ROLE_INFO)
	@GetMapping(value={ApiConstant.USER_ROLE_INFO,ApiConstant.USER_ROLE_INFO2})
	public BaseResponseResult userRoleInfo(@PathVariable(name="userId",required=false) String userId){
		List<RoleInfoResponse> list = userService.userRoleInfo(userId);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"user.userRoleInfo.success",list);
	}

	@ApiOperation(value=SwaggerConstant.SWAGGER_USER_ROLE_ASSIGNMENT)
	@PostMapping(ApiConstant.USER_ROLE_ASSIGNMENT)
	public BaseResponseResult userRoleAssignment(@RequestBody List<RoleIdAndStrategyRequest> list,@PathVariable(name="userId",required=true) String userId){
		userService.userRoleAssignment(userId,list);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"user.userRoleAssignment.success",null);
	}
	
	//ApiConstant.USER_MODULE_INFO：获取指定用户的模块信息
	//ApiConstant.USER_ROLE_INFO2：获取所有模块信息
	@ApiOperation(value=SwaggerConstant.SWAGGER_USER_MODULE_INFO)
	@GetMapping(value={ApiConstant.USER_MODULE_INFO,ApiConstant.USER_MODULE_INFO2})
	public BaseResponseResult userModuleInfo(@PathVariable(name="userId",required=false) String userId){
		List<ModuleInfoResponse> list = userService.userModuleInfo(userId);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"user.userModuleInfo.success",list);
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_USER_MODULE_ASSIGNMENT)
	@PostMapping(ApiConstant.USER_MODULE_ASSIGNMENT)
	public BaseResponseResult userModuleAssignment(@RequestBody List<String> list,@PathVariable(name="userId",required=true) String userId){
		userService.userModuleAssignment(userId,list);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"user.userModuleAssignment.success",null);
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_USER_INIT_PASSWORD)
	@GetMapping(ApiConstant.USER_INIT_PASSWORD)
	public BaseResponseResult userInitPassword(@PathVariable(name="userId",required=true) String userId,@TokenDataAnnotation TokenData tokenData){
		userService.userInitPassword(userId,tokenData);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"user.init.password.success",null);
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_USER_PORTRAIT_UPLOAD)
	@PostMapping(ApiConstant.USER_PORTRAIT_UPLOAD)
	public BaseResponseResult userPortraitUpload(@TokenDataAnnotation TokenData tokenData,@PathVariable(name="userId",required=true) String userId,@RequestParam(value="userPortraitFile") MultipartFile multipartFile){
		userService.userPortraitUpload(userId,multipartFile);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"user.portrait.upload.success",null);
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_USER_PORTRAIT)
	@GetMapping(ApiConstant.USER_USER_PORTRAIT)
	public void userPortrait(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
		userService.userPortrait(httpServletRequest.getParameter(SystemConstant.HEAD_USERID),httpServletResponse);
	}
	
}

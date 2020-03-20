package com.javaweb.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import com.javaweb.web.po.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags=SwaggerConstant.SWAGGER_USER_CONTROLLER_TAGS)
@RestController
@RequestMapping(ApiConstant.WEB_USER_PREFIX)
public class UserController extends BaseController {

	@ApiOperation(value=SwaggerConstant.SWAGGER_USER_ADD)
	@PostMapping(ApiConstant.USER_ADD)
	public BaseResponseResult userAdd(HttpServletRequest request,@RequestBody @Validated({BaseValidatedGroup.add.class}) User user,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,bindingResult);
		}else{
			TokenData tokenData = getTokenData(request);
			User currentUser = tokenData.getUser();
			user.setUserId(SecretUtil.defaultGenUniqueStr(SystemConstant.SYSTEM_NO));
			try{user.setPassword(SecretUtil.getSecret(user.getPassword(),"SHA-256"));}catch(Exception e){}
			user.setParentId(currentUser.getUserId());
			user.setLevel(currentUser.getLevel()+1);
			user.setCreateDate(DateUtil.getDefaultDate());
			user.setCreator(currentUser.getUserName());
			userService.userAdd(user);
			return getBaseResponseResult(HttpCodeEnum.SUCCESS,"user.add.success",null);
		}
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_USER_LIST)
	@PostMapping(ApiConstant.USER_LIST)
	public BaseResponseResult userList(HttpServletRequest request,@RequestBody UserListRequest userListRequest){
		TokenData tokenData = getTokenData(request);
		userListRequest.setLevel(tokenData.getUser().getLevel());
		Page page = userService.userList(userListRequest);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"user.list.success",page);
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_USER_MODIFY)
	@PutMapping(ApiConstant.USER_MODIFY)
	public BaseResponseResult userModify(HttpServletRequest request,@RequestBody @Validated({BaseValidatedGroup.update.class}) User user,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,bindingResult);
		}else{
			TokenData tokenData = getTokenData(request);
			User currentUser = tokenData.getUser();
			user.setPassword(null);//密码不在此处修改
			user.setUpdateDate(DateUtil.getDefaultDate());
			user.setUpdater(currentUser.getUserName());
			userService.userModify(user);
			return getBaseResponseResult(HttpCodeEnum.SUCCESS,"user.modify.success",null);
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
	
	//这里接口设计的初衷是新增用户仅仅就是新增用户，而不是在新增用户时给其分配角色，但是为了接口的两用型，所以做了适配
	@ApiOperation(value=SwaggerConstant.SWAGGER_USER_ROLE_INFO)
	@GetMapping(value={ApiConstant.USER_ROLE_INFO,ApiConstant.USER_ROLE_INFO2})
	public BaseResponseResult userRoleInfo(@PathVariable(name="userId",required=false) String userId){
		List<RoleInfoResponse> list = userService.userRoleInfo(userId);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"user.userRoleInfo.success",list);
	}

	@ApiOperation(value=SwaggerConstant.SWAGGER_USER_ROLE_ASSIGNMENT)
	@PostMapping(ApiConstant.USER_ROLE_ASSIGNMENT)
	public BaseResponseResult userRoleAssignment(@PathVariable(name="userId",required=true) String userId,@RequestBody List<RoleIdAndStrategyRequest> list){
		userService.userRoleAssignment(userId,list);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"user.userRoleAssignment.success",null);
	}
	
	//这里接口设计的初衷是新增用户仅仅就是新增用户，而不是在新增用户时给其分配模块，但是为了接口的两用型，所以做了适配
	@ApiOperation(value=SwaggerConstant.SWAGGER_USER_MODULE_INFO)
	@GetMapping(value={ApiConstant.USER_MODULE_INFO,ApiConstant.USER_MODULE_INFO2})
	public BaseResponseResult userModuleInfo(@PathVariable(name="userId",required=false) String userId){
		List<ModuleInfoResponse> list = userService.userModuleInfo(userId);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"user.userModuleInfo.success",list);
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_USER_MODULE_ASSIGNMENT)
	@PostMapping(ApiConstant.USER_MODULE_ASSIGNMENT)
	public BaseResponseResult userModuleAssignment(@PathVariable(name="userId",required=true) String userId,@RequestBody List<String> list){
		userService.userModuleAssignment(userId,list);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"user.userModuleAssignment.success",null);
	}
	
}

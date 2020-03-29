package com.javaweb.web.controller;

import java.util.List;

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

import com.javaweb.annotation.token.TokenDataAnnotation;
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
import com.javaweb.web.eo.role.RoleListRequest;
import com.javaweb.web.po.Role;
import com.javaweb.web.po.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags=SwaggerConstant.SWAGGER_ROLE_CONTROLLER_TAGS)
@RestController
@RequestMapping(ApiConstant.WEB_ROLE_PREFIX)
public class RoleController extends BaseController {
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_ROLE_ADD)
	@PostMapping(ApiConstant.ROLE_ADD)
	public BaseResponseResult roleAdd(@TokenDataAnnotation TokenData tokenData,@RequestBody @Validated({BaseValidatedGroup.add.class}) Role role,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,bindingResult);
		}else{
			User currentUser = tokenData.getUser();
			role.setRoleId(SecretUtil.defaultGenUniqueStr(SystemConstant.SYSTEM_NO));
			role.setCreateDate(DateUtil.getDefaultDate());
			role.setCreator(currentUser.getUserName());
			role.setDelFlag(0);
			roleService.roleAdd(role);
			return getBaseResponseResult(HttpCodeEnum.SUCCESS,"role.add.success",null);
		}
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_ROLE_LIST)
	@PostMapping(ApiConstant.ROLE_LIST)
	public BaseResponseResult roleList(@RequestBody RoleListRequest roleListRequest){
		Page page = roleService.roleList(roleListRequest);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"role.list.success",page);
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_ROLE_MODIFY)
	@PutMapping(ApiConstant.ROLE_MODIFY)
	public BaseResponseResult roleModify(@TokenDataAnnotation TokenData tokenData,@RequestBody @Validated({BaseValidatedGroup.update.class}) Role role,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,bindingResult);
		}else{
			User currentUser = tokenData.getUser();
			role.setUpdateDate(DateUtil.getDefaultDate());
			role.setUpdater(currentUser.getUserName());
			roleService.roleModify(role);
			return getBaseResponseResult(HttpCodeEnum.SUCCESS,"role.modify.success",null);
		}
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_ROLE_DETAIL)
	@GetMapping(ApiConstant.ROLE_DETAIL)
	public BaseResponseResult roleDetail(@PathVariable(name="roleId",required=true) String roleId){
		Role role = roleService.roleDetail(roleId);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"role.detail.success",role);
	}
	
	//支持批量删除，用逗号隔开
	@ApiOperation(value=SwaggerConstant.SWAGGER_ROLE_DELETE)
	@DeleteMapping(ApiConstant.ROLE_DELETE)
	public BaseResponseResult roleDelete(@PathVariable(name="roleId",required=true) String roleId){
		roleService.roleDelete(roleId);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"role.delete.success",null);
	}
	
	//ApiConstant.ROLE_MODULE_INFO：获取指定角色的模块信息
	//ApiConstant.ROLE_MODULE_INFO2：获取所有模块信息
	@ApiOperation(value=SwaggerConstant.SWAGGER_ROLE_MODULE_INFO)
	@GetMapping(value={ApiConstant.ROLE_MODULE_INFO,ApiConstant.ROLE_MODULE_INFO2})
	public BaseResponseResult roleModuleInfo(@PathVariable(name="roleId",required=false) String roleId){
		List<ModuleInfoResponse> list = roleService.roleModuleInfo(roleId);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"role.roleModuleInfo.success",list);
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_ROLE_MODULE_ASSIGNMENT)
	@PostMapping(ApiConstant.ROLE_MODULE_ASSIGNMENT)
	public BaseResponseResult roleModuleAssignment(@PathVariable(name="roleId",required=true) String roleId,@RequestBody List<String> list){
		roleService.roleModuleAssignment(roleId,list);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"role.roleModuleAssignment.success",null);
	}
	
}

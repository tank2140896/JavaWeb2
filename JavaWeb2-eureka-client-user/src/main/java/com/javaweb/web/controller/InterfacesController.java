package com.javaweb.web.controller;

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
import com.javaweb.constant.ApiConstant;
import com.javaweb.constant.SwaggerConstant;
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.util.core.DateUtil;
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.TokenData;
import com.javaweb.web.eo.interfaces.InterfacesListRequest;
import com.javaweb.web.eo.interfaces.UserRoleDataPermissionRequest;
import com.javaweb.web.eo.interfaces.UserRolePermissionRequest;
import com.javaweb.web.po.Interfaces;
import com.javaweb.web.po.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags=SwaggerConstant.SWAGGER_INTERFACES_CONTROLLER_TAGS)
@RestController
@RequestMapping(ApiConstant.WEB_INTERFACES_PREFIX)
public class InterfacesController extends BaseController {

	@ApiOperation(value=SwaggerConstant.SWAGGER_INTERFACES_LIST)
	@PostMapping(ApiConstant.INTERFACES_LIST)
	public BaseResponseResult interfacesList(@RequestBody InterfacesListRequest interfacesListRequest,@TokenDataAnnotation TokenData tokenData){
		Page page = interfacesService.interfacesList(interfacesListRequest);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"interfaces.list.success",page);
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_INTERFACES_DETAIL)
	@GetMapping(ApiConstant.INTERFACES_DETAIL)
	public BaseResponseResult interfacesDetail(@PathVariable(name="interfacesId",required=true) String interfacesId){
		Interfaces interfaces = interfacesService.interfacesDetail(interfacesId);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"interfaces.detail.success",interfaces);
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_INTERFACES_MODIFY)
	@PutMapping(ApiConstant.INTERFACES_MODIFY)
	public BaseResponseResult interfacesModify(@RequestBody Interfaces interfaces,@TokenDataAnnotation TokenData tokenData){
		User currentUser = tokenData.getUser();
		interfaces.setUpdateDate(DateUtil.getDefaultDate());
		interfaces.setUpdater(currentUser.getUserId());
		interfaces.setBaseUrl(null);//基础url不能被修改
		interfaces.setHistoryTimes(null);//接口调用次数由程序自动获取处理，不需要被修改
		interfacesService.interfacesModify(interfaces);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"interfaces.modify.success",null);
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_INTERFACES_USER_ROLE_DATA_PERMISSION)
	@PostMapping(ApiConstant.INTERFACES_USER_ROLE_DATA_PERMISSION)
	public BaseResponseResult userRoleDataPermission(@RequestBody UserRoleDataPermissionRequest userRoleDataPermissionRequest){
		Page page = interfacesService.userRoleDataPermission(userRoleDataPermissionRequest);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"interfaces.userRolePermission.success",page);
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_INTERFACES_DATA_PERMISSION_ASSIGNMENT)
	@PostMapping(ApiConstant.INTERFACES_DATA_PERMISSION_ASSIGNMENT)
	public BaseResponseResult dataPermissionAssignment(@PathVariable(name="interfacesId",required=true) String interfacesId,
			                                           @RequestBody UserRolePermissionRequest userRolePermissionResponse,
			                                           @TokenDataAnnotation TokenData tokenData){
		interfacesService.dataPermissionAssignment(userRolePermissionResponse,interfacesId,tokenData.getUser());
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"interfaces.dataPermissionAssignment.success",null);
	}
	
}

package com.javaweb.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.annotation.token.TokenDataAnnotation;
import com.javaweb.annotation.url.ControllerMethod;
import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.constant.ApiConstant;
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.util.core.DateUtil;
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.TokenData;
import com.javaweb.web.eo.interfaces.InterfacesListRequest;
import com.javaweb.web.eo.interfaces.InterfacesTestRequest;
import com.javaweb.web.eo.interfaces.UserRoleDataPermissionRequest;
import com.javaweb.web.eo.interfaces.UserRolePermissionRequest;
import com.javaweb.web.po.Interfaces;
import com.javaweb.web.po.User;

//登录且需要权限才可访问的接口管理模块
@RestController
@RequestMapping(ApiConstant.WEB_INTERFACES_PREFIX)
public class InterfacesController extends BaseController {

	@PostMapping(ApiConstant.INTERFACES_LIST)
	@ControllerMethod(interfaceName="接口列表接口")
	public BaseResponseResult interfacesList(@RequestBody InterfacesListRequest interfacesListRequest,@TokenDataAnnotation TokenData tokenData){
		Page page = interfacesService.interfacesList(interfacesListRequest);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"interfaces.list.success",page);
	}
	
	@GetMapping(ApiConstant.INTERFACES_DETAIL)
	@ControllerMethod(interfaceName="接口详情接口")
	public BaseResponseResult interfacesDetail(@PathVariable(name="interfacesId",required=true) String interfacesId){
		Interfaces interfaces = interfacesService.interfacesDetail(interfacesId);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"interfaces.detail.success",interfaces);
	}
	
	@PutMapping(ApiConstant.INTERFACES_MODIFY)
	@ControllerMethod(interfaceName="修改接口接口")
	public BaseResponseResult interfacesModify(@RequestBody Interfaces interfaces,@TokenDataAnnotation TokenData tokenData){
		User currentUser = tokenData.getUser();
		interfaces.setUpdateDate(DateUtil.getDefaultDate());
		interfaces.setUpdater(currentUser.getUserId());
		interfaces.setBaseUrl(null);//基础url不能被修改
		interfaces.setHistoryTimes(null);//接口调用次数由程序自动获取处理，不需要被修改
		interfacesService.interfacesModify(interfaces);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"interfaces.modify.success",null);
	}
	
	@PostMapping(ApiConstant.INTERFACES_USER_ROLE_DATA_PERMISSION)
	@ControllerMethod(interfaceName="用户角色数据权限接口")
	public BaseResponseResult userRoleDataPermission(@RequestBody UserRoleDataPermissionRequest userRoleDataPermissionRequest){
		Page page = interfacesService.userRoleDataPermission(userRoleDataPermissionRequest);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"interfaces.userRolePermission.success",page);
	}
	
	@PostMapping(ApiConstant.INTERFACES_DATA_PERMISSION_ASSIGNMENT)
	@ControllerMethod(interfaceName="数据权限分配接口")
	public BaseResponseResult dataPermissionAssignment(@PathVariable(name="interfacesId",required=true) String interfacesId,
			                                           @RequestBody UserRolePermissionRequest userRolePermissionResponse,
			                                           @TokenDataAnnotation TokenData tokenData){
		interfacesService.dataPermissionAssignment(userRolePermissionResponse,interfacesId,tokenData.getUser());
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"interfaces.dataPermissionAssignment.success",null);
	}
	
	@PostMapping(ApiConstant.INTERFACES_TEST)
	@ControllerMethod(interfaceName="接口测试接口")
	public BaseResponseResult interfacesTest(@RequestBody InterfacesTestRequest interfacesTestRequest){
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"interfaces.test.success",interfacesService.interfacesTest(interfacesTestRequest));
	}
	
}

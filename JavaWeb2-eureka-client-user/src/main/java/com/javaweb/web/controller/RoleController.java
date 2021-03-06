package com.javaweb.web.controller;

import java.util.ArrayList;
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
import com.javaweb.annotation.url.ControllerMethod;
import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.base.BaseServiceValidateResult;
import com.javaweb.base.BaseValidatedGroup;
import com.javaweb.constant.ApiConstant;
import com.javaweb.db.query.QueryWapper;
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.util.core.DateUtil;
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.TokenData;
import com.javaweb.web.eo.role.ModuleInfoResponse;
import com.javaweb.web.eo.role.RoleListRequest;
import com.javaweb.web.eo.validate.ColumnsRepeatRequest;
import com.javaweb.web.po.Role;
import com.javaweb.web.po.User;

//登录且需要权限才可访问的角色管理模块
@RestController
@RequestMapping(ApiConstant.WEB_ROLE_PREFIX)
public class RoleController extends BaseController {
	
	@PostMapping(ApiConstant.ROLE_ADD)
	@ControllerMethod(interfaceName="新增角色接口")
	public BaseResponseResult roleAdd(@RequestBody @Validated({BaseValidatedGroup.add.class}) Role role,BindingResult bindingResult,@TokenDataAnnotation TokenData tokenData){
		if(bindingResult.hasErrors()){
			return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,bindingResult);
		}
		List<ColumnsRepeatRequest<Role>> columnsRepeatRequestList = new ArrayList<>();
		columnsRepeatRequestList.add(new ColumnsRepeatRequest<Role>(new QueryWapper<Role>().eq(Role.roleNameColumn,role.getRoleName()),"validated.role.roleName.repeat"));
		columnsRepeatRequestList.add(new ColumnsRepeatRequest<Role>(new QueryWapper<Role>().eq(Role.roleCodeColumn,role.getRoleCode()),"validated.role.roleCode.repeat"));
		BaseServiceValidateResult baseServiceValidateResult = baseValidateService.isColumnsValueRepeat(columnsRepeatRequestList,roleDao);
		if(!baseServiceValidateResult.isValidatePass()){
			return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,baseServiceValidateResult.getMessage());
		}
		User currentUser = tokenData.getUser();
		role.setCreateDate(DateUtil.getDefaultDate());
		role.setCreator(currentUser.getUserId());
		role.setDelFlag(0);
		roleService.roleAdd(role);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"role.add.success");
	}
	
	@PostMapping(ApiConstant.ROLE_LIST)
	@ControllerMethod(interfaceName="查询角色接口")
	public BaseResponseResult roleList(@RequestBody RoleListRequest roleListRequest){
		Page page = roleService.roleList(roleListRequest);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"role.list.success",page);
	}
	
	@PutMapping(ApiConstant.ROLE_MODIFY)
	@ControllerMethod(interfaceName="修改角色接口")
	public BaseResponseResult roleModify(@RequestBody @Validated({BaseValidatedGroup.update.class}) Role role,BindingResult bindingResult,@TokenDataAnnotation TokenData tokenData){
		if(bindingResult.hasErrors()){
			return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,bindingResult);
		}
		Role originRole = roleService.roleDetail(role.getRoleId());
		if(originRole==null){
			return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,"validated.role.modify.notExist");
		}
		ColumnsRepeatRequest<Role> roleColumnsRepeatRequest = new ColumnsRepeatRequest<Role>(new QueryWapper<Role>().neq(Role.roleIdColumn,originRole.getRoleId()).eq(Role.roleNameColumn,role.getRoleName()),"validated.role.roleName.repeat");
		BaseServiceValidateResult baseServiceValidateResult = baseValidateService.isColumnsValueRepeat(roleColumnsRepeatRequest,roleDao);
		if(!baseServiceValidateResult.isValidatePass()){
			return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,baseServiceValidateResult.getMessage());
		}
		roleColumnsRepeatRequest = new ColumnsRepeatRequest<Role>(new QueryWapper<Role>().neq(Role.roleIdColumn,originRole.getRoleId()).eq(Role.roleCodeColumn,role.getRoleCode()),"validated.role.roleCode.repeat");
		baseServiceValidateResult = baseValidateService.isColumnsValueRepeat(roleColumnsRepeatRequest,roleDao);
		if(!baseServiceValidateResult.isValidatePass()){
			return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,baseServiceValidateResult.getMessage());
		}
		User currentUser = tokenData.getUser();
		role.setUpdateDate(DateUtil.getDefaultDate());
		role.setUpdater(currentUser.getUserId());
		roleService.roleModify(role);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"role.modify.success");
		
	}
	
	@GetMapping(ApiConstant.ROLE_DETAIL)
	@ControllerMethod(interfaceName="角色详情接口")
	public BaseResponseResult roleDetail(@PathVariable(name="roleId",required=true) String roleId){
		Role role = roleService.roleDetail(roleId);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"role.detail.success",role);
	}
	
	@DeleteMapping(ApiConstant.ROLE_DELETE)
	@ControllerMethod(interfaceName="删除角色接口（支持批量删除，用逗号隔开）")
	public BaseResponseResult roleDelete(@PathVariable(name="roleId",required=true) String roleId){
		roleService.roleDelete(roleId);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"role.delete.success");
	}
	
	@GetMapping(value={ApiConstant.ROLE_MODULE_INFO/*获取指定角色的模块信息*/,ApiConstant.ROLE_MODULE_INFO2/*获取所有模块信息*/})
	@ControllerMethod(interfaceName="角色模块信息接口")
	public BaseResponseResult roleModuleInfo(@PathVariable(name="roleId",required=false) String roleId){
		List<ModuleInfoResponse> list = roleService.roleModuleInfo(roleId);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"role.roleModuleInfo.success",list);
	}
	
	@PostMapping(ApiConstant.ROLE_MODULE_ASSIGNMENT)
	@ControllerMethod(interfaceName="角色模块分配接口")
	public BaseResponseResult roleModuleAssignment(@RequestBody List<String> list,@PathVariable(name="roleId",required=true) String roleId){
		roleService.roleModuleAssignment(roleId,list);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"role.roleModuleAssignment.success");
	}
	
}

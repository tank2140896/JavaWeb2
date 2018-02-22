package com.javaweb.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.javaweb.constant.CommonConstant;
import com.javaweb.constant.SystemConstant;
import com.javaweb.util.core.DateUtil;
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.TokenData;
import com.javaweb.web.eo.role.RoleListRequest;
import com.javaweb.web.po.Role;
import com.javaweb.web.po.User;

@RestController
@RequestMapping("/web/sys/role")
public class RoleController extends BaseController {
	
	@PostMapping("/list")
	public BaseResponseResult roleList(HttpServletRequest request,@RequestBody RoleListRequest roleListRequest){
		Page page = roleService.roleList(roleListRequest);
		return new BaseResponseResult(SystemConstant.SUCCESS,getMessage("role.list.success"),page);
	}
	
	@DeleteMapping("/delete/{roleId}")
	public BaseResponseResult roleDelete(@PathVariable("roleId") String roleId){
		roleService.roleDelete(roleId);
		return new BaseResponseResult(SystemConstant.SUCCESS,getMessage("role.delete.success"),null);
	}
	
	@PostMapping("/add")
	public BaseResponseResult roleAdd(HttpServletRequest request,@RequestBody @Validated({BaseValidatedGroup.add.class}) Role role,BindingResult bindingResult){
		BaseResponseResult baseResponseResult = new BaseResponseResult();
		if(bindingResult.hasErrors()){
			baseResponseResult = new BaseResponseResult(SystemConstant.VALIDATE_ERROR,getValidateMessage(bindingResult),CommonConstant.EMPTY_VALUE);
		}else{
			TokenData tokenData = getTokenData(request);
			User currentUser = tokenData.getUser();
			role.setRoleId(UUID.randomUUID().toString());
			role.setCreateDate(DateUtil.getDefaultDate());
			role.setCreator(currentUser.getUserName());
			roleService.roleAdd(role);
			baseResponseResult = new BaseResponseResult(SystemConstant.SUCCESS,getMessage("role.add.success"),null);
		}
		return baseResponseResult;
	}
	
	@PutMapping("/modify")
	public BaseResponseResult roleModify(HttpServletRequest request,@RequestBody @Validated({BaseValidatedGroup.update.class}) Role role,BindingResult bindingResult){
		BaseResponseResult baseResponseResult = new BaseResponseResult();
		if(bindingResult.hasErrors()){
			baseResponseResult = new BaseResponseResult(SystemConstant.VALIDATE_ERROR,getValidateMessage(bindingResult),CommonConstant.EMPTY_VALUE);
		}else{
			TokenData tokenData = getTokenData(request);
			User currentUser = tokenData.getUser();
			role.setUpdateDate(DateUtil.getDefaultDate());
			role.setUpdater(currentUser.getUserName());
			roleService.roleModify(role);
			baseResponseResult = new BaseResponseResult(SystemConstant.SUCCESS,getMessage("role.modify.success"),null);
		}
		return baseResponseResult;
	}
	
	@GetMapping("/detail/{roleId}")
	public BaseResponseResult roleDetail(@PathVariable("roleId") String roleId){
		Role role = roleService.roleDetail(roleId);
		return new BaseResponseResult(SystemConstant.SUCCESS,getMessage("role.detail.success"),role);
	}
	
	@GetMapping("/roleModuleInfo/{roleId}")
	public BaseResponseResult roleModuleInfo(@PathVariable("roleId") String roleId){
		Map<String,Object> map = roleService.roleModuleInfo(roleId);
		return new BaseResponseResult(SystemConstant.SUCCESS,getMessage("role.roleModuleInfo.success"),map);
	}
	
	@PostMapping("/moduleAssignment/{roleId}")
	public BaseResponseResult moduleAssignment(@PathVariable("roleId") String roleId,@RequestBody List<String> list){
		Map<String,Object> map = new HashMap<>();
		map.put("roleId",roleId);
		map.put("list",list);
		roleService.moduleAssignment(map);
		return new BaseResponseResult(SystemConstant.SUCCESS,getMessage("role.moduleAssignment.success"),null);
	}
	
}
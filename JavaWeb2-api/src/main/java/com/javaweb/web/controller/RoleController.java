package com.javaweb.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.constant.SystemConstant;
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.role.RoleListRequest;
import com.javaweb.web.service.RoleService;

@RestController
@RequestMapping("/web/sys/role")
public class RoleController extends BaseController {
	
	@Autowired
	private RoleService roleService;
	
	@PostMapping("/list")
	public BaseResponseResult roleList(HttpServletRequest request,@RequestBody RoleListRequest roleListRequest){
		Page page = roleService.roleList(roleListRequest);
		return new BaseResponseResult(SystemConstant.SUCCESS,getMessage("role.list.success"),page);
	}
	
}
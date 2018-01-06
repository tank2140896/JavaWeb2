package com.javaweb.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.constant.CommonConstant;
import com.javaweb.constant.SystemConstant;
import com.javaweb.web.service.UserService;

@RequestMapping("/web/permission")
@RestController
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user/getAllUsers")
	public BaseResponseResult getAllUsers(){
		return new BaseResponseResult(SystemConstant.INTERNAL_ERROR,getMessage("system.error"),CommonConstant.EMPTY_VALUE);
	}
	
}
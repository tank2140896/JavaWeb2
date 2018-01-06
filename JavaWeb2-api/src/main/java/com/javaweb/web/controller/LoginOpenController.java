package com.javaweb.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.constant.SystemConstant;

@RequestMapping("/web/logged")
@RestController
public class LoginOpenController extends BaseController {
	
	@GetMapping("/logout")
	public BaseResponseResult logout(HttpServletRequest request){
		String userId = request.getHeader(SystemConstant.HEAD_USERID);
		redisTemplate.delete(userId);
		return new BaseResponseResult(SystemConstant.SUCCESS,getMessage("login.User.logoutSuccess"),null);
	}
	
}
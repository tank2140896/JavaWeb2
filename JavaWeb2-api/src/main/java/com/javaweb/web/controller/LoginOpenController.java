package com.javaweb.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.constant.SystemConstant;
import com.javaweb.web.eo.TokenData;

@RestController
@RequestMapping("/web/loggedIn")
public class LoginOpenController extends BaseController {
	
	@GetMapping("/logout")
	public BaseResponseResult logout(HttpServletRequest request){
		String userId = request.getHeader(SystemConstant.HEAD_USERID);
		redisTemplate.delete(userId);
		return new BaseResponseResult(SystemConstant.SUCCESS,getMessage("login.User.logoutSuccess"),null);
	}
	
	@GetMapping("/getRedisUserInfo")
	public BaseResponseResult getRedisUserInfo(HttpServletRequest request){
		String userId = request.getHeader(SystemConstant.HEAD_USERID);
		TokenData tokenData = (TokenData)valueOperations.get(userId);//(TokenData)request.getSession().getAttribute(userId);
		return new BaseResponseResult(SystemConstant.SUCCESS,getMessage("login.User.getTokenDataSuccess"),tokenData);
	}
	
}
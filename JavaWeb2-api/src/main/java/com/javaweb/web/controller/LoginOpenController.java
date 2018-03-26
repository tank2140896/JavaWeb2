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
		String type = request.getHeader(SystemConstant.HEAD_TYPE);
		redisTemplate.delete(userId+","+type);
		return new BaseResponseResult(SystemConstant.SUCCESS,getMessage("login.user.logoutSuccess"),null);
	}
	
	@GetMapping("/getRedisUserInfo")
	public BaseResponseResult getRedisUserInfo(HttpServletRequest request){
		TokenData tokenData = getTokenData(request);//(TokenData)request.getSession().getAttribute(userId);
		return new BaseResponseResult(SystemConstant.SUCCESS,getMessage("login.user.getTokenDataSuccess"),tokenData);
	}
	
}
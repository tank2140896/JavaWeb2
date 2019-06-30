package com.javaweb.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.annotation.token.TokenDataAnnotation;
import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.constant.ApiConstant;
import com.javaweb.constant.CommonConstant;
import com.javaweb.constant.SwaggerConstant;
import com.javaweb.constant.SystemConstant;
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.web.eo.TokenData;

@Api(tags=SwaggerConstant.SWAGGER_LOGIN_ACCESS_CONTROLLER_TAGS)
@RestController
@RequestMapping(ApiConstant.LOGIN_ACCESS_PREFIX)
public class LoginAccessController extends BaseController {
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_LOGOUT)
	@GetMapping(ApiConstant.LOGIN_OUT)
	public BaseResponseResult logout(HttpServletRequest request){
		String userId = request.getHeader(SystemConstant.HEAD_USERID);
		//String token = request.getHeader(SystemConstant.HEAD_TOKEN);
		String type = request.getHeader(SystemConstant.HEAD_TYPE);
		String key = String.join(CommonConstant.COMMA,userId,type);
		if(deleteFromRedisByKey(key)) {
			return getBaseResponseResult(HttpCodeEnum.SUCCESS,"login.user.logoutSuccess");
		}else {
			return getBaseResponseResult(HttpCodeEnum.SUCCESS,"login.user.logoutFail");
		}
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_GET_REDIS_TOKEN_DATA)
	@GetMapping(ApiConstant.GET_REDIS_TOKEN_DATA)
	public BaseResponseResult getRedisTokenData(@TokenDataAnnotation TokenData tokenData,HttpServletRequest request){
		/**
		TokenData tokenData = getTokenData(request);
		String key = String.join(CommonConstant.COMMA,request.getHeader(SystemConstant.HEAD_USERID),request.getHeader(SystemConstant.HEAD_TYPE));
		setDefaultDataToRedis(key,tokenData);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"login.user.getTokenDataSuccess",tokenData);
		*/
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"login.user.getTokenDataSuccess",tokenData);
	}
	
}
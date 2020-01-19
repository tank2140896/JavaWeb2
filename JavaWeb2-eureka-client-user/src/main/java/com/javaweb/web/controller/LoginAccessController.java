package com.javaweb.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.annotation.token.TokenDataAnnotation;
import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.constant.ApiConstant;
import com.javaweb.constant.SwaggerConstant;
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.web.eo.TokenData;
import com.javaweb.web.po.Dictionary;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags=SwaggerConstant.SWAGGER_LOGIN_ACCESS_CONTROLLER_TAGS)
@RestController
@RequestMapping(ApiConstant.WEB_LOGIN_ACCESS_PREFIX)
public class LoginAccessController extends BaseController {
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_LOGOUT)
	@GetMapping(ApiConstant.LOGIN_OUT)
	public BaseResponseResult logout(@TokenDataAnnotation TokenData tokenData/*,HttpServletRequest request*/){
		/*
		String userId = request.getHeader(SystemConstant.HEAD_USERID);
		String token = request.getHeader(SystemConstant.HEAD_TOKEN);
		String type = request.getHeader(SystemConstant.HEAD_TYPE);
		String key = String.join(CommonConstant.COMMA,userId,type);
		*/
		//String key = String.join(CommonConstant.COMMA,tokenData.getUser().getUserId(),tokenData.getType());
		if(deleteFromRedisByKey(/*key*/tokenData.getToken())) {
			return getBaseResponseResult(HttpCodeEnum.SUCCESS,"login.user.logoutSuccess");
		}else {
			return getBaseResponseResult(HttpCodeEnum.SUCCESS,"login.user.logoutFail");
		}
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_GET_REDIS_TOKEN_DATA)
	@GetMapping(ApiConstant.GET_REDIS_TOKEN_DATA)
	public BaseResponseResult getRedisTokenData(@TokenDataAnnotation TokenData tokenData/*,HttpServletRequest request*/){
		/*
		TokenData tokenData = getTokenData(request);
		String key = String.join(CommonConstant.COMMA,request.getHeader(SystemConstant.HEAD_USERID),request.getHeader(SystemConstant.HEAD_TYPE));
		setDefaultDataToRedis(key,tokenData);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"login.user.getTokenDataSuccess",tokenData);
		*/
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"login.user.getTokenDataSuccess",tokenData);
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_GET_DICTIONARY)
    @PostMapping(ApiConstant.GET_DICTIONARY)
	public BaseResponseResult getDictionary(@RequestBody Dictionary dictionary) {
	    return getBaseResponseResult(HttpCodeEnum.SUCCESS,"login.user.getDictionarySuccess",dictionaryService.getDictionary(dictionary));
	}
	
}
package com.javaweb.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.annotation.token.TokenDataAnnotation;
import com.javaweb.annotation.url.ControllerMethod;
import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.constant.ApiConstant;
import com.javaweb.constant.CommonConstant;
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.web.eo.TokenData;
import com.javaweb.web.po.Dictionary;

//登录才可访问的模块
@RestController
@RequestMapping(ApiConstant.WEB_LOGIN_ACCESS_PREFIX)
public class LoginAccessController extends BaseController {
	
	@GetMapping(ApiConstant.LOGIN_OUT)
	@ControllerMethod(interfaceName="用户登出接口")
	public BaseResponseResult logout(@TokenDataAnnotation TokenData tokenData){
		if(deleteFromRedisByKey(tokenData.getUser().getUserId()+CommonConstant.COMMA+tokenData.getClientType()+CommonConstant.COMMA+tokenData.getLoginWay())) {
			return getBaseResponseResult(HttpCodeEnum.SUCCESS,"login.user.logoutSuccess");
		}else {
			return getBaseResponseResult(HttpCodeEnum.SUCCESS,"login.user.logoutFail");
		}
	}
	
	@GetMapping(ApiConstant.GET_REDIS_TOKEN_DATA)
	@ControllerMethod(interfaceName="获取redis中的token信息接口")
	public BaseResponseResult getRedisTokenData(@TokenDataAnnotation TokenData tokenData){
		tokenData.setRsaPublicKeyOfBackend(null);
		tokenData.setRsaPrivateKeyOfFrontend(null);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"login.user.getTokenDataSuccess",tokenData);
	}
	
    @PostMapping(ApiConstant.GET_DICTIONARY)
    @ControllerMethod(interfaceName="获得字典信息接口")
    public BaseResponseResult getDictionary(@RequestBody Dictionary dictionary) {
	    return getBaseResponseResult(HttpCodeEnum.SUCCESS,"login.user.getDictionarySuccess",dictionaryService.getDictionary(dictionary));
	}
	
}

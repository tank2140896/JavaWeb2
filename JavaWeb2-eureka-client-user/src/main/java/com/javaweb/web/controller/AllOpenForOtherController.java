package com.javaweb.web.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.annotation.url.ControllerMethod;
import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.constant.ApiConstant;
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.util.core.DateUtil;

//无需登录即可访问模块
@RestController
public class AllOpenForOtherController extends BaseController {
	
	@GetMapping(ApiConstant.GET_SERVE_TIME)
	@ControllerMethod(interfaceName="获取服务器时间接口")
	public BaseResponseResult getServeTime(){
		LocalDateTime localDateTime = LocalDateTime.now(ZoneId.systemDefault());//ZoneId.of("GMT+8")
		String dateTime = localDateTime.format(DateTimeFormatter.ofPattern(DateUtil.DEFAULT_DATETIME_PATTERN));
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"serve.getServeTime.success",dateTime);
	}
	
	/* -------------------------------------------------- 分界线 -------------------------------------------------- */
	
	@RequestMapping(value=ApiConstant.REQUEST_LIMIT,method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
	@ControllerMethod(interfaceName="请求接口受限接口")
	public BaseResponseResult requestLimit() {
		return getBaseResponseResult(HttpCodeEnum.REQUEST_LIMIT,"validated.permission.requestLimit");
	}
	
	@RequestMapping(value=ApiConstant.REQUEST_PARAMETER_LOST,method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
	@ControllerMethod(interfaceName="请求参数缺失接口")
	public BaseResponseResult requestParameterLost() {
		return getBaseResponseResult(HttpCodeEnum.REQUEST_PARAMETER_LOST,"validated.permission.requestParameterLost");
	}
	
	@RequestMapping(value=ApiConstant.INVALID_REQUEST,method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
	@ControllerMethod(interfaceName="请求失效接口")
	public BaseResponseResult invalidRequest(){
		return getBaseResponseResult(HttpCodeEnum.INVALID_REQUEST,"validated.permission.invalidRequest");
	}
	
	@RequestMapping(value=ApiConstant.REQUEST_PARAMETER_ERROR,method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
	@ControllerMethod(interfaceName="请求参数错误接口")
	public BaseResponseResult requestParameterError(){
		return getBaseResponseResult(HttpCodeEnum.REQUEST_PARAMETER_ERROR,"validated.permission.requestParameterError");
	}
	
	@RequestMapping(value=ApiConstant.NO_AUTHORY,method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
	@ControllerMethod(interfaceName="没有权限接口")
	public BaseResponseResult noAuthory(){
		return getBaseResponseResult(HttpCodeEnum.NO_AUTHORY,"validated.permission.noAuthory");
	}
	
	@RequestMapping(value=ApiConstant.NOT_FOUND,method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
	@ControllerMethod(interfaceName="请求接口不存在接口")
	public BaseResponseResult notFound(){
		return getBaseResponseResult(HttpCodeEnum.NOT_FOUND,"validated.permission.notFound");
	}
	
	@RequestMapping(value=ApiConstant.INTERNAL_ERROR,method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
	@ControllerMethod(interfaceName="系统异常接口")
	public BaseResponseResult internalError(){
		return getBaseResponseResult(HttpCodeEnum.INTERNAL_ERROR,"validated.permission.internalError");
	}

}

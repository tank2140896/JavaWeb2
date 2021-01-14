package com.javaweb.web.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.constant.ApiConstant;
import com.javaweb.constant.SwaggerConstant;
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.util.core.DateUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags=SwaggerConstant.SWAGGER_ALL_OPEN_CONTROLLER_TAGS)
@RestController
public class AllOpenForOtherController extends BaseController {
	
	//获取服务器时间接口
	@ApiOperation(value=SwaggerConstant.SWAGGER_GET_SERVE_TIME)
	@GetMapping(ApiConstant.GET_SERVE_TIME)
	public BaseResponseResult getServeTime(){
		LocalDateTime localDateTime = LocalDateTime.now(ZoneId.systemDefault());//ZoneId.of("GMT+8")
		String dateTime = localDateTime.format(DateTimeFormatter.ofPattern(DateUtil.DEFAULT_DATETIME_PATTERN));
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"serve.getServeTime.success",dateTime);
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_REQUEST_LIMIT)
	@RequestMapping(value=ApiConstant.REQUEST_LIMIT,method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
	public BaseResponseResult requestLimit() {
		return getBaseResponseResult(HttpCodeEnum.REQUEST_LIMIT,"validated.permission.requestLimit");
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_REQUEST_PARAMETER_LOST)
	@RequestMapping(value=ApiConstant.REQUEST_PARAMETER_LOST,method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
	public BaseResponseResult requestParameterLost() {
		return getBaseResponseResult(HttpCodeEnum.REQUEST_PARAMETER_LOST,"validated.permission.requestParameterLost");
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_INVALID_REQUEST)
	@RequestMapping(value=ApiConstant.INVALID_REQUEST,method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
	public BaseResponseResult invalidRequest(){
		return getBaseResponseResult(HttpCodeEnum.INVALID_REQUEST,"validated.permission.invalidRequest");
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_REQUEST_PARAMETER_ERROR)
	@RequestMapping(value=ApiConstant.REQUEST_PARAMETER_ERROR,method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
	public BaseResponseResult requestParameterError(){
		return getBaseResponseResult(HttpCodeEnum.REQUEST_PARAMETER_ERROR,"validated.permission.requestParameterError");
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_NO_AUTHORY)
	@RequestMapping(value=ApiConstant.NO_AUTHORY,method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
	public BaseResponseResult noAuthory(){
		return getBaseResponseResult(HttpCodeEnum.NO_AUTHORY,"validated.permission.noAuthory");
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_NOT_FOUND)
	@RequestMapping(value=ApiConstant.NOT_FOUND,method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
	public BaseResponseResult notFound(){
		return getBaseResponseResult(HttpCodeEnum.NOT_FOUND,"validated.permission.notFound");
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_INTERNAL_ERROR)
	@RequestMapping(value=ApiConstant.INTERNAL_ERROR,method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
	public BaseResponseResult internalError(){
		return getBaseResponseResult(HttpCodeEnum.INTERNAL_ERROR,"validated.permission.internalError");
	}

}

package com.javaweb.web.controller;

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
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.TokenData;
import com.javaweb.web.eo.interfaces.InterfacesListRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags=SwaggerConstant.SWAGGER_INTERFACES_CONTROLLER_TAGS)
@RestController
@RequestMapping(ApiConstant.WEB_INTERFACES_PREFIX)
public class InterfacesController extends BaseController {

	@ApiOperation(value=SwaggerConstant.SWAGGER_INTERFACES_LIST)
	@PostMapping(ApiConstant.INTERFACES_LIST)
	public BaseResponseResult interfacesList(@RequestBody InterfacesListRequest interfacesListRequest,@TokenDataAnnotation TokenData tokenData){
		Page page = interfacesService.interfacesList(interfacesListRequest);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"interfaces.list.success",page);
	}
	
}

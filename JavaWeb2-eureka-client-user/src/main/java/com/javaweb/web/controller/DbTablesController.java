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
import com.javaweb.web.eo.dbTables.DbTablesListRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags=SwaggerConstant.SWAGGER_DB_TABLES_CONTROLLER_TAGS)
@RestController
@RequestMapping(ApiConstant.WEB_DB_TABLES_PREFIX)
public class DbTablesController extends BaseController {

	@ApiOperation(value=SwaggerConstant.SWAGGER_DB_TABLES_LIST)
	@PostMapping(ApiConstant.DB_TABLES_LIST)
	public BaseResponseResult dbTablesList(@RequestBody DbTablesListRequest dbTablesListRequest,@TokenDataAnnotation TokenData tokenData){
		Page page = dbTablesService.dbTablesList(dbTablesListRequest);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"dbTables.list.success",page);
	}
	
}

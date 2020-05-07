package com.javaweb.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.constant.ApiConstant;
import com.javaweb.constant.SwaggerConstant;
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.dbTables.DbTablesColumnListResponse;
import com.javaweb.web.eo.dbTables.DbTablesListRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags=SwaggerConstant.SWAGGER_DB_TABLES_CONTROLLER_TAGS)
@RestController
@RequestMapping(ApiConstant.WEB_DB_TABLES_PREFIX)
public class DbTablesController extends BaseController {

	@ApiOperation(value=SwaggerConstant.SWAGGER_DB_TABLES_LIST)
	@PostMapping(ApiConstant.DB_TABLES_LIST)
	public BaseResponseResult dbTablesList(@RequestBody DbTablesListRequest dbTablesListRequest){
		Page page = dbTablesService.dbTablesList(dbTablesListRequest);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"dbTables.list.success",page);
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_DB_TABLES_DETAIL)
	@GetMapping(ApiConstant.DB_TABLES_DETAIL)
	public BaseResponseResult dbTablesDetail(@PathVariable(name="tableName",required=true) String tableName){
		List<DbTablesColumnListResponse> list = dbTablesService.getTableColumnInfo(tableName);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"dbTables.detail.success",list);
	}
	
}

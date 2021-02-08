package com.javaweb.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.annotation.url.ControllerMethod;
import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.constant.ApiConstant;
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.dbTables.DbTablesColumnListResponse;
import com.javaweb.web.eo.dbTables.DbTablesListRequest;

//登录且需要权限才可访问的数据库表管理模块
@RestController
@RequestMapping(ApiConstant.WEB_DB_TABLES_PREFIX)
public class DbTablesController extends BaseController {
	
	@PostMapping(ApiConstant.DB_TABLES_LIST)
	@ControllerMethod(interfaceName="数据库表列表接口")
	public BaseResponseResult dbTablesList(@RequestBody DbTablesListRequest dbTablesListRequest){
		Page page = dbTablesService.dbTablesList(dbTablesListRequest);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"dbTables.list.success",page);
	}
	
	@GetMapping(ApiConstant.DB_TABLES_DETAIL)
	@ControllerMethod(interfaceName="数据库表详情接口")
	public BaseResponseResult dbTablesDetail(@PathVariable(name="tableName",required=true) String tableName){
		List<DbTablesColumnListResponse> list = dbTablesService.getTableColumnInfo(tableName);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"dbTables.detail.success",list);
	}
	
	@GetMapping(ApiConstant.DB_TABLES_CODE_GENERATE)
	@ControllerMethod(interfaceName="数据库表代码生成接口")
	public void dbTablesCodeGenerate(@PathVariable(name="tableName",required=true) String tableName,HttpServletResponse httpServletResponse){
		dbTablesService.codeGenerate(tableName,httpServletResponse);
	}
	
}

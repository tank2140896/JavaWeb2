package com.javaweb.web.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.dbTables.DbTablesColumnListResponse;
import com.javaweb.web.eo.dbTables.DbTablesListRequest;

public interface DbTablesService {
	
	public Page dbTablesList(DbTablesListRequest dbTablesListRequest);
	
	public List<DbTablesColumnListResponse> getTableColumnInfo(String tableName);
	
	public void codeGenerate(String tableName,HttpServletResponse httpServletResponse);
	
}

package com.javaweb.web.service;

import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.dbTables.DbTablesListRequest;

public interface DbTablesService {
	
	public Page dbTablesList(DbTablesListRequest dbTablesListRequest);
	
}

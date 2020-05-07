package com.javaweb.web.dao.ds1;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.web.eo.dbTables.DbTablesListResponse;

@Mapper
public interface DbTablesDao {
	
	public List<String> getTableList();
	
	public List<DbTablesListResponse> getTableInfo(List<String> list);

}

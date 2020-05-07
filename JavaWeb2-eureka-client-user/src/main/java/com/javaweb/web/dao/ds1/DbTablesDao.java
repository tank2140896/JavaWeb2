package com.javaweb.web.dao.ds1;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.web.eo.dbTables.DbTablesListResponse;

@Mapper
public interface DbTablesDao {
	
	public List<String> getTableList();
	
	public List<DbTablesListResponse> getTableInfo(List<String> list);
	
	public List<Map<String,Object>> getTableColumnInfo(String tableName);

}

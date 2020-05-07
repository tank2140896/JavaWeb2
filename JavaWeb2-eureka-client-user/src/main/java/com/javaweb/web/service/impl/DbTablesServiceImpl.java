package com.javaweb.web.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.javaweb.base.BaseService;
import com.javaweb.constant.CommonConstant;
import com.javaweb.util.core.PageUtil;
import com.javaweb.util.core.StringUtil;
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.dbTables.DbTablesColumnListResponse;
import com.javaweb.web.eo.dbTables.DbTablesListRequest;
import com.javaweb.web.eo.dbTables.DbTablesListResponse;
import com.javaweb.web.service.DbTablesService;

@Service("dbTablesServiceImpl")
public class DbTablesServiceImpl extends BaseService implements DbTablesService {

	public Page dbTablesList(DbTablesListRequest dbTablesListRequest) {
		List<String> tablelist = dbTablesDao.getTableList();
		if(!(StringUtil.handleNullString(dbTablesListRequest.getTableName()).equals(CommonConstant.EMPTY_VALUE))){
			tablelist = tablelist.stream().filter(e->e.contains(dbTablesListRequest.getTableName())).collect(Collectors.toList());
		}
		long count = tablelist.size();
		tablelist = PageUtil.getSubList(tablelist,dbTablesListRequest.getPageSize(),dbTablesListRequest.getCurrentPage());
		List<DbTablesListResponse> list = new ArrayList<>();
		if(tablelist!=null&&tablelist.size()>0){
			list = dbTablesDao.getTableInfo(tablelist).stream().sorted(new Comparator<DbTablesListResponse>() {
				public int compare(DbTablesListResponse o1, DbTablesListResponse o2) {
					return o1.getTableName().hashCode()>o2.getTableName().hashCode()?0:1;
				}
			}).collect(Collectors.toList());
		}
		Page page = new Page(dbTablesListRequest,list,count);
		return page;
	}
	
	public List<DbTablesColumnListResponse> getTableColumnInfo(String tableName) {
		List<DbTablesColumnListResponse> result = new ArrayList<>();
		List<Map<String,Object>> list = dbTablesDao.getTableColumnInfo(tableName);
		for(int i=0;i<list.size();i++){
			Map<String,Object> map = list.get(i);
			DbTablesColumnListResponse dbTablesColumnListResponse = new DbTablesColumnListResponse();
			dbTablesColumnListResponse.setField(map.get("Field").toString());
			dbTablesColumnListResponse.setType(map.get("Type")==null?null:String.valueOf(map.get("Type")));
			dbTablesColumnListResponse.setIsNull(map.get("Null")==null?null:String.valueOf(map.get("Null")));
			dbTablesColumnListResponse.setExtra(map.get("Extra")==null?null:String.valueOf(map.get("Extra")));
			dbTablesColumnListResponse.setDefaultValue(map.get("Default")==null?null:String.valueOf(map.get("Default")));
			dbTablesColumnListResponse.setIsKey(map.get("Key")==null?null:String.valueOf(map.get("Key")));
			result.add(dbTablesColumnListResponse);
		}
		return result;
	}
	
}

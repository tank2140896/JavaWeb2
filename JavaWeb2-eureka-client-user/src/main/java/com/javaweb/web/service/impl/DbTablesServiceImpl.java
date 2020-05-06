package com.javaweb.web.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.javaweb.base.BaseService;
import com.javaweb.constant.CommonConstant;
import com.javaweb.util.core.PageUtil;
import com.javaweb.util.core.StringUtil;
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.dbTables.DbTablesListRequest;
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
		Page page = new Page(dbTablesListRequest,tablelist,count);
		return page;
	}
	
}

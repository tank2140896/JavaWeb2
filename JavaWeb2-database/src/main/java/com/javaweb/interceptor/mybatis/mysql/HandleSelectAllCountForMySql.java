package com.javaweb.interceptor.mybatis.mysql;

import com.javaweb.interceptor.mybatis.SqlBuildInfo;
import com.javaweb.interceptor.mybatis.SqlHandle;

public class HandleSelectAllCountForMySql implements SqlHandle {

	public String handle(SqlBuildInfo sqlBuildInfo) {
		String tableName = sqlBuildInfo.getTableName();
		return "select count(1) from " + tableName;
	}

}

package com.javaweb.db.mybatis.api.impl.mysql;

import com.javaweb.db.mybatis.interceptor.SqlBuildInfo;
import com.javaweb.db.mybatis.interceptor.SqlHandle;

public class HandleSelectAllCount implements SqlHandle {

	public String handle(SqlBuildInfo sqlBuildInfo) {
		String tableName = sqlBuildInfo.getTableName();
		return "select count(1) from " + tableName;
	}

}

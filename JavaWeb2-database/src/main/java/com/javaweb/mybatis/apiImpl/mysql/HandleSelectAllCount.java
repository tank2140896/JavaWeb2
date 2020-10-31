package com.javaweb.mybatis.apiImpl.mysql;

import com.javaweb.mybatis.apiImpl.SqlBuildInfo;
import com.javaweb.mybatis.apiImpl.SqlHandle;

public class HandleSelectAllCount implements SqlHandle {

	public String handle(SqlBuildInfo sqlBuildInfo) {
		String tableName = sqlBuildInfo.getTableName();
		return "select count(1) from " + tableName;
	}

}

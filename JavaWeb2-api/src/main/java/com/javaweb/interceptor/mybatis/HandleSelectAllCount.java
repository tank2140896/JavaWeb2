package com.javaweb.interceptor.mybatis;

public class HandleSelectAllCount implements SqlHandle {

	public String handle(SqlBuildInfo sqlBuildInfo) {
		String tableName = sqlBuildInfo.getTableName();
		return "select count(1) from " + tableName;
	}

}

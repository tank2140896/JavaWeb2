package com.javaweb.db.mybatis.interceptor;

import com.javaweb.db.mybatis.api.impl.MySqlForSqlString;

@FunctionalInterface
public interface SqlHandle {
	
	public final MySqlForSqlString mySqlForSqlString = new MySqlForSqlString();

	public String handle(SqlBuildInfo sqlBuildInfo);
	
}

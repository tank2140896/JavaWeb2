package com.javaweb.db.mybatis.interceptor;

@FunctionalInterface
public interface SqlHandle {

	public String handle(SqlBuildInfo sqlBuildInfo);
	
}

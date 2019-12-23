package com.javaweb.interceptor.mybatis;

@FunctionalInterface
public interface SqlHandle {

	public String handle(SqlBuildInfo sqlBuildInfo);
	
}

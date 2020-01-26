package com.javaweb.mybatis.apiImpl;

@FunctionalInterface
public interface SqlHandle {

	public String handle(SqlBuildInfo sqlBuildInfo);
	
}

package com.javaweb.db.mybatis.api.impl;

import com.javaweb.db.mybatis.interceptor.SqlBuildInfo;

public interface SqlString {
	
	public String getInsertStringSql(SqlBuildInfo sqlBuildInfo);
	
	public String getUpdateStringSql(SqlBuildInfo sqlBuildInfo);
	
	public String getDeleteStringSql(SqlBuildInfo sqlBuildInfo);
	
	public String getSelectAllStringSql(SqlBuildInfo sqlBuildInfo);
	
	public String getSelectAllCountStringSql(SqlBuildInfo sqlBuildInfo);
	
	public String getSelectByPkStringSql(SqlBuildInfo sqlBuildInfo);

	public String getSelectListStringSql(SqlBuildInfo sqlBuildInfo);
	
	public String getSelectListCountStringSql(SqlBuildInfo sqlBuildInfo);

}

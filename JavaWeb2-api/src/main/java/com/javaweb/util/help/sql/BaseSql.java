package com.javaweb.util.help.sql;

import com.javaweb.util.entity.SqlConnection;

public interface BaseSql {
	
	public boolean executeExport(SqlConnection jdbcBean, String filePath) throws Exception;//导出
	
	public boolean executeImport(SqlConnection jdbcBean, String filePath) throws Exception;//导入

}

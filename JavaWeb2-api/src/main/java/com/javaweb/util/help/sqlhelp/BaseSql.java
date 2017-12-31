package com.javaweb.util.help.sqlhelp;

public interface BaseSql {
	
	public boolean executeExport(SqlConnectionBean jdbcBean, String filePath) throws Exception;//导出
	
	public boolean executeImport(SqlConnectionBean jdbcBean, String filePath) throws Exception;//导入

}

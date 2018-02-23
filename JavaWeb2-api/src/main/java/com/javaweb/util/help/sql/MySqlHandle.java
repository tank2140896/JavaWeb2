package com.javaweb.util.help.sql;

import java.io.OutputStream;
import java.io.OutputStreamWriter;

import com.javaweb.util.entity.SqlConnection;

public class MySqlHandle implements BaseSql {

	public synchronized boolean executeExport(SqlConnection jdbcBean,String filePath) throws Exception {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("mysqldump -P ").append(jdbcBean.getPort()).append(" -h ").append(jdbcBean.getIp())
		            .append(" -u ").append(jdbcBean.getUsername()).append(" -p").append(jdbcBean.getPassword())
		            .append(" ").append(jdbcBean.getDb()).append(" --default-character-set=utf8 ")
		            .append("--lock-tables=false --result-file=").append(filePath);
		Process process = Runtime.getRuntime().exec(stringBuffer.toString());
		if(process.waitFor()==0){
			return true;
		}
		return false;
	}

	public synchronized boolean executeImport(SqlConnection jdbcBean,String filePath) throws Exception {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("mysql -P ").append(jdbcBean.getPort()).append(" -h ").append(jdbcBean.getIp()).append(" -u ")
		            .append(jdbcBean.getUsername()).append(" -p").append(jdbcBean.getPassword()).append(" --default-character-set=utf8");
		StringBuffer stringBuffer2 = new StringBuffer();
		stringBuffer2.append("use ").append(jdbcBean.getDb()).append("\r\n")
					 .append("source ").append(filePath);
		Process process = Runtime.getRuntime().exec(stringBuffer.toString());
		OutputStream os = process.getOutputStream();  
		OutputStreamWriter writer = new OutputStreamWriter(os);  
		writer.write(stringBuffer2.toString());  
		writer.flush();  
		writer.close();  
		os.close();
		if(process.waitFor()==0){
			return true;
		}
		return false;
	}

}

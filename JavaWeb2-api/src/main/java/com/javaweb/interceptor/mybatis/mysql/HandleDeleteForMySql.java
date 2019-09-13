package com.javaweb.interceptor.mybatis.mysql;

import java.util.List;

import com.javaweb.interceptor.mybatis.SqlBuildInfo;
import com.javaweb.interceptor.mybatis.SqlHandle;

public class HandleDeleteForMySql implements SqlHandle {

	public String handle(SqlBuildInfo sqlBuildInfo) {
		String tableName = sqlBuildInfo.getTableName();
		List<String> columnList = sqlBuildInfo.getColumnList();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("delete from ").append(tableName).append(" where ");
		for(int i=0;i<columnList.size();i++){
			if(columnList.get(i).equals(sqlBuildInfo.getPk())){
				stringBuilder.append(sqlBuildInfo.getPk()).append(" = ");
				if(sqlBuildInfo.getParameterValue() instanceof String){
					stringBuilder.append("'"+sqlBuildInfo.getParameterValue()+"'");
				}else{
					stringBuilder.append(sqlBuildInfo.getParameterValue());
				}
				break;
			}
		}
		return stringBuilder.toString();
	}

}

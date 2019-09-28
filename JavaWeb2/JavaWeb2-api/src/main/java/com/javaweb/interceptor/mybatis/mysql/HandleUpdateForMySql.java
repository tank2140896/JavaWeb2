package com.javaweb.interceptor.mybatis.mysql;

import java.util.List;

import com.javaweb.interceptor.mybatis.SqlBuildInfo;
import com.javaweb.interceptor.mybatis.SqlHandle;

public class HandleUpdateForMySql implements SqlHandle {

	public String handle(SqlBuildInfo sqlBuildInfo) {
		String tableName = sqlBuildInfo.getTableName();
		Object pkValue = null;
		List<Object> entityValueList = sqlBuildInfo.getEntityValueList();
		List<String> columnList = sqlBuildInfo.getColumnList();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("update ").append(tableName).append(" set ");
		for(int i=0;i<columnList.size();i++){
			Object eachValue = entityValueList.get(i);
			if(eachValue==null){
				continue;
			}else if(eachValue instanceof String){
				stringBuilder.append(columnList.get(i)).append(" = ").append("'").append(entityValueList.get(i)).append("'");
			}else{
				stringBuilder.append(columnList.get(i)).append(" = ").append(entityValueList.get(i));
			}
			if(i!=entityValueList.size()-1){
				stringBuilder.append(",");
			}
			if(columnList.get(i).equals(sqlBuildInfo.getPk())){
				pkValue = entityValueList.get(i);
			}
		}
		if(pkValue instanceof String){
			stringBuilder.append(" where ").append(sqlBuildInfo.getPk()).append(" = ").append("'").append(pkValue).append("'");
		}else{
			stringBuilder.append(" where ").append(sqlBuildInfo.getPk()).append(" = ").append(pkValue);
		}
		return stringBuilder.toString();
	}

}

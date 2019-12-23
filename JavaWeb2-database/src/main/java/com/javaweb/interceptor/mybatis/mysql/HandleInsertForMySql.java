package com.javaweb.interceptor.mybatis.mysql;

import java.util.List;

import com.javaweb.interceptor.mybatis.SqlBuildInfo;
import com.javaweb.interceptor.mybatis.SqlHandle;

public class HandleInsertForMySql implements SqlHandle {

	public String handle(SqlBuildInfo sqlBuildInfo) {
		String tableName = sqlBuildInfo.getTableName();
		List<Object> entityValueList = sqlBuildInfo.getEntityValueList();
		List<String> columnList = sqlBuildInfo.getColumnList();
		StringBuilder stringBuilder = new StringBuilder();
		if(sqlBuildInfo.getPkGenerate()){//主键自增的处理
			for(int i=0;i<columnList.size();i++){
				if(sqlBuildInfo.getPk().equals(columnList.get(i))){
					columnList.remove(i);
					entityValueList.remove(i);
					break;
				}
			}
		}
		stringBuilder.append("insert into ").append(tableName).append("(");
		for(int i=0;i<columnList.size();i++){
			stringBuilder.append(columnList.get(i));
			if(i!=columnList.size()-1){
				stringBuilder.append(",");
			}
		}
		stringBuilder.append(")").append(" values ").append("(");
		for(int i=0;i<entityValueList.size();i++){
			Object eachValue = entityValueList.get(i);
			if(eachValue==null){
				stringBuilder.append("null");
			}else if(eachValue instanceof String){
				stringBuilder.append("'"+entityValueList.get(i)+"'");
			}else{
				stringBuilder.append(entityValueList.get(i));
			}
			if(i!=entityValueList.size()-1){
				stringBuilder.append(",");
			}
		}
		stringBuilder.append(")");
		return stringBuilder.toString();
	}

}

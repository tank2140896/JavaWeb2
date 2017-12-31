package com.javaweb.interceptor.mybatis;

import java.util.List;

public class HandleSelectByPk implements SqlHandle {

	public String handle(SqlBuildInfo sqlBuildInfo) {
		String tableName = sqlBuildInfo.getTableName();
		Object pkValue = null;
		List<String> entityList = sqlBuildInfo.getEntityList();
		List<Object> entityValueList = sqlBuildInfo.getEntityValueList();
		List<String> columnList = sqlBuildInfo.getColumnList();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select ");
		for(int i=0;i<entityList.size();i++){
			stringBuilder.append(columnList.get(i)).append(" as ").append(entityList.get(i));
			if(i!=entityList.size()-1){
				stringBuilder.append(",");
			}
		}
		stringBuilder.append(" from ").append(tableName).append(" where ");
		for(int i=0;i<columnList.size();i++){
			if(columnList.get(i).equals(sqlBuildInfo.getPk())){
				pkValue = entityValueList.get(i);
				break;
			}
		}
		stringBuilder.append(sqlBuildInfo.getPk()).append(" = ");
		if(pkValue instanceof String){
			stringBuilder.append("'"+pkValue+"'");
		}else{
			stringBuilder.append(pkValue);
		}
		return stringBuilder.toString();
	}

}

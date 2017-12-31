package com.javaweb.interceptor.mybatis;

import java.util.List;

public class HandleDelete implements SqlHandle {

	@Override
	public String handle(SqlBuildInfo sqlBuildInfo) {
		String tableName = sqlBuildInfo.getTableName();
		Object pkValue = null;
		List<Object> entityValueList = sqlBuildInfo.getEntityValueList();
		List<String> columnList = sqlBuildInfo.getColumnList();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("delete from ").append(tableName).append(" where ");
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

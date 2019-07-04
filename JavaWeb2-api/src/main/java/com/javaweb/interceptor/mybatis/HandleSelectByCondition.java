package com.javaweb.interceptor.mybatis;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class HandleSelectByCondition implements SqlHandle {

	@SuppressWarnings("unchecked")
	public String handle(SqlBuildInfo sqlBuildInfo) {
		Map<String,Object> map = (Map<String,Object>)sqlBuildInfo.getParameterValue();
		Set<String> keySet = map.keySet();
		String tableName = sqlBuildInfo.getTableName();
		List<String> entityList = sqlBuildInfo.getEntityList();
		List<String> columnList = sqlBuildInfo.getColumnList();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select ");
		for(int i=0;i<entityList.size();i++){
			stringBuilder.append(columnList.get(i)).append(" as ").append(entityList.get(i));
			if(i!=entityList.size()-1){
				stringBuilder.append(",");
			}
		}
		stringBuilder.append(" from ").append(tableName).append(" where 1=1 ");
		for(String key:keySet) {
		    stringBuilder.append("and ").append(key).append(" ").append(map.get(key));
		}
		return stringBuilder.toString();
	}

}

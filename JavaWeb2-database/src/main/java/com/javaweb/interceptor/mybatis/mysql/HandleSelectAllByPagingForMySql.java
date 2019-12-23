package com.javaweb.interceptor.mybatis.mysql;

import java.util.List;
import java.util.Map;

import com.javaweb.interceptor.mybatis.SqlBuildInfo;
import com.javaweb.interceptor.mybatis.SqlHandle;

public class HandleSelectAllByPagingForMySql implements SqlHandle {

	@SuppressWarnings("unchecked")
	public String handle(SqlBuildInfo sqlBuildInfo) {
		Map<String,Long> map = (Map<String,Long>)sqlBuildInfo.getParameterValue();
		Long currentPage = map.get("currentPage");
		Long pageSize = map.get("pageSize");
		
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
		stringBuilder.append(" from ").append(tableName)
		             .append(" limit ").append((currentPage-1)*pageSize)
		             .append(",").append(pageSize);
		return stringBuilder.toString();
	}

}

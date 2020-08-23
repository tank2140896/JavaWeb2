package com.javaweb.mybatis.apiImpl.mysql;

import java.util.List;

import com.javaweb.constant.CommonConstant;
import com.javaweb.mybatis.apiImpl.SqlBuildInfo;
import com.javaweb.mybatis.apiImpl.SqlHandle;

public class HandleUpdateForMySql implements SqlHandle {

	public String handle(SqlBuildInfo sqlBuildInfo) {
		String tableName = sqlBuildInfo.getTableName();
		Object pkValue = null;
		List<Object> entityValueList = sqlBuildInfo.getEntityValueList();
		List<Boolean> canUpdateSetEmptyList = sqlBuildInfo.getCanUpdateSetEmptyList();
		List<String> columnList = sqlBuildInfo.getColumnList();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("update ").append(tableName).append(" set ");
		for(int i=0;i<columnList.size();i++){
			if(columnList.get(i).equals(sqlBuildInfo.getPk())){
				pkValue = entityValueList.get(i);
			}
			Object eachValue = entityValueList.get(i);
			if(eachValue==null||CommonConstant.EMPTY_VALUE.equals(eachValue.toString().trim())){
				if(canUpdateSetEmptyList.get(i)){
					stringBuilder.append(columnList.get(i)).append(" = ").append(eachValue);
				}else{
					continue;
				}
			}else if(eachValue instanceof String){
				stringBuilder.append(columnList.get(i)).append(" = ").append("'").append(entityValueList.get(i)).append("'");
			}else{
				stringBuilder.append(columnList.get(i)).append(" = ").append(entityValueList.get(i));
			}
			if(i!=entityValueList.size()-1){
				stringBuilder.append(",");
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

package com.javaweb.mybatis.apiImpl.mysql;

import java.util.List;

import com.javaweb.enums.DbTypeEnum;
import com.javaweb.exception.ServiceException;
import com.javaweb.mybatis.apiImpl.SqlBuildInfo;
import com.javaweb.mybatis.apiImpl.SqlHandle;

public class HandleDelete implements SqlHandle {
	
	public String handle(SqlBuildInfo sqlBuildInfo) {
		StringBuilder stringBuilder = new StringBuilder();
		if(DbTypeEnum.MYSQL.equals(sqlBuildInfo.getDbTypeEnum())){
			String tableName = sqlBuildInfo.getTableName();
			List<String> columnList = sqlBuildInfo.getColumnList();
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
		}else{
			throw new ServiceException("目前只支持mysql数据库");
		}
		return stringBuilder.toString();
	}

}

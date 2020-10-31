package com.javaweb.mybatis.apiImpl.mysql;

import java.util.List;

import com.javaweb.enums.DbTypeEnum;
import com.javaweb.exception.ServiceException;
import com.javaweb.mybatis.apiImpl.SqlBuildInfo;
import com.javaweb.mybatis.apiImpl.SqlHandle;

public class HandleSelectAll implements SqlHandle {

	public String handle(SqlBuildInfo sqlBuildInfo) {
		StringBuilder stringBuilder = new StringBuilder();
		if(DbTypeEnum.MYSQL.equals(sqlBuildInfo.getDbTypeEnum())){
			String tableName = sqlBuildInfo.getTableName();
			List<String> entityList = sqlBuildInfo.getEntityList();
			List<String> columnList = sqlBuildInfo.getColumnList();
			stringBuilder.append("select ");
			for(int i=0;i<entityList.size();i++){
				stringBuilder.append(columnList.get(i)).append(" as ").append(entityList.get(i));
				if(i!=entityList.size()-1){
					stringBuilder.append(",");
				}
			}
			stringBuilder.append(" from ").append(tableName);
		}else{
			throw new ServiceException("目前只支持mysql数据库");
		}
		return stringBuilder.toString();
	}

}

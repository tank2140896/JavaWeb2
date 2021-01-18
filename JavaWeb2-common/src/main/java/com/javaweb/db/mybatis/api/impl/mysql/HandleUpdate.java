package com.javaweb.db.mybatis.api.impl.mysql;

import java.util.List;

import com.javaweb.constant.CommonConstant;
import com.javaweb.db.mybatis.interceptor.SqlBuildInfo;
import com.javaweb.db.mybatis.interceptor.SqlHandle;
import com.javaweb.enums.DbTypeEnum;
import com.javaweb.exception.ServiceException;

public class HandleUpdate implements SqlHandle {

	public String handle(SqlBuildInfo sqlBuildInfo) {
		StringBuilder stringBuilder = new StringBuilder();
		if(DbTypeEnum.MYSQL.equals(sqlBuildInfo.getDbTypeEnum())){
			String tableName = sqlBuildInfo.getTableName();
			Object pkValue = null;
			List<Object> entityValueList = sqlBuildInfo.getEntityValueList();
			List<Boolean> canUpdateSetEmptyList = sqlBuildInfo.getCanUpdateSetEmptyList();
			List<String> columnList = sqlBuildInfo.getColumnList();
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
		}else{
			throw new ServiceException("目前只支持mysql数据库");
		}
		return stringBuilder.toString();
	}

}

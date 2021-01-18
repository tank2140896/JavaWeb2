package com.javaweb.db.mybatis.api.impl.mysql;

import java.util.List;

import com.javaweb.db.mybatis.interceptor.SqlBuildInfo;
import com.javaweb.db.mybatis.interceptor.SqlCondition;
import com.javaweb.db.mybatis.interceptor.SqlHandle;
import com.javaweb.db.query.QueryWapper;
import com.javaweb.enums.DbTypeEnum;
import com.javaweb.enums.SqlConditionEnum;
import com.javaweb.exception.ServiceException;

public class HandleSelectListCount implements SqlHandle {

	public String handle(SqlBuildInfo sqlBuildInfo) {
		StringBuilder stringBuilder = new StringBuilder();
		if(DbTypeEnum.MYSQL.equals(sqlBuildInfo.getDbTypeEnum())){
			QueryWapper<?> queryWapper = (QueryWapper<?>)sqlBuildInfo.getParameterValue();
			List<SqlCondition> sqlConditionList = queryWapper.getSqlConditionList();
			String tableName = sqlBuildInfo.getTableName();
			stringBuilder.append("select count(1) from ").append(tableName).append(" where 1=1 ");
			if(sqlConditionList.size()>0){
				for(int i=0;i<sqlConditionList.size();i++){
					SqlCondition sqlCondition = sqlConditionList.get(i);
					if(sqlCondition.getSqlConditionEnum().compareTo(SqlConditionEnum.EQ)==0){
						stringBuilder.append("and ").append(sqlCondition.getColumnName()).append(" = ").append(sqlCondition.getColumnValue() instanceof Number ? (sqlCondition.getColumnValue()) : ("'"+sqlCondition.getColumnValue()+"'"));
						break;
					}
					if(sqlCondition.getSqlConditionEnum().compareTo(SqlConditionEnum.LIKE)==0){
						stringBuilder.append("and ").append(sqlCondition.getColumnName()).append(" like ").append("'%"+sqlCondition.getColumnValue()+"%'");
						break;
					}
					if(sqlCondition.getSqlConditionEnum().compareTo(SqlConditionEnum.LT)==0){
						stringBuilder.append("and ").append(sqlCondition.getColumnName()).append(" < ").append(sqlCondition.getColumnValue() instanceof Number ? (sqlCondition.getColumnValue()) : ("'"+sqlCondition.getColumnValue()+"'"));
						break;
					}
					if(sqlCondition.getSqlConditionEnum().compareTo(SqlConditionEnum.LE)==0){
						stringBuilder.append("and ").append(sqlCondition.getColumnName()).append(" <= ").append(sqlCondition.getColumnValue() instanceof Number ? (sqlCondition.getColumnValue()) : ("'"+sqlCondition.getColumnValue()+"'"));
						break;
					}
					if(sqlCondition.getSqlConditionEnum().compareTo(SqlConditionEnum.GT)==0){
						stringBuilder.append("and ").append(sqlCondition.getColumnName()).append(" > ").append(sqlCondition.getColumnValue() instanceof Number ? (sqlCondition.getColumnValue()) : ("'"+sqlCondition.getColumnValue()+"'"));
						break;
					}
					if(sqlCondition.getSqlConditionEnum().compareTo(SqlConditionEnum.GE)==0){
						stringBuilder.append("and ").append(sqlCondition.getColumnName()).append(" >= ").append(sqlCondition.getColumnValue() instanceof Number ? (sqlCondition.getColumnValue()) : ("'"+sqlCondition.getColumnValue()+"'"));
						break;
					}
				}
			}
		}else{
			throw new ServiceException("目前只支持mysql数据库");
		}
		return stringBuilder.toString();
	}

}

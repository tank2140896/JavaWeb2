package com.javaweb.mybatis.apiImpl.mysql;

import java.util.List;

import com.javaweb.enums.SqlConditionEnum;
import com.javaweb.mybatis.apiImpl.SqlBuildInfo;
import com.javaweb.mybatis.apiImpl.SqlCondition;
import com.javaweb.mybatis.apiImpl.SqlHandle;
import com.javaweb.query.QueryWapper;

public class HandleSelectListForMySql implements SqlHandle {

	public String handle(SqlBuildInfo sqlBuildInfo) {
		QueryWapper<?> queryWapper = (QueryWapper<?>)sqlBuildInfo.getParameterValue();
		List<SqlCondition> sqlConditionList = queryWapper.getSqlConditionList();
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
		if(queryWapper.getIsPaged()){
			stringBuilder.append(" limit "+(queryWapper.getCurrentPage()-1)*queryWapper.getPageSize()+","+queryWapper.getPageSize());
		}
		return stringBuilder.toString();
	}

}

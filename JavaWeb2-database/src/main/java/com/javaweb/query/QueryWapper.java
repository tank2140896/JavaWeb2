package com.javaweb.query;

import java.util.ArrayList;
import java.util.List;

import com.javaweb.enums.SqlConditionEnum;
import com.javaweb.mybatis.apiImpl.SqlCondition;

public class QueryWapper<T> {

	private QueryWapper<T> queryWapper = this;
	
	private long currentPage;
	
	private long pageSize;
	
	private boolean isPaged = false;
	
	private List<SqlCondition> sqlConditionList = new ArrayList<>();
	
	public List<SqlCondition> getSqlConditionList() {
		return sqlConditionList;
	}
	
	public QueryWapper<T> page(long currentPage,long pageSize){
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.isPaged = true;
		return queryWapper;
	}
	
	public long getCurrentPage() {
		return currentPage;
	}

	public long getPageSize() {
		return pageSize;
	}
	
	public boolean getIsPaged() {
		return isPaged;
	}

	public QueryWapper<T> eq(String columnName,Object columnValue){
		sqlConditionList.add(new SqlCondition(SqlConditionEnum.EQ,columnName,columnValue));
		return queryWapper;
	}
	
	public QueryWapper<T> like(String columnName,Object columnValue){
		sqlConditionList.add(new SqlCondition(SqlConditionEnum.LIKE,columnName,columnValue));
		return queryWapper;
	}
	
	public QueryWapper<T> lt(String columnName,Object columnValue){
		sqlConditionList.add(new SqlCondition(SqlConditionEnum.LT,columnName,columnValue));
		return queryWapper;
	}
	
	public QueryWapper<T> le(String columnName,Object columnValue){
		sqlConditionList.add(new SqlCondition(SqlConditionEnum.LE,columnName,columnValue));
		return queryWapper;
	}
	
	public QueryWapper<T> gt(String columnName,Object columnValue){
		sqlConditionList.add(new SqlCondition(SqlConditionEnum.GT,columnName,columnValue));
		return queryWapper;
	}
	
	public QueryWapper<T> ge(String columnName,Object columnValue){
		sqlConditionList.add(new SqlCondition(SqlConditionEnum.GE,columnName,columnValue));
		return queryWapper;
	}
	
}

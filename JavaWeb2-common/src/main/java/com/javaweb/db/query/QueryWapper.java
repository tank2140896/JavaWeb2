package com.javaweb.db.query;

import java.util.ArrayList;
import java.util.List;

import com.javaweb.db.help.SqlCondition;
import com.javaweb.enums.SqlConditionEnum;

public class QueryWapper<T> {
	
	public final long CURRENT_PAGE_VALUE = 1L;
	public final long PAGE_SIZE_VALUE = 10L;
	public final boolean IS_PAGED_VALUE = false;

	private QueryWapper<T> queryWapper = this;
	
	private long currentPage = CURRENT_PAGE_VALUE;
	
	private long pageSize = PAGE_SIZE_VALUE;
	
	private boolean isPaged = IS_PAGED_VALUE;
	
	private List<SqlCondition> sqlConditionList = new ArrayList<>();
	
	public List<SqlCondition> getSqlConditionList() {
		return sqlConditionList;
	}
	
	public QueryWapper<T> clear(){
		this.currentPage = CURRENT_PAGE_VALUE;
		this.pageSize = PAGE_SIZE_VALUE;
		this.isPaged = IS_PAGED_VALUE;
		this.sqlConditionList = new ArrayList<>();
		return queryWapper;
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

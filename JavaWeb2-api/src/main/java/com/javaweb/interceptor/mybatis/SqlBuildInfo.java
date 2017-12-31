package com.javaweb.interceptor.mybatis;

import java.util.List;

public class SqlBuildInfo {
	
	private String tableName;
	
	private String id;
	
	private List<String> entityList;
	
	private List<Object> entityValueList;
	
	private List<String> columnList;
	
	private String pk;
	
	public SqlBuildInfo(String tableName,String id,List<String> entityList,List<Object> entityValueList,List<String> columnList,String pk){
		this.tableName=tableName;
		this.id=id;
		this.entityList=entityList;
		this.entityValueList=entityValueList;
		this.columnList=columnList;
		this.pk=pk;
	}
	
	public String getTableName() {
		return tableName;
	}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public List<String> getEntityList() {
		return entityList;
	}
	
	public void setEntityList(List<String> entityList) {
		this.entityList = entityList;
	}
	
	public List<Object> getEntityValueList() {
		return entityValueList;
	}
	
	public void setEntityValueList(List<Object> entityValueList) {
		this.entityValueList = entityValueList;
	}
	
	public List<String> getColumnList() {
		return columnList;
	}
	
	public void setColumnList(List<String> columnList) {
		this.columnList = columnList;
	}
	
	public String getPk() {
		return pk;
	}
	
	public void setPk(String pk) {
		this.pk = pk;
	}

}

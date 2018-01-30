package com.javaweb.interceptor.mybatis;

import java.util.List;

public class SqlBuildInfo {
	
	private String tableName;//表名
	
	private List<String> entityList;//实体类属性名称
	
	private List<Object> entityValueList;//实体类属性值
	
	private Object parameterValue;//获取参数值
	
	private List<String> columnList;//实体类对应表字段名称
	
	private String pk;//数据库主键名称
	
	private Boolean pkGenerate;//主键是否自增

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
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

	public Object getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(Object parameterValue) {
		this.parameterValue = parameterValue;
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

	public Boolean getPkGenerate() {
		return pkGenerate;
	}

	public void setPkGenerate(Boolean pkGenerate) {
		this.pkGenerate = pkGenerate;
	}
	
}

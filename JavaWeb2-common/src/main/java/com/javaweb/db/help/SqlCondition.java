package com.javaweb.db.help;

import com.javaweb.enums.SqlConditionEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SqlCondition {
	
	private SqlConditionEnum sqlConditionEnum;
	
	private String columnName;
	
	private Object columnValue;
	
	public SqlCondition(){
		
	}
	
	public SqlCondition(SqlConditionEnum sqlConditionEnum,String columnName,Object columnValue){
		this.sqlConditionEnum = sqlConditionEnum;
		this.columnName = columnName;
		this.columnValue = columnValue;
	}

}

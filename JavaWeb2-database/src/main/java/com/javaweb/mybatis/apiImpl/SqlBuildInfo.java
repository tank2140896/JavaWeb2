package com.javaweb.mybatis.apiImpl;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SqlBuildInfo {
	
	private String tableName;//表名
	
	private List<String> entityList;//实体类属性名称
	
	private List<Object> entityValueList;//实体类属性值
	
	private Object parameterValue;//获取参数值
	
	private List<String> columnList;//实体类对应表字段名称
	
	private String pk;//数据库主键名称
	
	private Boolean pkGenerate;//主键是否自增

}

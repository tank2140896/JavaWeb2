package com.javaweb.web.eo.dbTables;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DbTablesColumnListResponse implements Serializable {

	private static final long serialVersionUID = 982904744299610555L;
	
	private String field;//字段名称
	
	private String type;//字段类型
	
	private String isNull;//是否是null
	
	private String isKey;//是否是主键
	
	private String defaultValue;//默认值
	
	private String comment;//字段说明

}

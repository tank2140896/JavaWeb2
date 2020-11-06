package com.javaweb.web.eo.dbTables;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DbTablesListResponse implements Serializable {

	private static final long serialVersionUID = -8756441670983244166L;

	private String tableSchema;//表空间
	
	private String tableName;//表名
	
	private String tableComment;//表说明
	
}

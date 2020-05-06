package com.javaweb.web.eo.dbTables;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DbTablesListRequest implements Serializable {

	private static final long serialVersionUID = 2308443267060140306L;
	
	private String tableName;//表名
	
	private Long currentPage = 1L;//默认当前第1页
	
	private Long pageSize = 10L;//默认每页显示10条

}

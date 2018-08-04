package com.javaweb.config.datasource;

import java.util.HashMap;
import java.util.Map;

public interface DataSourceName {
	
	Map<String,String> DATA_SOURCE_MAP = new HashMap<String,String>(){
		private static final long serialVersionUID = 717496285488091874L;
		{
			put(MYSQL_DS_1,MYSQL_DS_1);	
			put(MYSQL_DS_2,MYSQL_DS_2);	
		}
	};
	
	String MYSQL_DS_1 = "mysql_ds_1";
	
    String MYSQL_DS_2 = "mysql_ds_2"; 

}

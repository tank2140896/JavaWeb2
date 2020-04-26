package com.javaweb.util.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SqlConnection {
	
	private Integer port = 3306;
	
	private String ip = "127.0.0.1";
	
	private String username = "root";
	
	private String password = "root";
	
	private String db = "test";
	
}

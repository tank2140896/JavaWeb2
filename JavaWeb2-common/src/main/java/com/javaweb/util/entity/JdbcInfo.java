package com.javaweb.util.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JdbcInfo {
	
	private String sql;
	
	private String url;
	
	private String username;
	
	private String password;
	
	private Connection connection;
	
	private PreparedStatement preparedStatement;
	
	private ResultSet resultSet;

}

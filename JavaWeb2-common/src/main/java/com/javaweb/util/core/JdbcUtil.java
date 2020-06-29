package com.javaweb.util.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcUtil {
    
    /** 原始JDBC使用
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Class.forName("com.mysql.cj.jdbc.Driver");
    String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&characterSetResults=utf8&autoReconnect=true&allowMultiQueries=true&serverTimezone=GMT%2B8";
    String username = "root";
    String password = "root";
    connection = DriverManager.getConnection(url,username,password);
    String sql = "select * from module where module_name like ?";
    preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1,"%管理%");
    resultSet = preparedStatement.executeQuery();
    while(resultSet.next()) {
        System.out.println(resultSet.getObject(1));
        System.out.println(resultSet.getObject(2));
        System.out.println(resultSet.getObject(3));
    }
    resultSet.close();
    preparedStatement.close();
    connection.close();
    */
	
	/** 封装后的使用示例
	public static void main(String[] args) throws Exception {
		String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&characterSetResults=utf8&autoReconnect=true&allowMultiQueries=true&serverTimezone=GMT%2B8";
	    String username = "root";
	    String password = "root";
		String sql = "select * from sys_module where module_name like ?";
		List<Map<String,Object>> list = JdbcUtil.getResultSet(url, username, password, sql,(preparedStatement)->{
		//List<String> list = JdbcUtil.getColumnNameList(url, username, password, sql,(preparedStatement)->{
	    	try {
				preparedStatement.setString(1,"%管理%");
			} catch (Exception e) {
				//do nothing
			}
	    });
	    System.out.println(list);
	}
	*/
	
    //加载驱动
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver1");
        } catch (ClassNotFoundException e) {
            //do nothing
        }
    }
	
	//获得结果集
	public static List<Map<String,Object>> getResultSet(String url,String username,String password,String sql,PreparedStatementLambda preparedStatementLambda){
		List<Map<String,Object>> list = new ArrayList<>();
		try {
			Connection connection = DriverManager.getConnection(url,username,password);
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatementLambda.excute(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				Map<String,Object> map = new HashMap<>(); 
				ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
				int columnCount = resultSetMetaData.getColumnCount();
				for(int i=1;i<=columnCount;i++){
					map.put(resultSetMetaData.getColumnName(i),resultSet.getObject(i));
				}
				list.add(map);
			}
			if(resultSet!=null){
				resultSet.close();
	    	}
	    	if(preparedStatement!=null){
    			preparedStatement.close();
	    	}
	    	if(connection!=null){
				connection.close();
	    	}
		} catch (Exception e) {
			//do nothing
		}
		return list;
	}
	
	//获得返回字段
	public static List<String> getColumnNameList(String url,String username,String password,String sql,PreparedStatementLambda preparedStatementLambda){
		List<String> columnNameList = new ArrayList<>();
		try{
			Connection connection = DriverManager.getConnection(url,username,password);
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatementLambda.excute(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			int columnCount = resultSetMetaData.getColumnCount();
			for(int i=1;i<=columnCount;i++){
				columnNameList.add(resultSetMetaData.getColumnName(i));
			}
		}catch(Exception e){
			//do nothing
		}
		return columnNameList;
	}
    
    public interface PreparedStatementLambda {
    	
    	public void excute(PreparedStatement preparedStatement);

    }
    
}

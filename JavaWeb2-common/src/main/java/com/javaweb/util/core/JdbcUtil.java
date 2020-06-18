package com.javaweb.util.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaweb.util.entity.JdbcInfo;

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
		String sql = "select * from module where module_name like ?";
	    
	    JdbcInfo jdbcInfo = new JdbcInfo();
	    jdbcInfo.setSql(sql);
	    jdbcInfo.setUrl(url);
	    jdbcInfo.setUsername(username);
	    jdbcInfo.setPassword(password);
	    
	    jdbcInfo = getResultSet(jdbcInfo,(preparedStatement)->{
	    	try {
				preparedStatement.setString(1,"%管理%");
			} catch (Exception e) {
				//do nothing
			}
	    });
	    ResultSet resultSet = jdbcInfo.getResultSet();
	    while(resultSet.next()) {
	         System.out.println(resultSet.getObject(1));
	         System.out.println(resultSet.getObject(2));
	         System.out.println(resultSet.getObject(3));
	    }
	    close(jdbcInfo);
	}
	*/
	
	//获得结果集
	public static JdbcInfo getResultSet(JdbcInfo jdbcInfo,PreparedStatementLambda preparedStatementLambda){
		try {
			jdbcInfo.setConnection(getConnection(jdbcInfo));
			PreparedStatement preparedStatement = jdbcInfo.getConnection().prepareStatement(jdbcInfo.getSql());
			preparedStatementLambda.excute(preparedStatement);
			jdbcInfo.setPreparedStatement(preparedStatement);
			jdbcInfo.setResultSet(preparedStatement.executeQuery());
		} catch (Exception e) {
			//do nothing
		}
		return jdbcInfo;
	}
	
	//获得返回字段
	public static List<String> getColumnNameList(ResultSet resultSet){
		List<String> columnNameList = new ArrayList<>();
		try{
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
    
    //加载驱动
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            //do nothing
        }
    }
    
    //获得连接
    public static Connection getConnection(JdbcInfo jdbcInfo) throws SQLException{
    	return DriverManager.getConnection(jdbcInfo.getUrl(),jdbcInfo.getUsername(),jdbcInfo.getPassword());
    }
    
    //关闭连接
    public static void close(JdbcInfo jdbcInfo){
    	ResultSet resultSet = jdbcInfo.getResultSet();
    	PreparedStatement preparedStatement = jdbcInfo.getPreparedStatement();
    	Connection connection = jdbcInfo.getConnection();
    	if(resultSet!=null){
    		try {
				resultSet.close();
			} catch (SQLException e) {
				//do nothing
			}
    	}
    	if(preparedStatement!=null){
    		try {
    			preparedStatement.close();
			} catch (SQLException e) {
				//do nothing
			}
    	}
    	if(connection!=null){
    		try {
				connection.close();
			} catch (SQLException e) {
				//do nothing
			}
    	}
    }
    
    public interface PreparedStatementLambda {
    	
    	public void excute(PreparedStatement preparedStatement);

    }
    
}
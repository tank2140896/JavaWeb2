package com.javaweb.util.core;

public class JdbcUtil {
    
    /**
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
    
    //加载驱动
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            //do nothing
        }
    }
    
}

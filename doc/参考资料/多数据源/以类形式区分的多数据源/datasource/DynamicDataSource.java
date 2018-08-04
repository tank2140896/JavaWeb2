package com.javaweb.config.datasource;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
	
    private static final ThreadLocal<String> dataSourceKey = new ThreadLocal<>();
    
    //实现抽象类AbstractRoutingDataSource中的方法
    protected Object determineCurrentLookupKey() {
        return getDataSource();
    }
 
    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object,Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }
 
    public static void setDataSource(String dataSource) {
    	dataSourceKey.set(dataSource);
    }
 
    public static String getDataSource() {
        return dataSourceKey.get();
    }
 
    public static void clearDataSource() {
    	dataSourceKey.remove();
    }

}

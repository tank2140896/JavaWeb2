package com.javaweb.config.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSource;
import com.javaweb.interceptor.MyBatisBaseDaoInterceptor;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {
	
    @Value("${mybatis.mapperLocations}")
    private String mybatisMapperLocations;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private MysqlDateSource1 mysqlDateSource1;
	
	@Autowired
	private MysqlDateSource2 mysqlDateSource2;
	
	@Autowired
	private MyBatisBaseDaoInterceptor myBatisBaseDaoInterceptor;

	/**
	@Bean("mysql_d1")
	@ConfigurationProperties(prefix="application.server.db.slave")
	public DataSource mysql_d1(){
		//在配置文件（如application.properties）中添加如：application.server.db.slave.username=root
		return DataSourceBuilder.create().build();
	}
	*/
	@Bean("mysql_d1")
	public DataSource mysql_d1(){
		HikariDataSource hikariDataSource = new HikariDataSource();
		hikariDataSource.setJdbcUrl(mysqlDateSource1.getUrl());
		hikariDataSource.setDriverClassName(mysqlDateSource1.getDriverClassName());
		hikariDataSource.setUsername(mysqlDateSource1.getUsername());
		hikariDataSource.setPassword(mysqlDateSource1.getPassword());
		hikariDataSource.setMinimumIdle(mysqlDateSource1.getMinimumIdle());
		hikariDataSource.setMaximumPoolSize(mysqlDateSource1.getMaximumPoolSize());
		hikariDataSource.setMaxLifetime(mysqlDateSource1.getMaxLifetime());
		return hikariDataSource;
	}
	
	@Bean("mysql_d2")
	public DataSource mysql_d2(){
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setUrl(mysqlDateSource2.getUrl());
		druidDataSource.setDriverClassName(mysqlDateSource2.getDriverClassName());
		druidDataSource.setUsername(mysqlDateSource2.getUsername());
		druidDataSource.setPassword(mysqlDateSource2.getPassword());
		druidDataSource.setInitialSize(mysqlDateSource2.getInitialSize());
		druidDataSource.setMinIdle(mysqlDateSource2.getMinIdle());
		druidDataSource.setMaxActive(mysqlDateSource2.getMaxActive());
		druidDataSource.setTestWhileIdle(mysqlDateSource2.getTestWhileIdle());
		druidDataSource.setMaxWait(mysqlDateSource2.getMaxWait());
		return druidDataSource;
	}
	
	@Primary
	@Bean("multipleDataSourceManage")
    public MultipleDataSourceManage multipleDataSourceManage() {
		MultipleDataSourceManage multipleDataSourceManage = new MultipleDataSourceManage();
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put("mysql_d1",mysql_d1());
        dataSourceMap.put("mysql_d2",mysql_d2());
        //将mysql_d1数据源作为默认指定的数据源
        multipleDataSourceManage.setDefaultTargetDataSource(mysql_d1());
        multipleDataSourceManage.setTargetDataSources(dataSourceMap);
        //将数据源的 key放到数据源上下文的key集合中，用于切换时判断数据源是否有效
        return multipleDataSourceManage;
    }
	
	@Bean
    public SqlSessionFactory sqlSessionFactory(MultipleDataSourceManage multipleDataSourceManage) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(multipleDataSourceManage);
        sqlSessionFactoryBean.setTypeAliasesPackage(environment.getProperty("mybatis.typeAliasesPackage"));//environment.getProperty("mybatis.typeAliasesPackage",String.class)
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mybatisMapperLocations));
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{myBatisBaseDaoInterceptor});
        return sqlSessionFactoryBean.getObject();
    }
	
}

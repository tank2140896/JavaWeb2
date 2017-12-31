package com.javaweb.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSource;
import com.javaweb.config.datasource.MultipleDataSourceManage;
import com.javaweb.interceptor.mybatis.MyBatisBaseDaoInterceptor;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private MyBatisBaseDaoInterceptor myBatisBaseDaoInterceptor;

	@Bean("mysql_d1")
	//@ConfigurationProperties(prefix="application.server.db.slave")
	public DataSource mysql_d1(){
		HikariDataSource hikariDataSource = new HikariDataSource();
		hikariDataSource.setJdbcUrl(environment.getProperty("datasource.mysql.d1.url"));
		hikariDataSource.setDriverClassName(environment.getProperty("datasource.mysql.d1.driverClassName"));
		hikariDataSource.setUsername(environment.getProperty("datasource.mysql.d1.username"));
		hikariDataSource.setPassword(environment.getProperty("datasource.mysql.d1.password"));
		hikariDataSource.setMinimumIdle(environment.getProperty("datasource.mysql.d1.minimumIdle",Integer.class));
		hikariDataSource.setMaximumPoolSize(environment.getProperty("datasource.mysql.d1.maximumPoolSize",Integer.class));
		hikariDataSource.setMaxLifetime(environment.getProperty("datasource.mysql.d1.maxLifetime",Long.class));
		return hikariDataSource;
		//return DataSourceBuilder.create().build();
	}
	
	@Bean("mysql_d2")
	public DataSource mysql_d2(){
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setUrl(environment.getProperty("datasource.mysql.d2.url"));
		druidDataSource.setDriverClassName(environment.getProperty("datasource.mysql.d2.driverClassName"));
		druidDataSource.setUsername(environment.getProperty("datasource.mysql.d2.username"));
		druidDataSource.setPassword(environment.getProperty("datasource.mysql.d2.password"));
		druidDataSource.setInitialSize(environment.getProperty("datasource.mysql.d2.initialSize",Integer.class));
		druidDataSource.setMinIdle(environment.getProperty("datasource.mysql.d2.minIdle",Integer.class));
		druidDataSource.setMaxActive(environment.getProperty("datasource.mysql.d2.maxActive",Integer.class));
		druidDataSource.setTestWhileIdle(environment.getProperty("datasource.mysql.d2.testWhileIdle",Boolean.class));
		druidDataSource.setMaxWait(environment.getProperty("datasource.mysql.d2.maxWait",Long.class));
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
        sqlSessionFactoryBean.setTypeAliasesPackage(environment.getProperty("mybatis.typeAliasesPackage"));
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(environment.getProperty("mybatis.mapperLocations")));
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{myBatisBaseDaoInterceptor});
        return sqlSessionFactoryBean.getObject();
    }
	
}

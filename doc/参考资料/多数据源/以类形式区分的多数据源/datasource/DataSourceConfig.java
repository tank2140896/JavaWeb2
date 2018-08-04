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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSource;
import com.javaweb.interceptor.MyBatisBaseDaoInterceptor;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {
	
    @Value("${mybatis.mapperLocations}")
    private String mybatisMapperLocations;
    
    @Value("${mybatis.typeAliasesPackage}")
    private String typeAliasesPackage;
	
	@Autowired
	private MysqlDateSource1 mysqlDateSource1;
	
	@Autowired
	private MysqlDateSource2 mysqlDateSource2;
	
	@Autowired
	private MyBatisBaseDaoInterceptor myBatisBaseDaoInterceptor;

	/**
	 * 还可以这么写:
	 * @Bean("mysql_d1")  
	 * @ConfigurationProperties(prefix="application.server.db.slave")
	 * public DataSource mysql_d1(){
	 *     //在配置文件（如application.properties）中添加如：application.server.db.slave.username=root
	 * 	   return DataSourceBuilder.create().build();
	 * }
	 */
	@Bean("mysql_ds_1")
	public DataSource mysql_ds_1(){
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
	
	@Bean("mysql_ds_2")
	public DataSource mysql_ds_2(){
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
	@Bean("dynamicDataSource")
    public DynamicDataSource multipleDataSourceManage() {
        Map<Object,Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put("mysql_ds_1",mysql_ds_1());
        dataSourceMap.put("mysql_ds_2",mysql_ds_2());
        return new DynamicDataSource(mysql_ds_1(),dataSourceMap);//若不指定,默认使用数据源mysql_d1
    }
	
	@Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);
        /**
         * 还可以这么写:
         * @Autowired
         * private Environment environment;
         * sqlSessionFactoryBean.setTypeAliasesPackage(environment.getProperty("mybatis.typeAliasesPackage"));
         * sqlSessionFactoryBean.setTypeAliasesPackage(environment.getProperty("mybatis.typeAliasesPackage",String.class));
         */
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mybatisMapperLocations));
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{myBatisBaseDaoInterceptor});
        return sqlSessionFactoryBean.getObject();
    }
	
}

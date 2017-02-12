package com.javaweb.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(basePackages="com.javaweb.dao.datasource1",sqlSessionTemplateRef="datasource1SqlSessionTemplate")
public class DataSource1Config {

	@Primary
    @Bean(name="datasource1")
    @ConfigurationProperties(prefix="spring.datasource.db1")
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

	@Primary
    @Bean(name="datasource1SqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("datasource1") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/datasource1/*/*.xml"));
        return bean.getObject();
    }

	@Primary
    @Bean(name="datasource1TransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("datasource1") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

	@Primary
    @Bean(name="datasource1SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("datasource1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
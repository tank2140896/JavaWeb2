package com.javaweb.config.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * 注意点:
 * 1.如果只写成@ConfigurationProperties,那么属性需要加上@Value("${datasource.mysql.d2.url}")
 * 2.如果不指定配置文件@PropertySource({"classpath:application.properties"}),那么就读取默认激活的配置文件
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix="datasource.mysql.d2")
public class MysqlDateSource2 {
	
	private String url;
	
	private String driverClassName;
	
	private String username;
	
	private String password;
	
	private Integer initialSize;
	
	private Integer minIdle;
	
	private Integer maxActive;
	
	private Boolean testWhileIdle;
	
	private Long maxWait;

}

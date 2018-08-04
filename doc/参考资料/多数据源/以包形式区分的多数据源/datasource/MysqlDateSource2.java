package com.javaweb.config.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 注意点:
 * 1.如果只写成@ConfigurationProperties,那么属性需要加上@Value("${datasource.mysql.d2.url}")
 * 2.如果不指定配置文件@PropertySource({"classpath:application.properties"}),那么就读取默认激活的配置文件
 */
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getInitialSize() {
		return initialSize;
	}

	public void setInitialSize(Integer initialSize) {
		this.initialSize = initialSize;
	}

	public Integer getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(Integer minIdle) {
		this.minIdle = minIdle;
	}

	public Integer getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(Integer maxActive) {
		this.maxActive = maxActive;
	}

	public Boolean getTestWhileIdle() {
		return testWhileIdle;
	}

	public void setTestWhileIdle(Boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}

	public Long getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(Long maxWait) {
		this.maxWait = maxWait;
	}
	
}

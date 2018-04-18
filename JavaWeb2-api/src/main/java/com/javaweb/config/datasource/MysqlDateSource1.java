package com.javaweb.config.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="datasource.mysql.d1")
public class MysqlDateSource1 {
	
	private String url;
	
	private String driverClassName;
	
	private String username;
	
	private String password;
	
	private Integer minimumIdle;
	
	private Integer maximumPoolSize;
	
	private Long maxLifetime;

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

	public Integer getMinimumIdle() {
		return minimumIdle;
	}

	public void setMinimumIdle(Integer minimumIdle) {
		this.minimumIdle = minimumIdle;
	}

	public Integer getMaximumPoolSize() {
		return maximumPoolSize;
	}

	public void setMaximumPoolSize(Integer maximumPoolSize) {
		this.maximumPoolSize = maximumPoolSize;
	}

	public Long getMaxLifetime() {
		return maxLifetime;
	}

	public void setMaxLifetime(Long maxLifetime) {
		this.maxLifetime = maxLifetime;
	}
	
}

package com.javaweb.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationContextConfig {
	
	@Bean
	public ApplicationContextHelper applicationContextHelper(){
		return new ApplicationContextHelper();
	}

}

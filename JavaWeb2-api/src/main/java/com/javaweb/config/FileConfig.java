package com.javaweb.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileConfig {
	
	@Bean
    public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory multipartConfigFactory = new MultipartConfigFactory();
		multipartConfigFactory.setMaxFileSize("10240KB");//设置单个文件上传大小
		multipartConfigFactory.setMaxRequestSize("102400KB");//总上传大小
		return multipartConfigFactory.createMultipartConfig();
    }

}

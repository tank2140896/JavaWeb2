package com.javaweb.config.file;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

@Configuration
public class FileConfig {
	
	@Value("${multipart.file.maxFileSize}")
	private String maxFileSize;
	
	@Value("${multipart.file.maxRequestSize}")
	private String maxRequestSize;
	
	@Bean
    public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory multipartConfigFactory = new MultipartConfigFactory();
		multipartConfigFactory.setMaxFileSize(DataSize.parse(maxFileSize));//设置单个文件上传大小
		multipartConfigFactory.setMaxRequestSize(DataSize.parse(maxRequestSize));//总上传大小
		return multipartConfigFactory.createMultipartConfig();
    }

}

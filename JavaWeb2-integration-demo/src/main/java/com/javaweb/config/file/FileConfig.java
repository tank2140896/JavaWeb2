package com.javaweb.config.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;

@Configuration
public class FileConfig {
	
	@Value("${multipart.file.maxFileSize}")
	private String maxFileSize;
	
	@Value("${multipart.file.maxRequestSize}")
	private String maxRequestSize;
	
	/**
	@Bean
    public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory multipartConfigFactory = new MultipartConfigFactory();
		multipartConfigFactory.setMaxFileSize(DataSize.parse(maxFileSize));//设置单个文件上传大小
		multipartConfigFactory.setMaxRequestSize(DataSize.parse(maxRequestSize));//设置总文件上传大小
		return multipartConfigFactory.createMultipartConfig();
    }
    */
	
	//commons-fileupload写法
	@Bean(name="multipartResolver")
	public MultipartResolver multipartResolver() {
		System.out.println(maxFileSize);
		System.out.println(maxRequestSize);
		CustomMultipartResolver customMultipartResolver = new CustomMultipartResolver();
		customMultipartResolver.setMaxUploadSizePerFile(1024*1024);//设置单个文件上传大小
		customMultipartResolver.setMaxUploadSize(1024*1024);//设置总文件上传大小
		return customMultipartResolver;
	}

}

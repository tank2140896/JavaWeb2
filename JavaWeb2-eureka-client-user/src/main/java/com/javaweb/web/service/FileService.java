package com.javaweb.web.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.javaweb.web.po.User;

public interface FileService {
	
	public List<String> uploadFile(MultipartFile multipartFile[],String rootPath,User user) throws Exception;
	
}

package com.javaweb.web.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.file.FileContentListRequest;
import com.javaweb.web.eo.file.FileListRequest;
import com.javaweb.web.po.User;

public interface FileService {
	
	public List<String> uploadFile(MultipartFile multipartFile[],String rootPath,User user) throws Exception;
	
	public Page list(FileListRequest fileListRequest);

	public Page contentList(FileContentListRequest fileContentListRequest);
	
	public com.javaweb.web.po.File fileDetail(String id);
	
	public void fileDelete(String id);
	
}

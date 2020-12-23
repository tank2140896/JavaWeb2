package com.javaweb.web.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaweb.base.BaseService;
import com.javaweb.constant.SystemConstant;
import com.javaweb.util.core.DateUtil;
import com.javaweb.util.core.FileUtil;
import com.javaweb.util.core.SecretUtil;
import com.javaweb.web.po.User;
import com.javaweb.web.service.FileService;

@Service("fileServiceImpl")
public class FileServiceImpl extends BaseService implements FileService {

	public List<String> uploadFile(MultipartFile multipartFile[],String rootPath,User user) throws Exception {
		final String fileSerNo = UUID.randomUUID().toString();//文件批次号（同一批次上传的多个文件的批次号应该相同）
		List<String> list = new ArrayList<>();
		if(multipartFile!=null&&multipartFile.length>0){
    		for(int i=0;i<multipartFile.length;i++){
    			String id = SecretUtil.defaultGenUniqueStr(SystemConstant.SYSTEM_NO);//文件唯一ID
    			String originFileName = multipartFile[i].getOriginalFilename();//原上传文件名称
    			String newFileName = UUID.randomUUID().toString()+"_"+originFileName;//新文件名称
    			String filePath = rootPath+newFileName;//文件全路径
    			FileUtil.writeFile(multipartFile[i].getInputStream(),new byte[1024],new File(filePath));
    			com.javaweb.web.po.File fileEntity = new com.javaweb.web.po.File();
    			fileEntity.setId(id);
    			fileEntity.setSystemId(SystemConstant.SYSTEM_NO);
    			fileEntity.setFileUniqueIndex(UUID.randomUUID().toString());
    			fileEntity.setOriginFileName(originFileName);
    			fileEntity.setCurrentFileName(newFileName);
    			fileEntity.setFilePath(rootPath);
    			fileEntity.setFileFullPath(filePath);
    			fileEntity.setFileSuffix(fileEntity.getOriginFileName().split("\\.")[fileEntity.getOriginFileName().split("\\.").length-1]);
    			fileEntity.setFileSize(multipartFile[i].getSize());
    			fileEntity.setFileUnit("byte");
    			fileEntity.setFileSerNo(fileSerNo);
    			fileEntity.setFileUseType(1);
    			fileEntity.setCreator(user.getCreator());
    			fileEntity.setCreateDate(DateUtil.getDefaultDate());
    			fileDao.insert(fileEntity);
    			list.add(id);
    		}
    	}
		return list;
	}
	
}

package com.javaweb.web.eo.file;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileListResponse implements Serializable {

	private static final long serialVersionUID = -2015286652094508961L;

	private String id;//主键ID
	
	private String originFileName;//原始文件名称
	
	private Long fileSize;//文件大小
	
	private String createDate;//创建时间

	private String fileSizeAndFileUnit;//文件大小和单位
	
	private String filePath;//文件路径

}

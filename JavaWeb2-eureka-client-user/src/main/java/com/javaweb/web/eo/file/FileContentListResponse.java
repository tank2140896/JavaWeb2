package com.javaweb.web.eo.file;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileContentListResponse implements Serializable {

	private static final long serialVersionUID = -6719266890740544233L;

	private String id;//主键ID（只有文件该字段才有值）
	
	private String originFileName;//原始文件名称（只有文件该字段才有值）
	
	private String createDate;//创建时间（只有文件该字段才有值）

	private String fileSizeAndFileUnit;//文件大小和单位（只有文件该字段才有值）

	private String filePath;//文件路径（只有文件该字段才有值）

	private String name;//文件或目录名称
	
	private boolean file;//是否是文件

	private String fileAbsolutePath;//文件绝对路径
	
}

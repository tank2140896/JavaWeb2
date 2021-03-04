package com.javaweb.web.eo.file;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileContentListRequest implements Serializable {

	private static final long serialVersionUID = 7202081449442738214L;
	
	private String folderPath;//文件目录路径

	private Long currentPage = 1L;//默认当前第1页
	
	private Long pageSize = 10L;//默认每页显示10条

}

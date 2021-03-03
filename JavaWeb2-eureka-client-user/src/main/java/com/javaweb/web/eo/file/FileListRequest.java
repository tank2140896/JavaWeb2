package com.javaweb.web.eo.file;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileListRequest implements Serializable {

	private static final long serialVersionUID = -2687232983308745956L;
	
	private String fileName;//文件名称
    
	private Long currentPage = 1L;//默认当前第1页
	
	private Long pageSize = 10L;//默认每页显示10条

}

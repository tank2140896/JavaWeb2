package com.javaweb.web.eo.module;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuleListRequest implements Serializable {
	
	private static final long serialVersionUID = -4159852987835551961L;

	private String moduleName;//模块名称
	
	private String pageUrl;//页面URL
	
	private String apiUrl;//API请求URL
	
	private String moduleType;//模块类型
	
	private String alias;//别名
	
	private String createStartDate;//模块创建的开始日期
	
	private String createEndDate;//模块创建的结束日期
	
	private Long currentPage = 1L;//默认当前第1页
	
	private Long pageSize = 10L;//默认每页显示10条
	
}

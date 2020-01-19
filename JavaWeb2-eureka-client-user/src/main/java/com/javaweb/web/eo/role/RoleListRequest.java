package com.javaweb.web.eo.role;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleListRequest implements Serializable {
	
	private static final long serialVersionUID = -250839408653592074L;

	private String roleName;//角色名称
	
	private String createStartDate;//角色创建的开始日期
	
	private String createEndDate;//角色创建的结束日期
	
	private Long currentPage = 1L;//默认当前第1页
	
	private Long pageSize = 10L;//默认每页显示10条
	
}

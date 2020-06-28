package com.javaweb.web.eo.user;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserListRequest implements Serializable {
	
	private static final long serialVersionUID = -3616575788300683386L;

	private String userName;//用户名
	
	private String personName;//用户姓名

    private String roleName;//角色名
	
	private String createStartDate;//用户创建的开始日期
	
	private String createEndDate;//用户创建的结束日期
	
	private Long currentPage = 1L;//默认当前第1页
	
	private Long pageSize = 10L;//默认每页显示10条

}

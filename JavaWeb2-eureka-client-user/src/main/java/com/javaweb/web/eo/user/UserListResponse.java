package com.javaweb.web.eo.user;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserListResponse implements Serializable {
	
	private static final long serialVersionUID = -7480353722420364562L;

	private String userId;//用户ID
	
	private String userName;//用户名
	
	private String personName;//用户姓名
	
	private String status;//账号状态
	
	private String createDate;//注册日期
	
	private String roleName;//角色名称
	
}

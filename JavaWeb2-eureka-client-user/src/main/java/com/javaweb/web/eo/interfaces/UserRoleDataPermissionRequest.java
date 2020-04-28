package com.javaweb.web.eo.interfaces;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRoleDataPermissionRequest implements Serializable {
    
	private static final long serialVersionUID = -7835589966896417806L;

	private String interfacesId;//接口ID
    
	private String userName;//用户名
    
	private String roleName;//角色名
    
	private Integer type;//1：用户列表获取；2：角色列表获取
    
	private Long currentPage = 1L;//默认当前第1页
	
	private Long pageSize = 10L;//默认每页显示10条
    
}

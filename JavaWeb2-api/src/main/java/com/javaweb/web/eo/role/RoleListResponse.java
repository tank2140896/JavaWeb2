package com.javaweb.web.eo.role;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleListResponse implements Serializable {
	
	private static final long serialVersionUID = -3589773690897290520L;

	private String roleId;//角色ID
	
	private String roleName;//角色名
	
	private String createDate;//注册日期

}

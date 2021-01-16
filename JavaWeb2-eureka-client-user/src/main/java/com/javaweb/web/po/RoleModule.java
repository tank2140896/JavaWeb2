package com.javaweb.web.po;

import java.io.Serializable;

import com.javaweb.annotation.sql.Column;
import com.javaweb.annotation.sql.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="sys_role_module")
public class RoleModule implements Serializable {

	private static final long serialVersionUID = 6708103631349460475L;

	@Column(name="id",pk=true,columnDesc="主键ID")
	private String id;//主键ID
	
	@Column(name="role_id",columnDesc="角色ID")
	private String roleId;//角色ID
	
	@Column(name="module_id",columnDesc="模块ID")
	private String moduleId;//模块ID
	
	public static final String idColumn = "id";
	public static final String roleIdColumn = "role_id";
	public static final String moduleIdColumn = "module_id";

}

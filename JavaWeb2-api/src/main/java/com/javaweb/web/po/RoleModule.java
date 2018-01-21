package com.javaweb.web.po;

import java.io.Serializable;

import com.javaweb.interceptor.mybatis.Column;
import com.javaweb.interceptor.mybatis.Table;

@Table(name="role_module")
public class RoleModule implements Serializable {

	private static final long serialVersionUID = 6708103631349460475L;

	@Column(name="id",pk=true)
	private String id;//主键ID
	
	@Column(name="role_id")
	private String roleId;//角色ID
	
	@Column(name="module_id")
	private String moduleId;//模块ID

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	
}

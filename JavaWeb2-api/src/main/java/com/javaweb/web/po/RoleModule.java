package com.javaweb.web.po;

import com.javaweb.interceptor.mybatis.Column;
import com.javaweb.interceptor.mybatis.Pk;
import com.javaweb.interceptor.mybatis.Table;

@Table(name="role_module")
public class RoleModule {

	@Column(name="id")
	@Pk(name="id")
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

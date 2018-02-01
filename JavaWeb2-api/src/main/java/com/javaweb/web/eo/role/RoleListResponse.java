package com.javaweb.web.eo.role;

public class RoleListResponse {
	
	private String roleId;//角色ID
	
	private String roleName;//角色名
	
	private String createDate;//注册日期

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
}

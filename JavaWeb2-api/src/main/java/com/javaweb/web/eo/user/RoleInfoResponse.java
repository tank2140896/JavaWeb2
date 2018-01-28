package com.javaweb.web.eo.user;

public class RoleInfoResponse {
	
	private String roleId;
	
	private String roleName;
	
	private boolean checkFlag;//false表示未被选中；true表示被选中

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

	public boolean isCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(boolean checkFlag) {
		this.checkFlag = checkFlag;
	}

}

package com.javaweb.web.po;

import com.javaweb.interceptor.mybatis.Column;
import com.javaweb.interceptor.mybatis.Pk;
import com.javaweb.interceptor.mybatis.Table;

@Table(name="user_role")
public class UserRole {
	
	@Column(name="id")
	@Pk(name="id")
	private String id;//主键ID
	
	@Column(name="user_id")
	private String userId;//用户ID

	@Column(name="role_id")
	private String roleId;//角色ID

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
}

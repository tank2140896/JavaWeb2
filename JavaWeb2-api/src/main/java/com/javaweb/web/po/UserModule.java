package com.javaweb.web.po;

import java.io.Serializable;

import com.javaweb.interceptor.mybatis.Column;
import com.javaweb.interceptor.mybatis.Table;

@Table(name="user_module")
public class UserModule implements Serializable {

	private static final long serialVersionUID = -3308661987883600632L;

	@Column(name="id",pk=true)
	private String id;//主键ID
	
	@Column(name="user_id")
	private String userId;//用户ID
	
	@Column(name="module_id")
	private String moduleId;//模块ID

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

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

}

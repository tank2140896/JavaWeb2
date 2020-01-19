package com.javaweb.web.po;

import java.io.Serializable;

import com.javaweb.annotation.sql.Column;
import com.javaweb.annotation.sql.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="role_module")
public class RoleModule implements Serializable {

	private static final long serialVersionUID = 6708103631349460475L;

	@Column(name="id",pk=true)
	private String id;//主键ID
	
	@Column(name="role_id")
	private String roleId;//角色ID
	
	@Column(name="module_id")
	private String moduleId;//模块ID

}

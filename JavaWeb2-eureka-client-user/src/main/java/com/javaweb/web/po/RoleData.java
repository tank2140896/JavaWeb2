package com.javaweb.web.po;

import java.io.Serializable;

import com.javaweb.annotation.sql.Column;
import com.javaweb.annotation.sql.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="sys_role_data")
public class RoleData implements Serializable {

	private static final long serialVersionUID = -8720034407531582856L;

	@Column(name="id",pk=true)
	private String id;//主键ID
	
	@Column(name="role_id")
	private String roleId;//角色ID

	@Column(name="data_permission_id")
	private String dataPermissionId;//数据权限ID
	
}

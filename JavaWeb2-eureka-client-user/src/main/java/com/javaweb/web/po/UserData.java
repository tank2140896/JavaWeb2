package com.javaweb.web.po;

import java.io.Serializable;

import com.javaweb.annotation.sql.Column;
import com.javaweb.annotation.sql.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="sys_user_data")
public class UserData implements Serializable {

	private static final long serialVersionUID = 3816531177960720557L;

	@Column(name="id",pk=true,columnDesc="主键ID")
	private String id;//主键ID
	
	@Column(name="user_id",columnDesc="用户ID")
	private String userId;//用户ID

	@Column(name="data_permission_id",columnDesc="数据权限ID")
	private String dataPermissionId;//数据权限ID
	
	public static final String idColumn = "id";
	public static final String userIdColumn = "user_id";
	public static final String dataPermissionIdColumn = "data_permission_id";
	
}

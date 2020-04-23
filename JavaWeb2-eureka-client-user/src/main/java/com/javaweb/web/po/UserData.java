package com.javaweb.web.po;

import java.io.Serializable;

import com.javaweb.annotation.sql.Column;
import com.javaweb.annotation.sql.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="user_data")
public class UserData implements Serializable {

	private static final long serialVersionUID = 3816531177960720557L;

	@Column(name="id",pk=true)
	private String id;//主键ID
	
	@Column(name="user_id")
	private String userId;//用户ID

	@Column(name="data_permission_id")
	private String dataPermissionId;//数据权限ID
	
}

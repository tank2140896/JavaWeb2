package com.javaweb.web.po;

import java.io.Serializable;

import com.javaweb.annotation.sql.Column;
import com.javaweb.annotation.sql.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="user_role")
public class UserRole implements Serializable {

	private static final long serialVersionUID = -362334579286862491L;

	@Column(name="id",pk=true)
	private String id;//主键ID
	
	@Column(name="user_id")
	private String userId;//用户ID

	@Column(name="role_id")
	private String roleId;//角色ID
	
	@Column(name="strategy")
	private Integer strategy = 1;//权限获取策略(0:自定义;1:并集;2:交集;3:以用户权限为准;4:以角色权限为准;其它:默认为未定义,作为并集处理)

}

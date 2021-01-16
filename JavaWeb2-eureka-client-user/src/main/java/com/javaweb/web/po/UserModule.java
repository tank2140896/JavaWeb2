package com.javaweb.web.po;

import java.io.Serializable;

import com.javaweb.annotation.sql.Column;
import com.javaweb.annotation.sql.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="sys_user_module")
public class UserModule implements Serializable {

	private static final long serialVersionUID = -3308661987883600632L;

	@Column(name="id",pk=true,columnDesc="主键ID")
	private String id;//主键ID
	
	@Column(name="user_id",columnDesc="用户ID")
	private String userId;//用户ID
	
	@Column(name="module_id",columnDesc="模块ID")
	private String moduleId;//模块ID
	
	public static final String idColumn = "id";
	public static final String userIdColumn = "user_id";
	public static final String moduleIdColumn = "module_id";

}

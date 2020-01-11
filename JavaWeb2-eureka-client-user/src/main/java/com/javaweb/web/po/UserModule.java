package com.javaweb.web.po;

import java.io.Serializable;

import com.javaweb.annotation.sql.Column;
import com.javaweb.annotation.sql.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="user_module")
public class UserModule implements Serializable {

	private static final long serialVersionUID = -3308661987883600632L;

	@Column(name="id",pk=true)
	private String id;//主键ID
	
	@Column(name="user_id")
	private String userId;//用户ID
	
	@Column(name="module_id")
	private String moduleId;//模块ID

}

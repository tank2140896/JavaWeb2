package com.javaweb.web.po;

import java.io.Serializable;

import com.javaweb.annotation.sql.Column;
import com.javaweb.annotation.sql.Table;
import com.javaweb.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="interfaces")
public class Interfaces extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -627334587177260300L;
	
	@Column(name="id",pk=true)
	private String id;//主键ID
	
	@Column(name="name")
	private String name;//接口名称
	
	@Column(name="url")
	private String url;//URL
	
	@Column(name="method")
	private String method;//方法名称
	
	@Column(name="remark")
	private String remark;//备注

}

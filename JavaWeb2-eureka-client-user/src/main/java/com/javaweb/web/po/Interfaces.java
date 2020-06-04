package com.javaweb.web.po;

import java.io.Serializable;
import java.math.BigInteger;

import com.javaweb.annotation.sql.Column;
import com.javaweb.annotation.sql.Table;
import com.javaweb.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="sys_interfaces")
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
	
	@Column(name="times")
	private String times;//时间
	
	@Column(name="unit")
	private String unit;//单位
	
	@Column(name="counts")
	private String counts;//次数
	
	@Column(name="data_permission")
	private Integer dataPermission;//数据权限（0:无数据权限；1：有数据权限）
	
	@Column(name="entity")
	private String entity;//返回的实体类（主要数据对象）
	
	@Column(name="history_times")
	private BigInteger historyTimes;//历史接口被调用次数
	
	@Column(name="remark")
	private String remark;//备注

}

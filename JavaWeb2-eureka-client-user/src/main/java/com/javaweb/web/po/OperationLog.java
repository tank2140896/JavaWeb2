package com.javaweb.web.po;

import java.io.Serializable;

import com.javaweb.annotation.sql.Column;
import com.javaweb.annotation.sql.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="operation_log")
public class OperationLog implements Serializable {

	private static final long serialVersionUID = 6824826467869255030L;

	@Column(name="id",pk=true)
	private String id;//主键ID
	
	@Column(name="user_id")
	private String userId;//用户ID
	
	@Column(name="url")
	private String url;//请求地址
	
	@Column(name="request_parameter")
	private String requestParameter;//请求参数
	
	@Column(name="request_ip_address")
	private String requestIpAddress;//请求IP地址
	
	@Column(name="request_time")
	private String requestTime;//请求时间
	
	@Column(name="remark")
	private String remark;//备注
	
}

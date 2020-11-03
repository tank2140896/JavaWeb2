package com.javaweb.web.po;

import java.io.Serializable;

import com.javaweb.annotation.sql.Column;
import com.javaweb.annotation.sql.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="sys_operation_log")
public class OperationLog implements Serializable {

	private static final long serialVersionUID = 6824826467869255030L;

	@Column(name="id",pk=true,columnDesc="主键ID")
	private String id;//主键ID
	
	@Column(name="user_id",columnDesc="用户ID")
	private String userId;//用户ID
	
	@Column(name="url",columnDesc="请求地址")
	private String url;//请求地址

	@Column(name="request_method",columnDesc="请求方法")
	private String requestMethod;//请求方法
	
	@Column(name="request_parameter",columnDesc="请求参数")
	private String requestParameter;//请求参数
	
	@Column(name="request_ip_address",columnDesc="请求IP地址")
	private String requestIpAddress;//请求IP地址
	
	@Column(name="request_time",columnDesc="请求时间")
	private String requestTime;//请求时间
	
	@Column(name="remark",columnDesc="备注")
	private String remark;//备注
	
}

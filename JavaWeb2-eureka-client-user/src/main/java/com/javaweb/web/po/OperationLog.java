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
	
	@Column(name="login_way",columnDesc="登录方式（1：账号密码登录（默认）；2：二维码扫码登录；3：短信验证码登录）")
	private Integer loginWay;//登录方式（1：账号密码登录（默认）；2：二维码扫码登录；3：短信验证码登录）
	
	@Column(name="client_type",columnDesc="客户端类型（1：页面端（默认）；2：安卓端；3：IOS端）")
	private Integer clientType;//客户端类型（1：页面端（默认）；2：安卓端；3：IOS端）
	
	@Column(name="remark",columnDesc="备注")
	private String remark;//备注
	
	public static final String idColumn = "id";
	public static final String userIdColumn = "user_id";
	public static final String urlColumn = "url";
	public static final String requestMethodColumn = "request_method";
	public static final String requestParameterColumn = "request_parameter";
	public static final String requestIpAddressColumn = "request_ip_address";
	public static final String requestTimeColumn = "request_time";
	public static final String loginWayColumn = "login_way";
	public static final String clientTypeColumn = "client_type";
	public static final String remarkColumn = "remark";
	
}

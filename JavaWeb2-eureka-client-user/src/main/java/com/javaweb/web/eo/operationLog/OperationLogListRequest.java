package com.javaweb.web.eo.operationLog;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperationLogListRequest implements Serializable {

	private static final long serialVersionUID = -1259214841753949424L;
	
	private String userName;//用户名
	
	private String requestIpAddress;//请求IP地址
	
	private Long currentPage = 1L;//默认当前第1页
	
	private Long pageSize = 10L;//默认每页显示10条
	
}

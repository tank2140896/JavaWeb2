package com.javaweb.web.eo.operationLog;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperationLogListResponse implements Serializable {

	private static final long serialVersionUID = 3580792040945061093L;
	
	private String userName;
	
	private String url;
	
	private String requestIpAddress;
	
	private String requestTime;
	
}

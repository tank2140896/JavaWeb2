package com.javaweb.web.eo.interfaces;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InterfacesTestRequest implements Serializable {
    
	private static final long serialVersionUID = -2969389053289017989L;

	private String requestUrl;//请求URL
    
    private String requestType;//请求类型
    
    private String requestHeader;//请求头
    
    private String requestBody;//请求体
    
}

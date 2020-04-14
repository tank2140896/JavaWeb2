package com.javaweb.web.eo.interfaces;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InterfacesListRequest implements Serializable {
    
	private static final long serialVersionUID = 410569567005457022L;

	private String name;//接口名称
    
    private String url;//URL
    
    private String method;//方法名称
    
	private Long currentPage = 1L;//默认当前第1页
	
	private Long pageSize = 10L;//默认每页显示10条
    
}

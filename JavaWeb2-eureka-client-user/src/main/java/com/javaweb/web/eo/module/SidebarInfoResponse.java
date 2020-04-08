package com.javaweb.web.eo.module;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SidebarInfoResponse implements Serializable {

	private static final long serialVersionUID = 4557533500032668636L;
	
	private String moduleId;
	
	private String parentId;

	private String moduleName;
	
	private String pageUrl;
	
	private String icon;
	
	private List<SidebarInfoResponse> list;
	
}

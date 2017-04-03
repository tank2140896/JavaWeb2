package com.javaweb.dataobject.eo;

import java.util.List;

public class UserRoleModule {
	
	private String moduleId;
	
	private String moduleName;
	
	private String pageUrl;
	
	private String apiUrl;
	
	private String moduleType;
	
	private String parentId;
	
	private String icon;
	
	private List<UserRoleModule> list;

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<UserRoleModule> getList() {
		return list;
	}

	public void setList(List<UserRoleModule> list) {
		this.list = list;
	}

}
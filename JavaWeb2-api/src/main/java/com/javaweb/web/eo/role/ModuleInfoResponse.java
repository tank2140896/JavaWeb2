package com.javaweb.web.eo.role;

import java.util.List;

public class ModuleInfoResponse {
	
	private boolean checkFlag;
	
	private String moduleName;
	
	private String moduleId;
	
	private String parentId;
	
	private List<ModuleInfoResponse> list;

	public boolean isCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(boolean checkFlag) {
		this.checkFlag = checkFlag;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public List<ModuleInfoResponse> getList() {
		return list;
	}

	public void setList(List<ModuleInfoResponse> list) {
		this.list = list;
	}

}

package com.javaweb.dataobject.eo;

import java.util.List;

public class UserRoleModule {
	
	private String moduleid;
	
	private String modulename;
	
	private String moduleurl;
	
	private String moduletype;
	
	private String parentid;
	
	private String icon;
	
	private List<UserRoleModule> list;

	public String getModuleid() {
		return moduleid;
	}

	public void setModuleid(String moduleid) {
		this.moduleid = moduleid;
	}

	public String getModulename() {
		return modulename;
	}

	public void setModulename(String modulename) {
		this.modulename = modulename;
	}

	public String getModuleurl() {
		return moduleurl;
	}

	public void setModuleurl(String moduleurl) {
		this.moduleurl = moduleurl;
	}

	public String getModuletype() {
		return moduletype;
	}

	public void setModuletype(String moduletype) {
		this.moduletype = moduletype;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
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
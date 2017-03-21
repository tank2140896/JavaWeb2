package com.javaweb.dataobject.po;

import com.javaweb.base.BaseEntity;

public class Module extends BaseEntity {
	
	private String moduleId;//模块ID
	
	private String moduleName;//模块名称
	
	private String moduleUrl = null;//模块URL
	
	private String parentId = null;//模块的上级ID
	
	private String fcode = null;//层级关系
	
	private int level = 0;//第几级(0表示未定义层级数;层级数1为最高,即根节点)
	
	private int orders = 0;//模块顺序(0表示没有顺序;顺序从1开始)
	
	private int moduleType = 0;//模块类型(0:未定义模块类型；1：菜单；2：操作)
	
	private String alias = null;//别名
	
	private String parentAlias = null;//上级别名
	
	private String remark = null;//备注

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

	public String getModuleUrl() {
		return moduleUrl;
	}

	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getFcode() {
		return fcode;
	}

	public void setFcode(String fcode) {
		this.fcode = fcode;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	public int getModuleType() {
		return moduleType;
	}

	public void setModuleType(int moduleType) {
		this.moduleType = moduleType;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getParentAlias() {
		return parentAlias;
	}

	public void setParentAlias(String parentAlias) {
		this.parentAlias = parentAlias;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}

package com.javaweb.dataobject.po;

import com.javaweb.base.BaseEntity;

public class Role extends BaseEntity {

	private String roleId;//角色ID,主键(必填)
	
	private String roleName;//角色名称(必填)
	
	private String parentId = null;//角色的上级ID
	
	private String fcode = null;//层级关系
	
	private Integer level = 0;//第几级(0表示未定义层级数;层级数1为最高,即根节点)
	
	private String remark = null;//备注

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}

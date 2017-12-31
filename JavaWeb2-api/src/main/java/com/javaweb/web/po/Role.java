package com.javaweb.web.po;

import javax.validation.constraints.NotNull;

import com.javaweb.base.BaseEntity;
import com.javaweb.interceptor.mybatis.Column;
import com.javaweb.interceptor.mybatis.Pk;
import com.javaweb.interceptor.mybatis.Table;

@Table(name="role")
public class Role extends BaseEntity {

	@NotNull(groups={update.class,delete.class},message="validated.Role.roleId.NotNull")
	@Column(name="role_id")
	@Pk(name="role_id")
	private String roleId;//角色ID
	
	@NotNull(groups={add.class,update.class},message="validated.Role.roleName.NotNull")
	@Column(name="role_name")
	private String roleName;//角色名称
	
	@Column(name="parent_id")
	private String parentId;//角色的上级ID
	
	@Column(name="fcode")
	private String fcode;//层级关系
	
	@Column(name="level")
	private Integer level = 0;//第几级(0表示未定义层级数;层级数1为最高,即根节点)
	
	@Column(name="type")
	private Integer type = 0;//类型(0表示未定义类型)
	
	@Column(name="remark")
	private String remark;//备注

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}

package com.javaweb.web.po;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.javaweb.annotation.sql.Column;
import com.javaweb.annotation.sql.Table;
import com.javaweb.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="sys_role")
public class Role extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 7161573735090231747L;

	@NotNull(groups={update.class,delete.class},message="validated.role.roleId.notNull")
	@Column(name="role_id",pk=true)
	private String roleId;//角色ID
	
	@NotNull(groups={add.class,update.class},message="validated.role.roleName.notNull")
	@Column(name="role_name")
	private String roleName;//角色名称
	
	@Column(name="parent_id")
	private String parentId;//角色的上级ID
	
	@Column(name="fcode")
	private String fcode;//层级关系
	
	@Column(name="level")
	private Integer level = 0;//第几级(0表示未定义层级数;层级数1为最高,即根节点)
	
	@Column(name="type")
	private Integer type = 0;//类型(0:未定义类型,作为全端通用接口使用;1:PC端;2:安卓端;3:IOS端) 
	
	@Column(name="system_id")
	private String systemId;//系统ID
	
	@Column(name="remark")
	private String remark;//备注

}

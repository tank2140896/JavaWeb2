package com.javaweb.web.po;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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

	@NotEmpty(groups={update.class},message="validated.role.roleId.notEmpty")
	@Column(name="role_id",pk=true,idAutoCreate=true,columnDesc="角色ID")
	private String roleId;//角色ID
	
	@NotEmpty(groups={add.class,update.class},message="validated.role.roleName.notEmpty")
	@Size(groups={add.class,update.class},max=20,message="validated.role.roleName.maxLength.limit")
	@Column(name="role_name",columnDesc="角色名称")
	private String roleName;//角色名称
	
	@NotEmpty(groups={add.class,update.class},message="validated.role.roleCode.notEmpty")
	@Size(groups={add.class,update.class},max=20,message="validated.role.roleCode.maxLength.limit")
	@Column(name="role_code",columnDesc="角色代码")
	private String roleCode;//角色代码
	
	@Column(name="parent_id",columnDesc="角色的上级ID")
	private String parentId;//角色的上级ID
	
	@Column(name="fcode",columnDesc="层级关系")
	private String fcode;//层级关系
	
	@Column(name="level",columnDesc="第几级(0表示未定义层级数;层级数1为最高,即根节点)")
	private Integer level = 0;//第几级(0表示未定义层级数;层级数1为最高,即根节点)
	
	@Column(name="type",columnDesc="类型(0:未定义类型,作为全端通用接口使用;1:PC端;2:安卓端;3:IOS端)")
	private Integer type = 0;//类型(0:未定义类型,作为全端通用接口使用;1:PC端;2:安卓端;3:IOS端) 
	
	@Column(name="system_id",columnDesc="系统ID")
	private String systemId;//系统ID
	
	@Column(name="remark",columnDesc="备注")
	private String remark;//备注
	
	public static final String roleIdColumn = "role_id";
	public static final String roleNameColumn = "role_name";
	public static final String roleCodeColumn = "role_code";
	public static final String parentIdColumn = "parent_id";
	public static final String fcodeColumn = "fcode";
	public static final String levelColumn = "level";
	public static final String typeColumn = "type";
	public static final String systemIdColumn = "system_id";
	public static final String remarkColumn = "remark";

}

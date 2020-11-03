package com.javaweb.web.po;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.javaweb.annotation.sql.Column;
import com.javaweb.annotation.sql.Table;
import com.javaweb.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="sys_user")
public class User extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = -696227785545506331L;

	@NotEmpty(groups={update.class},message="validated.user.userId.notEmpty")
	@Column(name="user_id",pk=true,columnDesc="用户ID")
	private String userId;//用户ID
	
	@NotEmpty(groups={add.class,update.class},message="validated.user.userName.notNull")
	@Pattern(groups={add.class,update.class},regexp="^(?![^a-zA-Z]+$)(?!\\D+$).{6,20}$",message="validated.user.userName.pattern")
	@Column(name="user_name",columnDesc="用户名")
	private String userName;//用户名
	
	@NotEmpty(groups={add.class},message="validated.user.password.notNull")
	@Pattern(groups={add.class},regexp="^(?![^a-zA-Z]+$)(?!\\D+$).{6,20}$",message="validated.user.password.pattern")
	@Column(name="password",columnDesc="用户密码")
	private String password;//用户密码
	
	@Column(name="person_name",columnDesc="用户姓名")
	private String personName;//用户姓名

	@Column(name="email",columnDesc="电子邮箱")
	private String email;//电子邮箱
	
	@Column(name="phone",columnDesc="手机号码")
	private String phone;//手机号码
	
	@Column(name="portrait",columnDesc="头像")
	private String portrait;//头像
	
	@Column(name="parent_id",columnDesc="创建该用户的ID")
	private String parentId;//创建该用户的ID
	
	@Column(name="fcode",columnDesc="层级关系")
	private String fcode;//层级关系
	
	@Column(name="level",columnDesc="第几级(层级数0为最高,即根节点)")
	private Integer level;//第几级(层级数0为最高,即根节点)
	
	@Column(name="remark",columnDesc="备注")
	private String remark;//备注
	
	@Column(name="status",columnDesc="账号状态(0:正常；1：禁用)")
	private Integer status = 0;//账号状态(0:正常；1：禁用)

}

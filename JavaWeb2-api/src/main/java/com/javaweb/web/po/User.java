package com.javaweb.web.po;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.javaweb.base.BaseEntity;
import com.javaweb.interceptor.mybatis.Column;
import com.javaweb.interceptor.mybatis.Pk;
import com.javaweb.interceptor.mybatis.Table;

@Table(name="user")
public class User extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = -696227785545506331L;

	@NotNull(groups={update.class,delete.class},message="validated.User.userId.NotNull")
	@Pk(name="user_id")
	@Column(name="user_id")
	private String userId;//用户ID
	
	@NotNull(groups={add.class,update.class},message="validated.User.userName.NotNull")
	@Pattern(groups={add.class,update.class},regexp="^(?![^a-zA-Z]+$)(?!\\D+$).{6,20}$",message="validated.User.userName.Pattern")
	@Column(name="user_name")
	private String userName;//用户名
	
	@NotNull(groups={add.class,update.class},message="validated.User.password.NotNull")
	@Pattern(groups={add.class,update.class},regexp="^(?![^a-zA-Z]+$)(?!\\D+$).{6,20}$",message="validated.User.password.Pattern")
	@Column(name="password")
	private String password;//用户密码
	
	@Column(name="person_name")
	private String personName;//用户姓名

	@Column(name="email")
	private String email;//电子邮箱
	
	@Column(name="phone")
	private String phone;//手机号码
	
	@Column(name="portrait")
	private String portrait;//头像
	
	@Column(name="parent_id")
	private String parentId;//创建该用户的ID
	
	@Column(name="fcode")
	private String fcode;//层级关系
	
	@Column(name="level")
	private Integer level = 0;//第几级(0表示未定义层级数;层级数1为最高,即根节点)
	
	@Column(name="remark")
	private String remark;//备注
	
	@Column(name="status")
	private Integer status = 0;//账号状态(0:正常)

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}

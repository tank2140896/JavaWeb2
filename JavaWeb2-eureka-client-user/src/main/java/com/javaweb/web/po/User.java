package com.javaweb.web.po;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	@Column(name="user_id",pk=true,idAutoCreate=true,columnDesc="用户ID")
	private String userId;//用户ID

	@NotEmpty(groups={add.class,update.class},message="validated.user.userName.notNull")
	@Pattern(groups={add.class,update.class},regexp="^(?![^a-zA-Z]+$)(?!\\D+$).{6,20}$",message="validated.user.userName.pattern")
	@Column(name="user_name",columnDesc="用户名")
	private String userName;//用户名
	
	@NotEmpty(groups={add.class},message="validated.user.password.notNull")
	@Pattern(groups={add.class},regexp="^(?![^a-zA-Z]+$)(?!\\D+$).{6,20}$",message="validated.user.password.pattern")
	@Column(name="password",columnDesc="用户密码")
	private String password;//用户密码
	
	@NotEmpty(groups={add.class,update.class},message="validated.user.personName.notNull")
	@Size(groups={add.class,update.class},max=20,message="validated.user.personName.maxLength.limit")
	@Column(name="person_name",columnDesc="用户姓名")
	private String personName;//用户姓名
	
	@Size(groups={add.class,update.class},max=20,message="validated.user.idCard.maxLength.limit")
	@Column(name="id_card",columnDesc="身份证号码")
	private String idCard;//身份证号码

	@Size(groups={add.class,update.class},max=50,message="validated.user.email.maxLength.limit")
	@Column(name="email",columnDesc="电子邮箱")
	private String email;//电子邮箱
	
	@Size(groups={add.class,update.class},max=20,message="validated.user.phone.maxLength.limit")
	@Column(name="phone",columnDesc="手机号码")
	private String phone;//手机号码
	
	@Column(name="portrait",columnDesc="头像")
	private String portrait;//头像
	
	@Column(name="parent_id",columnDesc="创建该用户的ID")
	private String parentId;//创建该用户的ID
	
	@Column(name="fcode",columnDesc="层级关系")
	private String fcode;//层级关系
	
	@Column(name="level",columnDesc="第几级（层级数0为最高，即根节点）")
	private Integer level;//第几级（层级数0为最高，即根节点）
	
	@Size(groups={add.class,update.class},max=100,message="validated.user.remark.maxLength.limit")
	@Column(name="remark",columnDesc="备注")
	private String remark;//备注
	
	@Column(name="status",columnDesc="账号状态（0：正常；1：禁用）")
	private Integer status = 0;//账号状态（0：正常；1：禁用）
	
	@Column(name="real_name_status",columnDesc="实名认证状态（0：未实名认证；1：已实名认证）")
	private Integer realNameStatus = 0;//实名认证状态（0：未实名认证；1：已实名认证）
	
	public static final String userIdColumn = "user_id";
	public static final String userNameColumn = "user_name";
	public static final String passwordColumn = "password";
	public static final String personNameColumn = "person_name";
	public static final String idCardColumn = "id_card";
	public static final String emailColumn = "email";
	public static final String phoneColumn = "phone";
	public static final String portraitColumn = "portrait";
	public static final String parentIdColumn = "parent_id";
	public static final String fcodeColumn = "fcode";
	public static final String levelColumn = "level";
	public static final String remarkColumn = "remark";
	public static final String statusColumn = "status";
	public static final String realNameStatusColumn = "real_name_status";

}

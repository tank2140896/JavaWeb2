package com.javaweb.web.po;

import com.javaweb.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends BaseEntity {
	
	private String userId;//用户ID
	
	private String userName;//用户名
	
	private String password;//用户密码
	
	private String personName;//用户姓名

	private String email;//电子邮箱
	
	private String phone;//手机号码
	
	private String portrait;//头像
	
	private String parentId;//创建该用户的ID
	
	private String fcode;//层级关系
	
	private Integer level;//第几级(层级数0为最高,即根节点)
	
	private String remark;//备注
	
	private Integer status = 0;//账号状态(0:正常)

}

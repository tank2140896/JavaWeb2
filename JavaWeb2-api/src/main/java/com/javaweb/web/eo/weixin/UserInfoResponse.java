package com.javaweb.web.eo.weixin;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoResponse implements Serializable {
	
	private static final long serialVersionUID = -4652612488851638161L;

	private String openid;
	
	private String nickname;//new String(nickname.getBytes("ISO-8859-1"),"UTF-8")
	
	private String sex;
	
	private String language;
	
	private String province;
	
	private String city;
	
	private String country;
	
	private String headimgurl;
	
	private String privilege[];
	
	private String unionid;

}


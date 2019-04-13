package com.javaweb.web.eo.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.javaweb.validate.UserLoginRequestTypeValidate;

public class UserLoginRequest {

	@NotNull(message="validated.user.userName.notNull")
	@Pattern(regexp="^(?![^a-zA-Z]+$)(?!\\D+$).{6,20}$",message="validated.user.userName.pattern")
	private String username;
	
	@NotNull(message="validated.user.password.notNull")
	@Pattern(regexp="^(?![^a-zA-Z]+$)(?!\\D+$).{6,20}$",message="validated.user.password.pattern")
	private String password;
	
	@UserLoginRequestTypeValidate(easyWayCheck=false,message="validated.user.type.pattern")//自定义校验
	private String type;
	
	/** ↓↓↓↓↓↓↓↓↓↓kaptcha和uuid仅在需要验证码时才会有用,此处没有用,可以不传↓↓↓↓↓↓↓↓↓↓ */
	private String kaptcha;
	
	private String uuid;
	/** ↑↑↑↑↑↑↑↑↑↑kaptcha和uuid仅在需要验证码时才会有用,此处没有用,可以不传↑↑↑↑↑↑↑↑↑↑ */

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKaptcha() {
		return kaptcha;
	}

	public void setKaptcha(String kaptcha) {
		this.kaptcha = kaptcha;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}

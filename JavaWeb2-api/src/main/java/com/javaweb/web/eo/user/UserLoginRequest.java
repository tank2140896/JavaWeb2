package com.javaweb.web.eo.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserLoginRequest {

	@NotNull(message="validated.userLogin.userName.notNull")
	@Pattern(regexp="^(?![^a-zA-Z]+$)(?!\\D+$).{6,20}$",message="validated.user.userName.pattern")
	private String username;
	
	@NotNull(message="validated.userLogin.password.notNull")
	@Pattern(regexp="^(?![^a-zA-Z]+$)(?!\\D+$).{6,20}$",message="validated.user.userName.pattern")
	private String password;
	
	@Pattern(regexp="^[1-9]$",message="validated.user.type.pattern")
	private String type;
	
	private String kaptcha;
	
	private String uuid;

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

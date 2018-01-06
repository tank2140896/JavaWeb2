package com.javaweb.web.eo.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserLogin {

	@NotNull(message="validated.UserLogin.userName.NotNull")
	@Pattern(regexp="^(?![^a-zA-Z]+$)(?!\\D+$).{6,20}$",message="validated.User.userName.Pattern")
	private String userName;
	
	@NotNull(message="validated.UserLogin.password.NotNull")
	@Pattern(regexp="^(?![^a-zA-Z]+$)(?!\\D+$).{6,20}$",message="validated.User.userName.Pattern")
	private String password;

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
	
}

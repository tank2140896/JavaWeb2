package com.javaweb.web.eo.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserLogin {

	@NotNull(message="validated.UserLogin.userName.NotNull")
	@Pattern(regexp="^(?![^a-zA-Z]+$)(?!\\D+$).{6,20}$",message="validated.User.userName.Pattern")
	private String username;
	
	@NotNull(message="validated.UserLogin.password.NotNull")
	@Pattern(regexp="^(?![^a-zA-Z]+$)(?!\\D+$).{6,20}$",message="validated.User.userName.Pattern")
	private String password;

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

}

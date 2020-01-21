package com.javaweb.web.eo.user;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.javaweb.annotation.validate.userServer.UserLoginRequestTypeValidate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequest implements Serializable {

	private static final long serialVersionUID = 8226342751622290262L;

	@NotNull(message="validated.user.userName.notNull")
	@Pattern(regexp="^(?![^a-zA-Z]+$)(?!\\D+$).{6,20}$",message="validated.user.userName.pattern")
	private String username;
	
	@NotNull(message="validated.user.password.notNull")
	@Pattern(regexp="^(?![^a-zA-Z]+$)(?!\\D+$).{6,20}$",message="validated.user.password.pattern")
	private String password;
	
	@UserLoginRequestTypeValidate(easyWayCheck=false,message="validated.user.type.pattern")//自定义校验
	private String type = "1";
	
	private String kaptcha;
	
	private String requestId;
	
}

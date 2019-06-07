package com.javaweb.web.eo.user;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.javaweb.validate.UserLoginRequestTypeValidate;

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
	private String type;
	
	/** ↓↓↓↓↓↓↓↓↓↓kaptcha和uuid仅在需要验证码时才会有用,此处没有用,可以不传↓↓↓↓↓↓↓↓↓↓ */
	private String kaptcha;
	
	private String uuid;
	/** ↑↑↑↑↑↑↑↑↑↑kaptcha和uuid仅在需要验证码时才会有用,此处没有用,可以不传↑↑↑↑↑↑↑↑↑↑ */

}

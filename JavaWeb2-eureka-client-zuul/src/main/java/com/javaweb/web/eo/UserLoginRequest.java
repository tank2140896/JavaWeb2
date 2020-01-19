package com.javaweb.web.eo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequest {

	private String username;
	
	private String password;
	
	private String type = "1";
	
	private String kaptcha;
	
	private String requestId;
	
}

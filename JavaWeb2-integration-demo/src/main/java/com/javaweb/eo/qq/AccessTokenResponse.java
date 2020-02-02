package com.javaweb.eo.qq;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessTokenResponse implements Serializable {
	
	private static final long serialVersionUID = -7918331211060714457L;

	private String access_token;
	
	private String expires_in;
	
	private String refresh_token;

}

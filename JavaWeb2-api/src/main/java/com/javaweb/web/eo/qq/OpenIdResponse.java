package com.javaweb.web.eo.qq;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpenIdResponse implements Serializable {
	
	private static final long serialVersionUID = -7775434452528244988L;

	private String client_id;
	
	private String openid;
	
}

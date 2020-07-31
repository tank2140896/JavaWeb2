package com.javaweb.eo.wechat;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FaceRequestConfigResponse implements Serializable {

	private static final long serialVersionUID = 8048790250120618862L;

	private String timestamp;
	
	private String nonceStr;
	
	private String signature;
	
	private String url;
	
	private String jsapiTicket;

}

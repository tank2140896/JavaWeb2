package com.javaweb.eo.wechat;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendUserMessageRequest implements Serializable {

	private static final long serialVersionUID = -2432357470831136895L;

	private String openId;
	
	private String content;
	
	private String kefuAccount;
	
}

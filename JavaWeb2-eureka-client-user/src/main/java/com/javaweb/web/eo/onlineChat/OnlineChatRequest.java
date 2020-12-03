package com.javaweb.web.eo.onlineChat;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnlineChatRequest implements Serializable {

	private static final long serialVersionUID = 5497518240634911209L;
	
	private String userId;

	private String userName;
	
	private String message;
	
	private String token;

}

package com.javaweb.web.eo.onlineChat;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnlineChatResponse implements Serializable {

	private static final long serialVersionUID = -1299212621697598042L;

	private String userName;
	
	private String message;
	
	private String userId;
	
}

package com.javaweb.web.eo.chat;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatResponse implements Serializable {
	
	private static final long serialVersionUID = 4674387970245411096L;

	private String message;
	
	private String userId;
	
	private String userName;

}

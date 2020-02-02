package com.javaweb.eo.chat;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRequest implements Serializable {
	
	private static final long serialVersionUID = 2869748071041867629L;
	
	private String message;

}

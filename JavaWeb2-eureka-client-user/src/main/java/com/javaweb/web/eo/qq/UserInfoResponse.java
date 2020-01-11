package com.javaweb.web.eo.qq;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoResponse implements Serializable {
	
	private static final long serialVersionUID = 3585524851107109729L;

	private String ret;
	
	private String msg;
	
	private String nickname;//new String(nickname.getBytes("ISO-8859-1"),"UTF-8")

	//还有其它参数
	
}


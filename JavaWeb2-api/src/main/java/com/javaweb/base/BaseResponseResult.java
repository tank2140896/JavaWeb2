package com.javaweb.base;

import java.io.Serializable;

public class BaseResponseResult implements Serializable {

	private static final long serialVersionUID = -3883891531784970360L;

	private Object code;
	
	private String message;
	
	private Object data;
	
	public BaseResponseResult(){
		
	}
	
	public BaseResponseResult(Object code,String message,Object data){
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public Object getCode() {
		return code;
	}

	public void setCode(Object code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}

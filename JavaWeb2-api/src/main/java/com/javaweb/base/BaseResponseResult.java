package com.javaweb.base;

public class BaseResponseResult {

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

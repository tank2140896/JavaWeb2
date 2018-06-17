package com.javaweb.base;

import java.io.Serializable;

public class BaseResponseResult implements Serializable {

	private static final long serialVersionUID = -3883891531784970360L;

	private Object code;//返回码
	
	private Object message;//返回信息
	
	private Object data;//返回数据
	
	public BaseResponseResult(){
		
	}
	
	public BaseResponseResult(Object code,Object message){
		this.code = code;
		this.message = message;
	}
	
	public BaseResponseResult(Object code,Object message,Object data){
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

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}

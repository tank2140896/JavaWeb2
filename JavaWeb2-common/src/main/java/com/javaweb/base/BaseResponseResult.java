package com.javaweb.base;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponseResult implements Serializable {

	private static final long serialVersionUID = -3883891531784970360L;

	private Object code;//返回码
	
	private Object message;//返回信息
	
	private Object data;//返回数据
	
	public BaseResponseResult(){
		
	}
	
	public BaseResponseResult(Object code){
		this(code,null,null);
	}
	
	public BaseResponseResult(Object code,Object message){
		this(code,message,null);
	}
	
	public BaseResponseResult(Object code,Object message,Object data){
		this.code = code;
		this.message = message;
		this.data = data;
	}

}

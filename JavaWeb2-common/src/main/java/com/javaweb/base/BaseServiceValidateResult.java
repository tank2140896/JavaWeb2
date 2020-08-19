package com.javaweb.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseServiceValidateResult {

	private boolean validatePass;//是否校验通过
	
	private String message;//返回的校验信息
	
	public BaseServiceValidateResult(){
		
	}
	
	public BaseServiceValidateResult(boolean validatePasse){
		this(validatePasse,null); 
	}
	
	public BaseServiceValidateResult(boolean validatePass,String message){
		this.validatePass = validatePass;
		this.message = message; 
	}

}

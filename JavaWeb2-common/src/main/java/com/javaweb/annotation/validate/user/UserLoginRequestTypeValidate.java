package com.javaweb.annotation.validate.user;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.regex.Pattern;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import com.javaweb.constant.CommonConstant;
import com.javaweb.util.core.PatternUtil;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=UserLoginRequestTypeClass.class)
public @interface UserLoginRequestTypeValidate {

	boolean easyWayCheck() default false;//是否是简单方式的校验
	
	/* ↓↓↓↓↓↓↓↓↓↓严格模式下必须有↓↓↓↓↓↓↓↓↓↓ */
	String message() default "validated.check.fail";//全局通用参数校验失败信息
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	/* ↑↑↑↑↑↑↑↑↑↑严格模式下必须有↑↑↑↑↑↑↑↑↑↑ */
	
}

class UserLoginRequestTypeClass implements ConstraintValidator<UserLoginRequestTypeValidate,Object> {
    
	protected UserLoginRequestTypeValidate userLoginRequestTypeValidate;
	
	public void initialize(UserLoginRequestTypeValidate userLoginRequestTypeValidate) {
		this.userLoginRequestTypeValidate = userLoginRequestTypeValidate;
	}

	public boolean isValid(Object value,ConstraintValidatorContext context) {
		boolean result = false;
		if((value!=null)&&(!CommonConstant.EMPTY_VALUE.equals(value.toString().trim()))) {
			result = true;
		}
		if(!userLoginRequestTypeValidate.easyWayCheck()) {
			result = PatternUtil.isPattern(value.toString(),Pattern.compile("[0-3]"));//0:admin;1:web;2:android;3:ios
		}
		return result;
	}
	
}

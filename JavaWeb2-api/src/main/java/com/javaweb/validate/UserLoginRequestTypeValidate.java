package com.javaweb.validate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import com.javaweb.constant.CommonConstant;
import com.javaweb.constant.PatternConstant;

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
			result = PatternConstant.isPattern(value.toString(),PatternConstant.HEAD_TYPE_PATTERN);
		}
		return result;
	}
	
}

package com.javaweb.validate;

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

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=UserNameValidateClass.class)
public @interface UserNameValidate {

	boolean easyWayCheck() default false;//是否是简单方式的校验
	
	/* ↓↓↓↓↓↓↓↓↓↓严格模式下必须有↓↓↓↓↓↓↓↓↓↓ */
	String message() default "validated.check.fail";//全局通用参数校验失败信息
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	/* ↑↑↑↑↑↑↑↑↑↑严格模式下必须有↑↑↑↑↑↑↑↑↑↑ */
	
}

class UserNameValidateClass implements ConstraintValidator<UserNameValidate,Object> {
	
	private final Pattern userNameValidatePattern = Pattern.compile("^[1-9]$");
	
	protected UserNameValidate constraintAnnotation;
	
	public void initialize(UserNameValidate constraintAnnotation) {
		this.constraintAnnotation = constraintAnnotation;
	}

	public boolean isValid(Object value,ConstraintValidatorContext context) {
		boolean result = false;
		if((value!=null)&&(!CommonConstant.EMPTY_VALUE.equals(value.toString().trim()))) {
			result = true;
		}
		if(!constraintAnnotation.easyWayCheck()) {
			result = userNameValidatePattern.matcher(value.toString()).matches();
		}
		return result;
	}
	
}

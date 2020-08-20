package com.javaweb.annotation.validate.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=IntegerValueClass.class)
public @interface IntegerValueValidate {

	int[] vauleArray() default {};//特定数值列表
	
	/* ↓↓↓↓↓↓↓↓↓↓严格模式下必须有↓↓↓↓↓↓↓↓↓↓ */
	String message() default "validated.check.fail";//全局通用参数校验失败信息
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	/* ↑↑↑↑↑↑↑↑↑↑严格模式下必须有↑↑↑↑↑↑↑↑↑↑ */
	
}

class IntegerValueClass implements ConstraintValidator<IntegerValueValidate,Object> {
    
	protected IntegerValueValidate integerValueValidate;
	
	public void initialize(IntegerValueValidate integerValueValidate) {
		this.integerValueValidate = integerValueValidate;
	}

	public boolean isValid(Object value,ConstraintValidatorContext context) {
		boolean result = false;
		if(value!=null) {
			Integer checkValue = (Integer)value;
			int[] valueArray = this.integerValueValidate.vauleArray();
			if(valueArray.length==0){
				return true;
			}
			for(int i=0;i<valueArray.length;i++){
				if(checkValue.intValue()==valueArray[i]){
					return true;
				}
			}
		}
		return result;
	}
	
}

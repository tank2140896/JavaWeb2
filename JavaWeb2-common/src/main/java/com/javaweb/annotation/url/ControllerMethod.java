package com.javaweb.annotation.url;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.javaweb.constant.CommonConstant;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ControllerMethod {
	
	String interfaceName() default CommonConstant.EMPTY_VALUE;//接口名称
	
	Class<?> dataPermissionEntity() default ControllerMethod.class;//数据权限

}

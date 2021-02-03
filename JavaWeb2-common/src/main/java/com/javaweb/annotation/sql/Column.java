package com.javaweb.annotation.sql;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.javaweb.constant.CommonConstant;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {

	String name();
	
	boolean pk() default false;//是否是主键
	
	boolean idAutoCreate() default false;//是否ID自动生成

	boolean keyGenerate() default false;//是否主键自增
	
	String columnDesc() default CommonConstant.EMPTY_VALUE;//字段说明
	
	boolean canUpdateSetEmpty() default false;//更新时是否能设置NULL或者空
	
}

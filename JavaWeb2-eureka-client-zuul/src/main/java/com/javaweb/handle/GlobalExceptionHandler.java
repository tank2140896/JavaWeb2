package com.javaweb.handle;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.javaweb.base.BaseResponseResult;
import com.javaweb.constant.CommonConstant;
import com.javaweb.enums.HttpCodeEnum;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value=Exception.class)  
    public BaseResponseResult allExceptionHandler(HttpServletRequest request,Exception e) {  
	    //e.printStackTrace();
		if(e instanceof NoHandlerFoundException){
			return new BaseResponseResult(HttpCodeEnum.NOT_FOUND.getCode(),"请求接口不存在",CommonConstant.EMPTY_VALUE);
		}else{
			return new BaseResponseResult(HttpCodeEnum.INTERNAL_ERROR.getCode(),"系统异常",CommonConstant.EMPTY_VALUE);
		}
    }  

}

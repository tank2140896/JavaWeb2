package com.javaweb.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.javaweb.base.BaseResponseResult;
import com.javaweb.base.BaseTool;
import com.javaweb.enums.HttpCodeEnum;

@RestControllerAdvice
public class GlobalExceptionHandler extends BaseTool {
	
	//private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(value=Exception.class)  
    public BaseResponseResult allExceptionHandler(HttpServletRequest request,Exception e) {  
		//e.printStackTrace();
		//logger.error(e.getMessage());
		if(e instanceof NoHandlerFoundException){
			return getBaseResponseResult(HttpCodeEnum.NOT_FOUND,"validated.permission.notFound");
		}else{
			return getBaseResponseResult(HttpCodeEnum.INTERNAL_ERROR,"validated.permission.internalError");
		}
    }  

}

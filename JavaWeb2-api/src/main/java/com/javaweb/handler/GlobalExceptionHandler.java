package com.javaweb.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.javaweb.base.BaseResponseResult;
import com.javaweb.base.BaseTool;
import com.javaweb.constant.CommonConstant;
import com.javaweb.constant.SystemConstant;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler extends BaseTool {
	
	@ExceptionHandler(value=Exception.class)  
    public BaseResponseResult allExceptionHandler(HttpServletRequest request,Exception e) throws Exception {  
        if(e instanceof NoHandlerFoundException){
        	return new BaseResponseResult(SystemConstant.NOT_FOUND,getMessage("validated.permission.notFound"),CommonConstant.EMPTY_VALUE);
        }else{
        	e.printStackTrace();
        	return new BaseResponseResult(SystemConstant.INTERNAL_ERROR,getMessage("validated.permission.internalError"),CommonConstant.EMPTY_VALUE);
        }
    }  

}

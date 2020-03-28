package com.javaweb.handler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.javaweb.base.BaseResponseResult;
import com.javaweb.base.BaseTool;
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.exception.TokenExpiredException;

@RestControllerAdvice
public class GlobalExceptionHandler extends BaseTool {
	
	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public BaseResponseResult handleMissingServletRequestParameterException(HttpServletRequest request,NoHandlerFoundException e) {
		return getBaseResponseResult(HttpCodeEnum.NOT_FOUND,"validated.permission.notFound");
	}
	
	@ExceptionHandler(TokenExpiredException.class)
	public BaseResponseResult handleMissingServletRequestParameterException(HttpServletRequest request,TokenExpiredException e) {
		return new BaseResponseResult(HttpCodeEnum.INVALID_REQUEST,"validated.permission.invalidRequest");
	}
	
	@ExceptionHandler(Exception.class)
	public BaseResponseResult handleMissingServletRequestParameterException(HttpServletRequest request,Exception e) {
		e.printStackTrace();
		logger.error(e.getMessage());
		return getBaseResponseResult(HttpCodeEnum.INTERNAL_ERROR,"validated.permission.internalError");
	}

}

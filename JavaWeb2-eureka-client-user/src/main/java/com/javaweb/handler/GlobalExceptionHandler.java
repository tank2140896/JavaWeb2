package com.javaweb.handler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.javaweb.base.BaseResponseResult;
import com.javaweb.base.BaseTool;
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.exception.ServiceException;
import com.javaweb.exception.TokenExpiredException;

@RestControllerAdvice
public class GlobalExceptionHandler extends BaseTool {
	
	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public BaseResponseResult handleException(HttpServletRequest request,NoHandlerFoundException e) {
		return getBaseResponseResult(HttpCodeEnum.NOT_FOUND,"validated.permission.notFound");
	}
	
	@ExceptionHandler(TokenExpiredException.class)
	public BaseResponseResult handleException(HttpServletRequest request,TokenExpiredException e) {
		return new BaseResponseResult(HttpCodeEnum.INVALID_REQUEST,"validated.permission.invalidRequest");
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public BaseResponseResult handleMissingServletRequestParameterException(HttpServletRequest request,MissingServletRequestParameterException e){
		return new BaseResponseResult(HttpCodeEnum.INVALID_REQUEST,"validated.permission.invalidRequest");
	}
	
	@ExceptionHandler(MultipartException.class)
	public BaseResponseResult uploadExcepttion(MultipartException e){
	        return new BaseResponseResult(HttpCodeEnum.INVALID_REQUEST,e.getMessage());
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public BaseResponseResult handleDataIntegrityViolationException(DataIntegrityViolationException e){
		return new BaseResponseResult(HttpCodeEnum.INVALID_REQUEST,e.getMessage());
	}
	
	@ExceptionHandler(ServiceException.class)
	public BaseResponseResult handleServiceException(HttpServletRequest request,ServiceException e){
		return new BaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,e.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public BaseResponseResult handleException(HttpServletRequest request,Exception e) {
		//e.printStackTrace();
		logger.error(e.getMessage());
		return getBaseResponseResult(HttpCodeEnum.INTERNAL_ERROR,"validated.permission.internalError");
	}
	
}

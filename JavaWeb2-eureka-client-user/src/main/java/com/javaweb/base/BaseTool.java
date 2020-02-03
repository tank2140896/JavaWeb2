package com.javaweb.base;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.javaweb.constant.CommonConstant;
import com.javaweb.constant.SystemConstant;
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.web.eo.TokenData;

public class BaseTool extends BaseInject {
	
	@Resource(name="redisTemplate")
	protected ValueOperations<Object,Object> valueOperations;
	
	public void setDataToRedis(String key,Object value,long timeOut,TimeUnit timeUnit){
		valueOperations.set(key,value,timeOut,timeUnit);
	}
	
	public void setDefaultDataToRedis(String key,Object value){
		valueOperations.set(key,value,SystemConstant.SYSTEM_DEFAULT_SESSION_OUT,TimeUnit.MINUTES);
	}
	
	public Object getDateFromRedis(String key){
		return valueOperations.get(key);
	}
	
	public boolean deleteFromRedisByKey(String key) {
		return stringRedisTemplate.delete(key);
	}

	public TokenData getTokenData(HttpServletRequest request){
		String key = request.getHeader(SystemConstant.HEAD_TOKEN);//String.join(CommonConstant.COMMA,request.getHeader(SystemConstant.HEAD_USERID),request.getHeader(SystemConstant.HEAD_TYPE));
		return (TokenData)valueOperations.get(key);
	}
	
	public String getMessage(String messageKey){
		return messageSource.getMessage(messageKey,null,LocaleContextHolder.getLocale());
	}
	
	public String getValidateMessage(BindingResult bindingResult){
		String message = CommonConstant.EMPTY_VALUE;
		List<ObjectError> list = bindingResult.getAllErrors();
		if(list!=null&&list.size()!=0){
			message = getMessage(list.get(0).getDefaultMessage());
		}
		return message;
	}
	
	public BaseResponseResult getBaseResponseResult(HttpCodeEnum httpCodeEnum,String messageKey) {
		return new BaseResponseResult(httpCodeEnum.getCode(),getMessage(messageKey),CommonConstant.EMPTY_VALUE);
	}
	
	public BaseResponseResult getBaseResponseResult(HttpCodeEnum httpCodeEnum,String messageKey,Object data) {
		return new BaseResponseResult(httpCodeEnum.getCode(),getMessage(messageKey),data);
	}
	
	public BaseResponseResult getBaseResponseResult(HttpCodeEnum httpCodeEnum,BindingResult bindingResult) {
		return new BaseResponseResult(httpCodeEnum.getCode(),getValidateMessage(bindingResult),CommonConstant.EMPTY_VALUE);
	}
	
	public BaseResponseResult getBaseResponseResult(HttpCodeEnum httpCodeEnum,BindingResult bindingResult,Object data) {
		return new BaseResponseResult(httpCodeEnum.getCode(),getValidateMessage(bindingResult),data);
	}
	
}

package com.javaweb.base;

import java.time.Duration;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.javaweb.constant.CommonConstant;
import com.javaweb.constant.SystemConstant;
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.util.core.SecretUtil;
import com.javaweb.web.eo.TokenData;

public class BaseTool extends BaseInject {
	
	@Resource(name="redisTemplate")
	protected ValueOperations<Object,Object> valueOperations;
	
	public void setDataToRedis(String key,Object value,Duration duration){
		valueOperations.set(key,value,duration);
	}
	
	public void setDefaultDataToRedis(String key,Object value){
		valueOperations.set(key,value,Duration.ofMinutes(Long.parseLong(environment.getProperty("redis.session.timeout"))));
	}
	
	public Object getDateFromRedis(String key){
		return valueOperations.get(key);
	}
	
	public boolean deleteFromRedisByKey(String key) {
		return stringRedisTemplate.delete(key);
	}

	public static TokenData getTokenData(HttpServletRequest httpServletRequest,RedisTemplate<String,Object> redisTemplate){
		TokenData tokenData = null;
		String token = httpServletRequest.getParameter(SystemConstant.HEAD_TOKEN);//1、支持问号传参方式
		if(token==null){
			token = httpServletRequest.getHeader(SystemConstant.HEAD_TOKEN);//2、支持header传参方式
		}
		if(token!=null){
	    	tokenData = getTokenData(token,redisTemplate);
		}
		return tokenData;
	}
	
	public static TokenData getTokenData(String token,RedisTemplate<String,Object> redisTemplate){
		TokenData tokenData = null;
		try{
			token = SecretUtil.base64DecoderString(token,"UTF-8");
	    	String tokens[] = token.split(CommonConstant.COMMA);//token由三部分组成：token,userId,type
	    	token = tokens[1]+CommonConstant.COMMA+tokens[2];//userId+type
	    	tokenData = (TokenData)(redisTemplate.opsForValue().get(token));
		}catch(Exception e){
			//do nothing
		}
		return tokenData;
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

package com.javaweb.base;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.javaweb.constant.CommonConstant;
import com.javaweb.constant.SystemConstant;
import com.javaweb.web.eo.TokenData;

public class BaseTool {
	
	/**
	@Autowired
	public MongoTemplate mongoTemplate;
	
	@Autowired
	public Neo4jTemplate neo4jTemplate;
	*/
	
	@Autowired
    public MessageSource messageSource;
	
	@Autowired
	public RedisTemplate<String,String> redisTemplate;
	
	@Resource(name="redisTemplate")
	public ValueOperations<Object,Object> valueOperations;
	
	public void setDefaultDataToRedis(String key,Object value){
		valueOperations.set(key,value,SystemConstant.SYSTEM_DEFAULT_SESSION_OUT,TimeUnit.MINUTES);
	}
	
	public TokenData getTokenData(HttpServletRequest request){
		String key = request.getHeader(SystemConstant.HEAD_USERID)+","+request.getHeader(SystemConstant.HEAD_TYPE);
		return (TokenData)valueOperations.get(key);
	}
	
	public String getMessage(String messageKey){
		return messageSource.getMessage(messageKey,null,LocaleContextHolder.getLocale());
	}
	
	public String getValidateMessage(BindingResult bindingResult){
		String message = CommonConstant.EMPTY_VALUE;
		try{
			//Locale locale = LocaleContextHolder.getLocale();
			List<ObjectError> list = bindingResult.getAllErrors();
			if(list.size()>=1){
				//message = messageSource.getMessage(list.get(0).getDefaultMessage(),null,locale);
				message = getMessage(list.get(0).getDefaultMessage());
			}
		}catch(Exception e){
			
		}
		return message;
	}
	
}

package com.javaweb.base;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.javaweb.constant.CommonConstant;

public class BaseTool {
	
	@Autowired
    public MessageSource messageSource;
	
	@Autowired
	public RedisTemplate<String,String> redisTemplate;
	
	@Resource(name="redisTemplate")
	public ValueOperations<Object,Object> valueOperations;
	
	public void setDefaultDataToRedis(String key,Object value){
		valueOperations.set(key,value,15,TimeUnit.MINUTES);
	}
	
	public String getValidateMessage(BindingResult bindingResult){
		String message = CommonConstant.EMPTY_VALUE;
		try{
			Locale locale = LocaleContextHolder.getLocale();
			List<ObjectError> list = bindingResult.getAllErrors();
			if(list.size()>=1){
				message = messageSource.getMessage(list.get(0).getDefaultMessage(),null,locale);
			}
		}catch(Exception e){
			
		}
		return message;
	}
	
	public String getMessage(String messageKey){
		return messageSource.getMessage(messageKey,null,LocaleContextHolder.getLocale());
	}

}

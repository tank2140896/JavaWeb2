package com.javaweb.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.javaweb.annotation.token.TokenDataAnnotation;
import com.javaweb.constant.CommonConstant;
import com.javaweb.constant.SystemConstant;
import com.javaweb.context.ApplicationContextHelper;
import com.javaweb.web.eo.TokenData;

public class TokenDataHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
	
	private RedisTemplate<String,Object> redisTemplate1 = null;

    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(TokenData.class) && parameter.hasParameterAnnotation(TokenDataAnnotation.class);
    }

    @SuppressWarnings("unchecked")
	public Object resolveArgument(MethodParameter parameter,ModelAndViewContainer container,NativeWebRequest request,WebDataBinderFactory factory) throws Exception {
    	if(redisTemplate1==null){
			redisTemplate1 = (RedisTemplate<String,Object>)ApplicationContextHelper.getBean(SystemConstant.REDIS_TEMPLATE_1);
		}
    	String key = String.join(CommonConstant.COMMA,request.getHeader(SystemConstant.HEAD_USERID),request.getHeader(SystemConstant.HEAD_TYPE));
		return (TokenData)redisTemplate1.opsForValue().get(key);
    }
    
}

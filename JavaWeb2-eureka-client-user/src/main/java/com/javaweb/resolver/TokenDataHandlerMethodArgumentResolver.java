package com.javaweb.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.javaweb.annotation.token.TokenDataAnnotation;
import com.javaweb.base.BaseTool;
import com.javaweb.constant.SystemConstant;
import com.javaweb.context.ApplicationContextHelper;
import com.javaweb.exception.TokenExpiredException;
import com.javaweb.web.eo.TokenData;

public class TokenDataHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
	
	private RedisTemplate<String,Object> redisTemplate = null;

    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(TokenData.class) && parameter.hasParameterAnnotation(TokenDataAnnotation.class);
    }
    
	public Object resolveArgument(MethodParameter parameter,ModelAndViewContainer container,NativeWebRequest request,WebDataBinderFactory factory) throws Exception {
		String token = request.getHeader(SystemConstant.HEAD_TOKEN);
		return TokenDataHandlerMethodArgumentResolver.getTokenData(redisTemplate,token);
    }
    
	@SuppressWarnings("unchecked")
    public static TokenData getTokenData(RedisTemplate<String,Object> redisTemplate,String token) throws Exception {
    	if(redisTemplate==null){
			redisTemplate = (RedisTemplate<String,Object>)ApplicationContextHelper.getBean(SystemConstant.REDIS_TEMPLATE);
		}
		TokenData tokenData = BaseTool.getTokenData(token,redisTemplate);
		if(tokenData==null){
			throw new TokenExpiredException();
		}
		return tokenData;
    }
    
}

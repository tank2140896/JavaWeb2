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
import com.javaweb.exception.TokenExpiredException;
import com.javaweb.util.core.SecretUtil;
import com.javaweb.web.eo.TokenData;

public class TokenDataHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
	
	private RedisTemplate<String,Object> redisTemplate = null;

    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(TokenData.class) && parameter.hasParameterAnnotation(TokenDataAnnotation.class);
    }

    @SuppressWarnings("unchecked")
	public Object resolveArgument(MethodParameter parameter,ModelAndViewContainer container,NativeWebRequest request,WebDataBinderFactory factory) throws Exception {
		if(redisTemplate==null){
			redisTemplate = (RedisTemplate<String,Object>)ApplicationContextHelper.getBean(SystemConstant.REDIS_TEMPLATE);
		}
		String token = request.getHeader(SystemConstant.HEAD_TOKEN);
		token = SecretUtil.base64DecoderString(token,"UTF-8");
		String tokens[] = token.split(CommonConstant.COMMA);
		TokenData tokenData = (TokenData)redisTemplate.opsForValue().get(tokens[1]+CommonConstant.COMMA+tokens[2]);
		if(tokenData==null){
			throw new TokenExpiredException();
		}
		return tokenData;
    }
    
}

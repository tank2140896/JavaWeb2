package com.javaweb.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.javaweb.annotation.token.TokenDataAnnotation;
import com.javaweb.base.BaseTool;
import com.javaweb.exception.TokenExpiredException;
import com.javaweb.web.eo.TokenData;

public class TokenDataHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
	
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(TokenData.class) && parameter.hasParameterAnnotation(TokenDataAnnotation.class);
    }
    
    //这里不建议将NativeWebRequest转为HttpServeltRequest
	public Object resolveArgument(MethodParameter parameter,ModelAndViewContainer container,NativeWebRequest request,WebDataBinderFactory factory) throws Exception {
		TokenData tokenData = BaseTool.getTokenData(BaseTool.getToken(request));
		if(tokenData==null){
			throw new TokenExpiredException();
		}
		return tokenData;
    }
    
}

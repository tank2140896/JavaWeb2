package com.javaweb.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.config.context.ApplicationContextHelper;
import com.javaweb.constant.CommonConstant;
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.web.eo.TokenData;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class MyZuulFilter extends ZuulFilter {
    
    private RedisTemplate<String,Object> redisTemplate1 = null;
    
    @SuppressWarnings("unchecked")
    public Object run() throws ZuulException {
        if(redisTemplate1==null){
            redisTemplate1 = (RedisTemplate<String,Object>)ApplicationContextHelper.getBean("redisTemplate1");
        }
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        //HttpServletResponse response = requestContext.getResponse();
        //String servletPath = request.getServletPath();
        //个人认为具体权限应该由每个微服务自己处理
        String token = request.getHeader("token");
        if(token==null||CommonConstant.EMPTY_VALUE.equals(token)) {
            requestContext.setSendZuulResponse(false);
            try {
                requestContext.setResponseBody(new ObjectMapper().writeValueAsString(new BaseResponseResult(HttpCodeEnum.VALIDATE_ERROR.getCode(),"token不存在",CommonConstant.EMPTY_VALUE)));
            } catch (JsonProcessingException e) {
                //do nothing
            }
        }else {
            //redis在这里有个坑，一个项目中对实体类序列化后，另一个项目读取该类如果两个项目的类的路径不一致就会报错
            TokenData tokenData = (TokenData)(redisTemplate1.opsForValue().get(token));
            if(tokenData==null) {
                requestContext.setSendZuulResponse(false);
                try {
                    requestContext.setResponseBody(new ObjectMapper().writeValueAsString(new BaseResponseResult(HttpCodeEnum.VALIDATE_ERROR.getCode(),"token失效或错误",CommonConstant.EMPTY_VALUE)));
                } catch (JsonProcessingException e) {
                    //do nothing
                }
            }
        }
        return null;
    }

    public boolean shouldFilter() {
        return true;
    }

    public String filterType() {
        return "pre";
    }

    public int filterOrder() {
        return 0;
    }
    
}
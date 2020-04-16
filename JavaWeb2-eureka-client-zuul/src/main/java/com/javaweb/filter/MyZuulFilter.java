package com.javaweb.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.constant.CommonConstant;
import com.javaweb.context.ApplicationContextHelper;
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.web.eo.TokenData;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class MyZuulFilter extends ZuulFilter {
    
    private RedisTemplate<String,Object> redisTemplate = null;
    
    @SuppressWarnings("unchecked")
    public Object run() throws ZuulException {
        if(redisTemplate==null){
        	redisTemplate = (RedisTemplate<String,Object>)ApplicationContextHelper.getBean("redisTemplate");
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
            	requestContext.getResponse().setContentType("application/json;charset=UTF-8");
                requestContext.setResponseBody(new ObjectMapper().writeValueAsString(new BaseResponseResult(HttpCodeEnum.VALIDATE_ERROR.getCode(),"token错误或不存在",CommonConstant.EMPTY_VALUE)));
            } catch (JsonProcessingException e) {
                //do nothing
            }
        }else {
            //redis在这里有个坑，一个项目中对实体类序列化后，另一个项目读取该类如果两个项目的类的路径不一致就会报错
            TokenData tokenData = (TokenData)(redisTemplate.opsForValue().get(token));
            if(tokenData==null) {
                requestContext.setSendZuulResponse(false);
                try {
                	requestContext.getResponse().setContentType("application/json;charset=UTF-8");
                    requestContext.setResponseBody(new ObjectMapper().writeValueAsString(new BaseResponseResult(HttpCodeEnum.VALIDATE_ERROR.getCode(),"token错误或失效",CommonConstant.EMPTY_VALUE)));
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
    
    /**
    private RedisTemplate<String,Object> redisTemplate = null;
    public static final int COUNT_TIMES = 3;//3次次数限制
    public static final int LIMIT_TIME = 5;//5秒时间限制
    @SuppressWarnings("unchecked")
	public boolean interfaceCurrentLimiting(String pathAndIp) {//简单接口限流
    	if(redisTemplate==null){
			redisTemplate = (RedisTemplate<String,Object>)ApplicationContextHelper.getBean("redisTemplate");
		}
    	if(redisTemplate.hasKey(pathAndIp)){//记录过
    		int count = (int)redisTemplate.opsForValue().get(pathAndIp);
    		if(count>=COUNT_TIMES){
    			redisTemplate.opsForValue().set(pathAndIp,COUNT_TIMES+1,5,TimeUnit.MINUTES);//频繁调用封禁5分钟
                return false;
    		}else{
    			redisTemplate.opsForValue().increment(pathAndIp,1);
    		}
    	}else{//没有记录过
    		redisTemplate.opsForValue().set(pathAndIp,1,LIMIT_TIME,TimeUnit.SECONDS);
    	}
    	return true;
    }
    */
    
    /**
    boolean pass = interfaceCurrentLimiting(servletPath+","+request.getRemoteHost());
    if(!pass){
        try {
            response.reset();
            ServletOutputStream sos = response.getOutputStream();
            response.setCharacterEncoding("UTF-8");          
            response.setContentType("application/json;charset=UTF-8");   
            sos.write("接口调用次数太频繁，请5分钟后再次尝试！".getBytes("UTF-8"));
            sos.flush();
            sos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */
    
}

package com.javaweb.filter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.javaweb.constant.CommonConstant;
import com.javaweb.context.ApplicationContextHelper;
import com.javaweb.util.core.HttpUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class MyZuulFilter extends ZuulFilter {
    
    private RedisTemplate<String,Object> redisTemplate = null;
    public static final int COUNT_TIMES = 3;//3次次数限制
    public static final int LIMIT_TIME = 5;//5秒时间限制
    
    @SuppressWarnings("unchecked")
    public Object run() throws ZuulException {
        if(redisTemplate==null){
        	redisTemplate = (RedisTemplate<String,Object>)ApplicationContextHelper.getBean("redisTemplate");
        }
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        HttpServletResponse response = requestContext.getResponse();
        String servletPath = request.getServletPath();
        boolean pass = interfaceCurrentLimiting(String.join(CommonConstant.COMMA,servletPath,HttpUtil.getIpAddress(request)));
        if(!pass){
            try {
            	/**
            	response.setContentType("application/json;charset=UTF-8");
                requestContext.setResponseBody(new ObjectMapper().writeValueAsString(new BaseResponseResult(500,"错误",null)));
                */
                response.reset();
                ServletOutputStream sos = response.getOutputStream();
                response.setCharacterEncoding("UTF-8");          
                response.setContentType("application/json;charset=UTF-8");   
                sos.write("接口调用次数太频繁，请5分钟后再次尝试！".getBytes("UTF-8"));
                sos.flush();
                sos.close();
            } catch (IOException e) {
                //do nothing
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
    
}

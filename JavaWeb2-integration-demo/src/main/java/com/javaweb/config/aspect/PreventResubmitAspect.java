package com.javaweb.config.aspect;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.javaweb.annotation.url.PreventResubmit;
import com.javaweb.context.ApplicationContextHelper;

@Aspect
@Component
public class PreventResubmitAspect {
	
	private RedisTemplate<String,Object> redisTemplate = null;
	
	@Pointcut("@annotation(preventResubmit)")
	public void pointcut(PreventResubmit preventResubmit) { 
		
	}
	
	@SuppressWarnings("unchecked")
	@Around("pointcut(preventResubmit)")
	public Object arround(ProceedingJoinPoint proceedingJoinPoint,PreventResubmit preventResubmit) throws Throwable {
		if(redisTemplate==null){
			redisTemplate = (RedisTemplate<String,Object>)ApplicationContextHelper.getBean("redisTemplate1");
		}
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
		String token = httpServletRequest.getHeader("token");
		String servletPath = httpServletRequest.getServletPath();
		boolean isSuccess = redisTemplate.opsForValue().setIfAbsent(token+servletPath,1,5,TimeUnit.SECONDS);//可以设置在5秒内不能重复提交表单
		if(isSuccess){
			return proceedingJoinPoint.proceed();
		}else{
			return "fail";//表示重复提交了
		}
	}

}

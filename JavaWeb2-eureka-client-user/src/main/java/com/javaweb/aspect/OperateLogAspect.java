package com.javaweb.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.javaweb.constant.CommonConstant;
import com.javaweb.constant.SystemConstant;
import com.javaweb.context.ApplicationContextHelper;
import com.javaweb.util.core.DateUtil;
import com.javaweb.util.core.HttpUtil;
import com.javaweb.util.core.SecretUtil;
import com.javaweb.web.eo.TokenData;
import com.javaweb.web.po.OperationLog;
import com.javaweb.web.service.OperationLogService;

@Aspect
@Component
public class OperateLogAspect {
	
	private RedisTemplate<String,Object> redisTemplate = null;
	
	private Environment environment = null;
	
	@Autowired
	private OperationLogService operationLogService;
	
	//TODO 等待改造
	/* 如果业务要求只记录Controller校验通过的操作，最简单的方法就是将@Before变为@Around，主要判断逻辑示例如下： 
	@Around(value=DEFAULT_LOG_POINT_CUT)
	public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		Object object = joinPoint.proceed();
		BaseResponseResult baseResponseResult = new ObjectMapper().readValue(JSONObject.fromObject(object).toString(),BaseResponseResult.class);
		if("200".equals(baseResponseResult.getCode().toString())){//只记录操作成功的数据
			//记录操作日志
		}
		return object;
	}
	*/
	
	@SuppressWarnings("unchecked")
	@Before(value=SystemConstant.DEFAULT_LOG_POINT_CUT)
	public void beforeMethod(JoinPoint joinPoint) {
		if(environment==null){
			environment = (Environment)ApplicationContextHelper.getBean(SystemConstant.ENVIRONMENT);
		}
		Boolean open = Boolean.parseBoolean(environment.getProperty("operatelog.aspect.open"));//获得配置文件中是否开启操作日志
		if(open){
			ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
			HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
			String url = httpServletRequest.getRequestURL().toString();
			if(url.contains("add")||url.contains("modify")||url.contains("delete")){//只记录关键的新增、修改和删除操作
				if(redisTemplate==null){
					redisTemplate = (RedisTemplate<String,Object>)ApplicationContextHelper.getBean(SystemConstant.REDIS_TEMPLATE);
				}
				
				TokenData tokenData = null;
				try{
					String token = httpServletRequest.getHeader(SystemConstant.HEAD_TOKEN);
					token = SecretUtil.base64DecoderString(token,"UTF-8");
			    	String tokens[] = token.split(CommonConstant.COMMA);//token由三部分组成：token,userId,type
			    	token = tokens[1]+CommonConstant.COMMA+tokens[2];//userId+type
			    	tokenData = (TokenData)(redisTemplate.opsForValue().get(token));
				}catch(Exception e){
					//do nothing
				}
				if(tokenData!=null){
					OperationLog operationLog = new OperationLog();
					operationLog.setId(SecretUtil.defaultGenUniqueStr(SystemConstant.SYSTEM_NO));
					operationLog.setUserId(tokenData.getUser().getUserId());
					operationLog.setUrl(url);
					operationLog.setRequestMethod(httpServletRequest.getMethod());
					//TODO 获取@RequestBody注解的实体类
					operationLog.setRequestParameter(Arrays.toString(joinPoint.getArgs()));//joinPoint.getSignature()
					operationLog.setRequestIpAddress(HttpUtil.getIpAddress(httpServletRequest));
					operationLog.setRequestTime(DateUtil.getDefaultDate());
					operationLogService.saveOperationLog(operationLog);
				}
			}
		}
	}

}

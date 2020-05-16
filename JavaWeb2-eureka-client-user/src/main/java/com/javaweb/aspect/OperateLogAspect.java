package com.javaweb.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.base.BaseSystemMemory;
import com.javaweb.base.BaseTool;
import com.javaweb.constant.CommonConstant;
import com.javaweb.constant.SystemConstant;
import com.javaweb.context.ApplicationContextHelper;
import com.javaweb.util.core.DateUtil;
import com.javaweb.util.core.HttpUtil;
import com.javaweb.util.core.SecretUtil;
import com.javaweb.web.eo.TokenData;
import com.javaweb.web.po.Dictionary;
import com.javaweb.web.po.OperationLog;
import com.javaweb.web.service.OperationLogService;

import net.sf.json.JSONObject;

@Aspect
@Component
public class OperateLogAspect {
	
	private RedisTemplate<String,Object> redisTemplate = null;
	
	@Autowired
	private OperationLogService operationLogService;
	
	@Around(value=SystemConstant.DEFAULT_LOG_POINT_CUT)
	public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		Object object = joinPoint.proceed();
		Boolean open = getOperatelogOpenFlag();
		if(open){//判断是否需要日志记录
			ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
			HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
			String url = httpServletRequest.getRequestURL().toString();
			if(SystemConstant.LOGGERED_URL.matcher(url).matches()){//日志只记录指定规则的URL请求
				if(matchBaseResponseResult(object)){
					saveOperationLog(httpServletRequest,url,joinPoint);
				}
			}
		}
		return object;
	}
	
	@SuppressWarnings("unchecked")
	private RedisTemplate<String,Object> getRedisTemplate(){
		if(redisTemplate==null){
			redisTemplate = (RedisTemplate<String,Object>)ApplicationContextHelper.getBean(SystemConstant.REDIS_TEMPLATE);
		}
		return redisTemplate;
	}
	
	private Boolean getOperatelogOpenFlag(){
		Boolean open = false;
		Dictionary dictionary = BaseSystemMemory.getDictionaryByKey("operatelog.aspect.open");
		try{
			if(dictionary!=null){
				open = Boolean.parseBoolean(dictionary.getValueCode());
			}
		}catch(Exception e){
			//do nothing
		}
		return open;
	}
	
	private void saveOperationLog(HttpServletRequest httpServletRequest,String url,ProceedingJoinPoint joinPoint){
		TokenData tokenData = BaseTool.getTokenData(httpServletRequest,getRedisTemplate());
		if(tokenData!=null){
			OperationLog operationLog = new OperationLog();
			operationLog.setId(SecretUtil.defaultGenUniqueStr(SystemConstant.SYSTEM_NO));
			operationLog.setUserId(tokenData.getUser().getUserId());
			operationLog.setUrl(url);
			operationLog.setRequestMethod(httpServletRequest.getMethod());
			//一般安全性较高的网站会对请求参数加密，这种情况下记录请求参数意义也不大，另外记录请求参数会暴露用户数据，所以个人觉得没有必要记录请求参数
			operationLog.setRequestParameter(joinPoint.getArgs().toString()/*joinPoint.getSignature()*/);
			operationLog.setRequestIpAddress(HttpUtil.getIpAddress(httpServletRequest));
			operationLog.setRequestTime(DateUtil.getDefaultDate());
			operationLogService.saveOperationLog(operationLog);//记录操作日志
		}
	}
	
	private boolean matchBaseResponseResult(Object object){
		boolean match = false;
		try{//只记录操作成功且符合BaseResponseResult格式的数据
			if(object!=null){
				BaseResponseResult baseResponseResult = new ObjectMapper().readValue(JSONObject.fromObject(object).toString(),BaseResponseResult.class);
				Object code = baseResponseResult.getCode();
				code = (code==null?CommonConstant.EMPTY_VALUE:code);
				if("200".equals(code.toString())){
					match = true;
				}
			}
		}catch(Exception e){
			//do nothing
		}
		return match;
	}

}

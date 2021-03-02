package com.javaweb.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.base.BaseSystemMemory;
import com.javaweb.base.BaseTool;
import com.javaweb.constant.CommonConstant;
import com.javaweb.constant.SystemConstant;
import com.javaweb.util.core.DateUtil;
import com.javaweb.util.core.HttpUtil;
import com.javaweb.util.core.SecretUtil;
import com.javaweb.web.eo.TokenData;
import com.javaweb.web.po.OperationLog;
import com.javaweb.web.service.OperationLogService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
操作日志记录常见的有三种方式
1、正则URL路径规则
2、数据库URL开关控制
3、Controller注解标记
*/
@Aspect
@Component
public class OperateLogAspect {
	
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
	
	private Boolean getOperatelogOpenFlag(){
		Boolean open = false;
		String value = BaseSystemMemory.getDictionaryValueByKey("operatelog.aspect.open","false");
		try{
			if(value!=null){
				open = Boolean.parseBoolean(value);
			}
		}catch(Exception e){
			//do nothing
		}
		return open;
	}
	
	private void saveOperationLog(HttpServletRequest httpServletRequest,String url,ProceedingJoinPoint joinPoint){
		TokenData tokenData = BaseTool.getTokenData(BaseTool.getToken(httpServletRequest));
		if(tokenData!=null){
			OperationLog operationLog = new OperationLog();
			operationLog.setId(SecretUtil.defaultGenUniqueStr(SystemConstant.SYSTEM_NO));
			operationLog.setUserId(tokenData.getUser().getUserId());
			operationLog.setUrl(url);
			operationLog.setRequestMethod(httpServletRequest.getMethod());
			//获得请求参数（一般指controller方法里的所有参数）
			String requestParameter = null;
			Object obj[] = joinPoint.getArgs();
			for(int i=0;i<obj.length;i++){
				if(obj[i] instanceof BindingResult || obj[i] instanceof TokenData || obj[i] instanceof HttpServletRequest || obj[i] instanceof HttpServletResponse) {
					continue;
				}
				try{
					requestParameter = JSONObject.fromObject(obj[i]).toString();
				}catch(Exception e){
					try{
						requestParameter = JSONArray.fromObject(obj[i]).toString();
					}catch(Exception ee){
						//do nothing
					}
				}
			}
			operationLog.setRequestParameter(requestParameter/*joinPoint.getArgs().toString()*//*joinPoint.getSignature()*/);
			operationLog.setRequestIpAddress(HttpUtil.getIpAddress(httpServletRequest));
			operationLog.setRequestTime(DateUtil.getDefaultDate());
			operationLog.setLoginWay(tokenData.getLoginWay());
			operationLog.setClientType(tokenData.getClientType());
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

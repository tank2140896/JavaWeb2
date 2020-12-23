package com.javaweb.base;

import java.io.File;
import java.time.Duration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.request.NativeWebRequest;

import com.javaweb.constant.CommonConstant;
import com.javaweb.constant.SystemConstant;
import com.javaweb.context.ApplicationContextHelper;
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.util.core.DateUtil;
import com.javaweb.util.core.FileUtil;
import com.javaweb.util.core.SecretUtil;
import com.javaweb.util.core.SystemUtil;
import com.javaweb.web.eo.TokenData;

public class BaseTool extends BaseInject {
	
	private static RedisTemplate<String,Object> staticRedisTemplate = null;
	
	private static Environment staticEnvironment = null;
	
	public void setDataToRedis(String key,Object value,Duration duration){
		valueOperations.set(key,value,duration);
	}
	
	public void setDefaultDataToRedis(String key,Object value){
		valueOperations.set(key,value,Duration.ofMinutes(Long.parseLong(environment.getProperty("redis.session.timeout"))));
	}
	
	public Object getDateFromRedis(String key){
		return valueOperations.get(key);
	}
	
	public boolean deleteFromRedisByKey(String key) {
		return stringRedisTemplate.delete(key);
	}

	public String getMessage(String messageKey){
		return messageSource.getMessage(messageKey,null,LocaleContextHolder.getLocale());
	}
	
	public String getValidateMessage(BindingResult bindingResult){
		String message = CommonConstant.EMPTY_VALUE;
		List<ObjectError> list = bindingResult.getAllErrors();
		if(list!=null&&list.size()>0){
			message = getMessage(list.get(0).getDefaultMessage());
		}
		return message;
	}
	
	public BaseResponseResult getBaseResponseResult(HttpCodeEnum httpCodeEnum,String messageKey) {
		return new BaseResponseResult(httpCodeEnum.getCode(),getMessage(messageKey),CommonConstant.EMPTY_VALUE);
	}
	
	public BaseResponseResult getBaseResponseResult(HttpCodeEnum httpCodeEnum,String messageKey,Object data) {
		return new BaseResponseResult(httpCodeEnum.getCode(),getMessage(messageKey),data);
	}
	
	public BaseResponseResult getBaseResponseResult(HttpCodeEnum httpCodeEnum,BindingResult bindingResult) {
		return new BaseResponseResult(httpCodeEnum.getCode(),getValidateMessage(bindingResult),CommonConstant.EMPTY_VALUE);
	}
	
	public BaseResponseResult getBaseResponseResult(HttpCodeEnum httpCodeEnum,BindingResult bindingResult,Object data) {
		return new BaseResponseResult(httpCodeEnum.getCode(),getValidateMessage(bindingResult),data);
	}
	
	/* -------------------------------------------------- 分界线 -------------------------------------------------- */
	
	//优先获取header里的token
	public static String getToken(HttpServletRequest httpServletRequest){
		String token = httpServletRequest.getHeader(SystemConstant.HEAD_TOKEN);//1、支持header传参方式
		if(token==null){
			token = httpServletRequest.getParameter(SystemConstant.HEAD_TOKEN);//2、支持问号传参方式
		}
		return token;
	}
	
	//优先获取header里的token
	public static String getToken(NativeWebRequest nativeWebRequest){
		String token = nativeWebRequest.getHeader(SystemConstant.HEAD_TOKEN);//1、支持header传参方式
		if(token==null){
			token = nativeWebRequest.getParameter(SystemConstant.HEAD_TOKEN);//2、支持问号传参方式
		}
		return token;
	}
	
	//优先获取header里的token
	public static String getToken(ServerHttpRequest serverHttpRequest){
		String token = serverHttpRequest.getHeaders().getFirst(SystemConstant.HEAD_TOKEN);//1、支持header传参方式
		if(token==null){
			try{
				token = serverHttpRequest.getURI().getPath();//2、支持问号传参方式
				token = token.split(SystemConstant.HEAD_TOKEN+CommonConstant.EQUAL)[1].split(CommonConstant.AND)[0];
			}catch(Exception e){
				//do nothing
			}
		}
		return token;
	}
	
	public static TokenData getTokenData(String token){
		TokenData tokenData = null;
		try{
			if(token!=null){
				token = SecretUtil.base64DecoderString(token,"UTF-8");
		    	String tokens[] = token.split(CommonConstant.COMMA);//token由四部分组成：token,userId,clientType,loginWay
		    	token = tokens[1]+CommonConstant.COMMA+tokens[2]+CommonConstant.COMMA+tokens[3];//userId,clientType,loginWay
		    	tokenData = (TokenData)(getRedisTemplate().opsForValue().get(token));
			}
		}catch(Exception e){
			//do nothing
		}
		return tokenData;
	}
	
	@SuppressWarnings("unchecked")
	public static RedisTemplate<String,Object> getRedisTemplate(){
		if(staticRedisTemplate==null){
			staticRedisTemplate = (RedisTemplate<String,Object>)ApplicationContextHelper.getBean(SystemConstant.REDIS_TEMPLATE);
		}
		return staticRedisTemplate;
	}
	
	public static Environment getEnvironment(){
		if(staticEnvironment==null){
			staticEnvironment = (Environment)ApplicationContextHelper.getBean(SystemConstant.ENVIRONMENT);
		}
		return staticEnvironment;
	}
	
	/* -------------------------------------------------- 分界线 -------------------------------------------------- */
	
	//快速校验
	public static final Validator FAST_VALIDATOR = Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();
	
	//全部校验
	public static final Validator ALL_VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

	public static <T> List<String> fastFailValidate(T t) {
		List<String> errorMessages = new LinkedList<String>();
		Set<ConstraintViolation<T>> constraintViolations = FAST_VALIDATOR.validate(t);
        if(constraintViolations!=null&&constraintViolations.size()>0){
        	//constraintViolations.iterator().next().getPropertyPath().toString()
        	errorMessages.add(constraintViolations.iterator().next().getMessage());
        }
        return errorMessages;
    }
	
	public static <T> List<String> allCheckValidate(T t) {
		List<String> errorMessages = new LinkedList<String>();
        Set<ConstraintViolation<T>> constraintViolations = ALL_VALIDATOR.validate(t);
        if(constraintViolations!=null&&constraintViolations.size()>0) {
            Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
            while(iterator.hasNext()){
                ConstraintViolation<T> violation = iterator.next();
                //violation.getPropertyPath().toString()
                errorMessages.add(violation.getMessage());
            }
        }
        return errorMessages;
	}
	
	/* -------------------------------------------------- 分界线 -------------------------------------------------- */
	
	//获取文件根路径
	public static String getFileRootPath(){
		String rootPath = CommonConstant.EMPTY_VALUE;
		String yearMonthDay = DateUtil.getDefaultDate(DateUtil.DATE_PATTERN_TYPE1);//年月日
		int random = (int)(Math.random()*10);//0-9
		if(SystemUtil.isLinux()) {//linux路径
            rootPath = BaseSystemMemory.getDictionaryValueByKey("file.root.linux.path","/tmp/file/");
            rootPath += (random+"/"+yearMonthDay+"/");
        } else {//windows路径
        	rootPath = BaseSystemMemory.getDictionaryValueByKey("file.root.windows.path","E:\\file\\");
        	rootPath += (random+"\\\\"+yearMonthDay+"\\\\");
        }
		FileUtil.makeFolder(new File(rootPath));
		return rootPath;
	}
	
	/* -------------------------------------------------- 分界线 -------------------------------------------------- */

}

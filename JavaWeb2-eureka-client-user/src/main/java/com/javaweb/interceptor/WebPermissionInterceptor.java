package com.javaweb.interceptor;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.javaweb.annotation.url.IgnoreUrl;
import com.javaweb.constant.ApiConstant;
import com.javaweb.constant.CommonConstant;
import com.javaweb.constant.SystemConstant;
import com.javaweb.context.ApplicationContextHelper;
import com.javaweb.util.core.SecretUtil;
import com.javaweb.web.eo.TokenData;

//页面端总拦截器
@Component
public class WebPermissionInterceptor extends HandlerInterceptorAdapter {
	
	//private Logger logger = LoggerFactory.getLogger(PermissionInterceptor.class);//本类日志
	//private Logger urlLog = LoggerFactory.getLogger("urlLog");//自定义输出日志
	
	private RedisTemplate<String,Object> redisTemplate = null;
	
	private Environment environment = null;

	/**
	 * httpServletRequest.getRequestURI()            -------------------- /javaweb/app/html/home.html
	 * httpServletRequest.getRequestURL().toString() -------------------- http://localhost:8080/javaweb/app/html/home.html 
	 * httpServletRequest.getServletPath()           -------------------- /app/html/home.html
	 * request.getRequestDispatcher("/test").forward(request,response);//服务端跳转
	 * response.sendRedirect(basePath+"/test");//页面端跳转
	 * System.getProperty("catalina.home")+File.separator+"webapps\\项目名称\\WEB-INF\\classes"
	 * request.getSession().getServletContext().getRealPath("/")//位于WebRoot下
	 */
	@SuppressWarnings("unchecked")
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception {
		if(ignoreUrl(handler)) {
	        return true;
		}
		//BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext()); 
		//RedisTemplate redisTemplate = (RedisTemplate) factory.getBean("redisTemplate"); 
		//RedisTemplate<String,Object> redisTemplate = (RedisTemplate<String,Object>)ApplicationContextHelper.getBean(SystemConstant.REDIS_TEMPLATE);
		//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath(); 
		if(redisTemplate==null){
			redisTemplate = (RedisTemplate<String,Object>)ApplicationContextHelper.getBean(SystemConstant.REDIS_TEMPLATE);
		}
		if(environment==null){
			environment = (Environment)ApplicationContextHelper.getBean(SystemConstant.ENVIRONMENT);
		}
		Long redisSessionTimeout = Long.parseLong(environment.getProperty("redis.session.timeout"));//获得配置文件中redis设置session失效的时间
		String token = CommonConstant.NULL_VALUE;
		try{
			token = request.getHeader(SystemConstant.HEAD_TOKEN);
			token = SecretUtil.base64DecoderString(token,"UTF-8");
	    	String tokens[] = token.split(CommonConstant.COMMA);//token由三部分组成：token,userId,type
	    	token = tokens[1]+CommonConstant.COMMA+tokens[2];//userId+type
		}catch(Exception e){
			//do nothing
		}
		String servletPath = request.getServletPath();
		boolean nullOrEmptyHead = Stream.of(token/*,userId,type*/).anyMatch(i->i==null||i.trim().equals(CommonConstant.EMPTY_VALUE));
		if(nullOrEmptyHead){
			request.getRequestDispatcher(ApiConstant.REQUEST_PARAMETER_LOST).forward(request,response);
			return false;
		}
		TokenData tokenData = (TokenData)(redisTemplate.opsForValue().get(token));
		if(tokenData==null){
			request.getRequestDispatcher(ApiConstant.INVALID_REQUEST).forward(request,response);
			return false;
		}
        if(!(tokenData.getToken().equals(request.getHeader(SystemConstant.HEAD_TOKEN)))){
			request.getRequestDispatcher(ApiConstant.REQUEST_PARAMETER_ERROR).forward(request,response);
			return false;
		}
		if(servletPath.startsWith(SystemConstant.URL_LOGIN_WEB_PERMISSION)){//该路径下只要登录即可访问，不需要权限
			redisTemplate.opsForValue().set(token,tokenData,Duration.ofMinutes(redisSessionTimeout));
			return true;
		}
		long count = tokenData.getAuthOperateList().stream().filter(i->{
			String splitApiUrl[] = i.getApiUrl().split(CommonConstant.COMMA);//某一操作可能会调用多个附属操作（即API接口），多个附属操作约定用逗号分开
			for(String str:splitApiUrl){
				if(servletPath.startsWith(str)){
					return true;
				}
			}
			return false; 
		}).count();
		if(count<=0){
			request.getRequestDispatcher(ApiConstant.NO_AUTHORY).forward(request,response);
			return false;
		}else{
			redisTemplate.opsForValue().set(token,tokenData,Duration.ofMinutes(redisSessionTimeout));
			return true;
		}
	}
	
	private boolean ignoreUrl(Object handler) {
	    if(handler instanceof HandlerMethod) {
            Method method = ((HandlerMethod)handler).getMethod();
            if (AnnotatedElementUtils.isAnnotated(method,IgnoreUrl.class)) {
                //IgnoreUrl ignoreUrl = method.getAnnotation(IgnoreUrl.class);
                return true;
            }
        }
	    return false;
	}

}

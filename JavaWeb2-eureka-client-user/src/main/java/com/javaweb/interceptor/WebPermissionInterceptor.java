package com.javaweb.interceptor;

import java.lang.reflect.Method;
import java.time.Duration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.javaweb.annotation.url.IgnoreUrl;
import com.javaweb.base.BaseTool;
import com.javaweb.constant.ApiConstant;
import com.javaweb.constant.SystemConstant;
import com.javaweb.web.eo.TokenData;

//页面端总拦截器
@Component
public class WebPermissionInterceptor extends HandlerInterceptorAdapter {
	
	//private Logger logger = LoggerFactory.getLogger(PermissionInterceptor.class);//本类日志
	//private Logger urlLog = LoggerFactory.getLogger("urlLog");//自定义输出日志

	/**
	 * httpServletRequest.getRequestURI()            -------------------- /javaweb/app/html/home.html
	 * httpServletRequest.getRequestURL().toString() -------------------- http://localhost:8080/javaweb/app/html/home.html 
	 * httpServletRequest.getServletPath()           -------------------- /app/html/home.html
	 * request.getRequestDispatcher("/test").forward(request,response);//服务端跳转
	 * response.sendRedirect(basePath+"/test");//页面端跳转
	 * System.getProperty("catalina.home")+File.separator+"webapps\\项目名称\\WEB-INF\\classes"
	 * request.getSession().getServletContext().getRealPath("/")//位于WebRoot下
	 */
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception {//本方法异常不应该主动抛出
        /**
		if(request.getMethod().toUpperCase().equals("OPTIONS")){
            return true;//通过所有OPTIONS请求
        }
        */
		String servletPath = request.getServletPath();
		if(ignoreUrl(handler)) {
	        return true;
		}
		//BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext()); 
		//RedisTemplate redisTemplate = (RedisTemplate) factory.getBean("redisTemplate"); 
		//RedisTemplate<String,Object> redisTemplate = (RedisTemplate<String,Object>)ApplicationContextHelper.getBean(SystemConstant.REDIS_TEMPLATE);
		//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath(); 
		Long redisSessionTimeout = Long.parseLong(BaseTool.getEnvironment().getProperty("redis.session.timeout"));//获得配置文件中redis设置session失效的时间
		TokenData tokenData = BaseTool.getTokenData(BaseTool.getToken(request)); 
		if(tokenData==null){
			request.getRequestDispatcher(ApiConstant.INVALID_REQUEST).forward(request,response);
			return false;
		}
        if((!(tokenData.getToken().equals(request.getHeader(SystemConstant.HEAD_TOKEN)))) && (!(tokenData.getToken().equals(request.getParameter(SystemConstant.HEAD_TOKEN))))){
			request.getRequestDispatcher(ApiConstant.REQUEST_PARAMETER_ERROR).forward(request,response);
			return false;
		}
		if(servletPath.startsWith(SystemConstant.URL_LOGIN_WEB_PERMISSION)){//该路径下只要登录即可访问，不需要权限
			BaseTool.getRedisTemplate().opsForValue().set(BaseTool.getRedisTokenKey(tokenData),tokenData,Duration.ofMinutes(redisSessionTimeout));
			return true;
		}
		long count = tokenData.getApiUrlList().stream().filter(i->{return servletPath.startsWith(i);}).count();
		if(count<=0){
			request.getRequestDispatcher(ApiConstant.NO_AUTHORY).forward(request,response);
			return false;
		}else{
			BaseTool.getRedisTemplate().opsForValue().set(BaseTool.getRedisTokenKey(tokenData),tokenData,Duration.ofMinutes(redisSessionTimeout));
			return true;
		}
	}
	
	private boolean ignoreUrl(Object handler) {
	    if(handler instanceof HandlerMethod) {
            Method method = ((HandlerMethod)handler).getMethod();
            if (AnnotatedElementUtils.isAnnotated(method,IgnoreUrl.class)) {//IgnoreUrl ignoreUrl = method.getAnnotation(IgnoreUrl.class);
                return true;
            }
        }
	    return false;
	}
	
}

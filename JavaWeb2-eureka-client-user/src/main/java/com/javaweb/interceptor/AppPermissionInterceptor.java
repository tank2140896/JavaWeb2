package com.javaweb.interceptor;

import java.lang.reflect.Method;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.javaweb.annotation.url.IgnoreUrl;
import com.javaweb.constant.ApiConstant;
import com.javaweb.constant.CommonConstant;
import com.javaweb.constant.SystemConstant;
import com.javaweb.context.ApplicationContextHelper;
import com.javaweb.web.eo.TokenData;

@Component
public class AppPermissionInterceptor extends HandlerInterceptorAdapter {
	
	//private Logger logger = LoggerFactory.getLogger(PermissionInterceptor.class);//本类日志
	//private Logger urlLog = LoggerFactory.getLogger("urlLog");//自定义输出日志
	
	private RedisTemplate<String,Object> redisTemplate1 = null;

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
		//BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext()); 
		//RedisTemplate redisTemplate = (RedisTemplate) factory.getBean("redisTemplate"); 
		//RedisTemplate<String,Object> redisTemplate = (RedisTemplate<String,Object>)ApplicationContextHelper.getBean(SystemConstant.REDIS_TEMPLATE);
		//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();  
	    if(ignoreUrl(handler)) {
            return true;
        }
	    if(redisTemplate1==null){
			redisTemplate1 = (RedisTemplate<String,Object>)ApplicationContextHelper.getBean(SystemConstant.REDIS_TEMPLATE_1);
		}
		//String userId = request.getHeader(SystemConstant.HEAD_USERID);
		String token = request.getHeader(SystemConstant.HEAD_TOKEN);
		//String type = request.getHeader(SystemConstant.HEAD_TYPE);
		//String servletPath = request.getServletPath();
		boolean nullOrEmptyHead = Stream.of(/*userId,*/token/*,type*/).anyMatch(i->i==null||i.trim().equals(CommonConstant.EMPTY_VALUE));
		if(nullOrEmptyHead){
			request.getRequestDispatcher(ApiConstant.REQUEST_PARAMETER_LOST).forward(request,response);
			return false;
		}
		/**
		if(!PatternUtil.isPattern(type,PatternConstant.HEAD_TYPE_PATTERN)){//0:admin;1:web;2:Android;3:IOS
			request.getRequestDispatcher(ApiConstant.REQUEST_PARAMETER_LOST).forward(request,response);
			return false;
		}
		*/
		TokenData tokenData = (TokenData)(redisTemplate1.opsForValue().get(token/*String.join(CommonConstant.COMMA,userId,type)*/));
		if(tokenData==null){
			request.getRequestDispatcher(ApiConstant.INVALID_REQUEST).forward(request,response);
			return false;
		}
		if(!(tokenData.getToken().equals(token))/*(String.join(CommonConstant.COMMA,tokenData.getUser().getUserId(),tokenData.getType(),tokenData.getToken()).equals(String.join(CommonConstant.COMMA,userId,type,token)))*/){
			request.getRequestDispatcher(ApiConstant.REQUEST_PARAMETER_ERROR).forward(request,response);
			return false;
		}
		/**
		if(servletPath.startsWith(SystemConstant.URL_LOGIN_WEB_PERMISSION)){//该路径下只要登录即可访问，不需要权限
			redisTemplate1.opsForValue().set(String.join(CommonConstant.COMMA,userId,type),tokenData,SystemConstant.SYSTEM_DEFAULT_SESSION_OUT,TimeUnit.MINUTES);
			return true;
		}
		long count = tokenData.getAuthOperateList().stream().filter(i->{
			String splitApiUrl[] = i.getApiUrl().split(CommonConstant.COMMA);//某一操作可能会调用多个附属操作，多个附属操作约定用逗号分开
			for(String str:splitApiUrl){
				if(servletPath.startsWith(str)){
					redisTemplate1.opsForValue().set(String.join(CommonConstant.COMMA,userId,type),tokenData,SystemConstant.SYSTEM_DEFAULT_SESSION_OUT,TimeUnit.MINUTES);
					return true;
				}
			}
			return false; 
		}).count();
		if(count<=0){
			request.getRequestDispatcher(ApiConstant.NO_AUTHORY).forward(request,response);
			return false;
		}else{
			redisTemplate1.opsForValue().set(String.join(CommonConstant.COMMA,userId,type),tokenData,SystemConstant.SYSTEM_DEFAULT_SESSION_OUT,TimeUnit.MINUTES);
			return true;
		}
		*/
		return true;
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

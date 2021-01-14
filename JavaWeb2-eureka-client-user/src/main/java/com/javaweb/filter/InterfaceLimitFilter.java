package com.javaweb.filter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.javaweb.base.BaseSystemMemory;
import com.javaweb.base.BaseTool;
import com.javaweb.constant.ApiConstant;
import com.javaweb.constant.CommonConstant;
import com.javaweb.constant.SystemConstant;
import com.javaweb.util.core.HttpUtil;
import com.javaweb.util.core.StringUtil;
import com.javaweb.web.eo.TokenData;
import com.javaweb.web.po.Interfaces;

@Order(1)//数字越小优先级越高
@WebFilter(filterName="interfaceLimitFilter",urlPatterns={SystemConstant.URL_ALL_PATTERN})
@Component
public class InterfaceLimitFilter implements Filter {
	
	//import org.slf4j.Logger;
	//import org.slf4j.LoggerFactory;
	//private Logger logger = LoggerFactory.getLogger(InterfaceLimitFilter.class);
	
	public void init(FilterConfig filterConfig) throws ServletException {

    }

	public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		//HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		String url = httpServletRequest.getServletPath();
		List<Interfaces> list = BaseSystemMemory.interfacesList;
		Optional<Interfaces> optional = list.stream().filter(e->url.startsWith(e.getBaseUrl())).findFirst();
		
		if(optional.isPresent()){
			String key = CommonConstant.EMPTY_VALUE;
			if(url!=null&&url.startsWith(SystemConstant.URL_WEB_INTERCEPTOR_START_PREFIX)){
				TokenData tokenData = BaseTool.getTokenData(BaseTool.getToken(httpServletRequest));
				if((tokenData!=null)&&(tokenData.getUser()!=null)&&(!CommonConstant.EMPTY_VALUE.equals(StringUtil.handleNullString(tokenData.getUser().getUserId())))){
					key = tokenData.getUser().getUserId() + CommonConstant.COMMA + url;//禁用户
				}else{
					key = HttpUtil.getIpAddress(httpServletRequest) + CommonConstant.COMMA + url;//禁IP
				}
			}else{
				key = HttpUtil.getIpAddress(httpServletRequest) + CommonConstant.COMMA + url;//禁IP
			}
			//logger.info("接口限流key值为："+key);
			BaseTool.getRedisTemplate().opsForHash().increment(SystemConstant.REDIS_INTERFACE_COUNT_KEY,url,1);//接口调用次数加1（接口调用次数统计，若不需要使用注释掉即可）
			Interfaces interfaces = optional.get();
			//TODO 这里还未完善，目前只支持秒且其它字段也还没有格式限制
			if(("秒".equals(interfaces.getUnit()))&&(interfaces.getTimes()!=null)&&(interfaces.getCounts()!=null)){
				boolean isSuccess = BaseTool.getRedisTemplate().opsForValue().setIfAbsent(key,Integer.parseInt(interfaces.getCounts()),Integer.parseInt(interfaces.getTimes()),TimeUnit.SECONDS);//可以设置在5秒内不能重复提交表单
				if(isSuccess){
					chain.doFilter(request,response);
				}else{
					httpServletRequest.getRequestDispatcher(ApiConstant.REQUEST_LIMIT).forward(request,response);
				}
			}else{
				chain.doFilter(request,response);
			}
		}else{
			httpServletRequest.getRequestDispatcher(ApiConstant.NOT_FOUND).forward(request,response);
		}
	}
	
    public void destroy() {

    }

}

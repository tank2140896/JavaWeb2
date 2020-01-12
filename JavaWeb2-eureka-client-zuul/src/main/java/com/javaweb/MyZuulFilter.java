package com.javaweb;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class MyZuulFilter extends ZuulFilter {
    
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        System.out.println(request.getMethod()+","+request.getRequestURL().toString());
        //Object accessToken = request.getParameter("token");
        return null;
    }
    
    //pre、routing、post、error
    public String filterType() {
        return "pre";
    }

    public boolean shouldFilter() {
        return true;
    }

    public int filterOrder() {
        return 0;
    }

}

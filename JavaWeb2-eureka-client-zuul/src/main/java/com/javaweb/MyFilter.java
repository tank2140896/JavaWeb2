package com.javaweb;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

@Component
public class MyFilter extends ZuulFilter {
    
    public Object run() throws ZuulException {
        //HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        //String tokern = request.getHeader("token");
        return null;
    }

    public boolean shouldFilter() {
        return true;
    }

    public String filterType() {
        return "pre";
    }

    public int filterOrder() {
        return 0;
    }
    
}
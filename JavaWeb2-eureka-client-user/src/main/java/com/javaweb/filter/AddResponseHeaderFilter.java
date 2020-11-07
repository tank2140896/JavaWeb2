package com.javaweb.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AddResponseHeaderFilter extends OncePerRequestFilter {

	//x.y.z：x为主版本号；y为次版本号（功能）；z为修订号（bug修复）
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		response.addHeader("Api-Version","1.0.0");
		filterChain.doFilter(request, response);
	}

}

package com.javaweb.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.base.BaseController;

@RequestMapping("/web/belogin")
@RestController
//此Controller中的所有接口需要登录，向登录成功者开放
public class LoginOpenController extends BaseController {
	
	@GetMapping("/see")
	public String see(){
		return "abc";
	}
	
}
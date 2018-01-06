package com.javaweb.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.base.BaseController;

@RequestMapping("/web/logged")
@RestController
public class LoginOpenController extends BaseController {
	
	@GetMapping("/see")
	public String see(){
		return "abc";
	}
	
}
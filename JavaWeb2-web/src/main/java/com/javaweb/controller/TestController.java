package com.javaweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.javaweb.service.rbac.UserService;

@RestController
public class TestController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/test")
	public String index(){
		return "aaa";
	}
	
	@PostMapping("/test2")
	public String index2(@RequestBody JsonNode jsonNode){
		System.out.println(jsonNode);
		return "bbb";
	}
	
}

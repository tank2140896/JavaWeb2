package com.javaweb.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.web.dao.ds1.UserDao;
import com.javaweb.web.po.User;

@RestController
public class TestController {
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping("/test")
	public String test(){
		userDao.delete(new User());
		return "test";
	}

}

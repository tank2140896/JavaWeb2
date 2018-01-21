package com.javaweb.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.constant.SystemConstant;
import com.javaweb.web.dao.ds1.UserDao;
import com.javaweb.web.eo.PageData;
import com.javaweb.web.eo.user.UserList;
import com.javaweb.web.po.User;
import com.javaweb.web.service.UserService;

@RequestMapping("/web/sys/user")
@RestController
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDao userDao;
	
	@PostMapping("/list")
	public BaseResponseResult userSearch(@RequestBody UserList userList){
		PageData pageData = new PageData();
		List<User> list = userDao.selectAll(User.class);
		pageData.setData(list);
		pageData.setTotalPage(5L);
		pageData.setTotalSize(50L);
		return new BaseResponseResult(SystemConstant.SUCCESS,"success"/*getMessage("system.error")*/,pageData);
	}
	
}
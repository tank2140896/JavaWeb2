package com.javaweb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.base.BaseController;
import com.javaweb.constant.SystemConstant;
import com.javaweb.dataobject.eo.Page;
import com.javaweb.dataobject.eo.TokenData;
import com.javaweb.dataobject.eo.UserSearchCondition;
import com.javaweb.help.GsonHelp;
import com.javaweb.help.ResponseResult;
import com.javaweb.service.rbac.UserService;

@RestController
@RequestMapping("/web/sys/user")
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	//用户列表接口
	@PostMapping("/list")
	public String login(@RequestBody UserSearchCondition UserSearchCondition,HttpServletRequest request){
		ResponseResult responseResult = null;
		try{
			UserSearchCondition.setUserId(((TokenData)request.getSession().getAttribute("sessionValue")).getUser().getUserId());//只能获取比当前用户等级低的所有用户
			Page page =  userService.listUser(UserSearchCondition);
			responseResult = new ResponseResult(SystemConstant.SUCCESS_CODE,"获取用户信息成功",page);
		}catch(Exception e){
			responseResult = new ResponseResult(SystemConstant.INTERNAL_ERROR_CODE,e.getMessage(),null);
		}
		return new GsonHelp().fromJsonDefault(responseResult);
	}
	
}
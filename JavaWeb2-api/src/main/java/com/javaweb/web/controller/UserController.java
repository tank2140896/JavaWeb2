package com.javaweb.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.base.BaseValidatedGroup;
import com.javaweb.constant.CommonConstant;
import com.javaweb.constant.SystemConstant;
import com.javaweb.web.eo.PageData;
import com.javaweb.web.eo.TokenData;
import com.javaweb.web.eo.user.UserListRequest;
import com.javaweb.web.po.User;
import com.javaweb.web.service.UserService;

@RequestMapping("/web/sys/user")
@RestController
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/list")
	public BaseResponseResult userList(HttpServletRequest request,@RequestBody UserListRequest userListRequest){
		TokenData tokenData = getTokenData(request);
		userListRequest.setLevel(tokenData.getUser().getLevel());
		PageData pageData = userService.userList(userListRequest);
		return new BaseResponseResult(SystemConstant.SUCCESS,getMessage("user.list.success"),pageData);
	}
	
	@DeleteMapping("/delete/{userId}")
	public BaseResponseResult userDelete(@PathVariable("userId") String userId){
		userService.userDelete(userId);
		return new BaseResponseResult(SystemConstant.SUCCESS,getMessage("user.delete.success"),null);
	}
	
	@PostMapping("/add")
	public BaseResponseResult userAdd(HttpServletRequest request,@RequestBody @Validated({BaseValidatedGroup.add.class}) User user,BindingResult bindingResult){
		BaseResponseResult baseResponseResult = new BaseResponseResult();
		if(bindingResult.hasErrors()){
			TokenData tokenData = getTokenData(request);
			User currentUser = tokenData.getUser();
			user.setParentId(currentUser.getUserId());
			user.setLevel(currentUser.getLevel()+1);
			userService.userAdd(user);
			baseResponseResult = new BaseResponseResult(SystemConstant.VALIDATE_ERROR,getValidateMessage(bindingResult),CommonConstant.EMPTY_VALUE);
		}else{
			baseResponseResult = new BaseResponseResult(SystemConstant.SUCCESS,getMessage("user.add.success"),null);
		}
		return baseResponseResult;
	}
	
}
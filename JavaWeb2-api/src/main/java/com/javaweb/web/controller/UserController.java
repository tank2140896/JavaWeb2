package com.javaweb.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.base.BaseValidatedGroup;
import com.javaweb.constant.CommonConstant;
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.util.core.DateUtil;
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.TokenData;
import com.javaweb.web.eo.user.RoleInfoResponse;
import com.javaweb.web.eo.user.UserListRequest;
import com.javaweb.web.po.User;

@RestController
@RequestMapping("/web/pc/sys/user")
public class UserController extends BaseController {
	
	@PostMapping("/list")
	public BaseResponseResult userList(HttpServletRequest request,@RequestBody UserListRequest userListRequest){
		TokenData tokenData = getTokenData(request);
		userListRequest.setLevel(tokenData.getUser().getLevel());
		Page page = userService.userList(userListRequest);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"user.list.success",page);
	}
	
	@DeleteMapping("/delete/{userId}")
	public BaseResponseResult userDelete(@PathVariable(name="userId",required=true) String userId){
		userService.userDelete(userId);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"user.delete.success",null);
	}
	
	@PostMapping("/add")
	public BaseResponseResult userAdd(HttpServletRequest request,@RequestBody @Validated({BaseValidatedGroup.add.class}) User user,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,bindingResult,CommonConstant.EMPTY_VALUE);
		}else{
			TokenData tokenData = getTokenData(request);
			User currentUser = tokenData.getUser();
			user.setUserId(UUID.randomUUID().toString());
			user.setParentId(currentUser.getUserId());
			user.setLevel(currentUser.getLevel()+1);
			user.setCreateDate(DateUtil.getDefaultDate());
			user.setCreator(currentUser.getUserName());
			userService.userAdd(user);
			return getBaseResponseResult(HttpCodeEnum.SUCCESS,"user.add.success",null);
		}
	}
	
	@PutMapping("/modify")
	public BaseResponseResult userModify(HttpServletRequest request,@RequestBody @Validated({BaseValidatedGroup.update.class}) User user,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,getValidateMessage(bindingResult),CommonConstant.EMPTY_VALUE);
		}else{
			TokenData tokenData = getTokenData(request);
			User currentUser = tokenData.getUser();
			user.setUpdateDate(DateUtil.getDefaultDate());
			user.setUpdater(currentUser.getUserName());
			userService.userModify(user);
			return getBaseResponseResult(HttpCodeEnum.SUCCESS,"user.modify.success",null);
		}
	}
	
	@GetMapping("/detail/{userId}")
	public BaseResponseResult userDetail(@PathVariable(name="userId",required=true) String userId){
		User user = userService.userDetail(userId);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"user.detail.success",user);
	}
	
	@GetMapping("/userRoleInfo/{userId}")
	public BaseResponseResult userRoleInfo(@PathVariable(name="userId",required=true) String userId){
		List<RoleInfoResponse> list = userService.userRoleInfo(userId);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"user.userRoleInfo.success",list);
	}
	
	@PostMapping("/roleAssignment/{userId}")
	public BaseResponseResult roleAssignment(@PathVariable(name="userId",required=true) String userId,@RequestBody List<String> list){
		Map<String,Object> map = new HashMap<>();
		map.put("userId",userId);
		map.put("list",list);
		userService.roleAssignment(map);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"user.roleAssignment.success",null);
	}
	
}
package com.javaweb.web.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.annotation.url.ControllerMethod;
import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.constant.ApiConstant;
import com.javaweb.constant.CommonConstant;
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.util.core.PageUtil;
import com.javaweb.util.core.StringUtil;
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.onlineUser.OnlineUserListRequest;
import com.javaweb.web.po.User;

//登录且需要权限才可访问的在线用户管理模块
@RestController
@RequestMapping(ApiConstant.WEB_ONLINE_USER_PREFIX)
public class OnlineUserController extends BaseController {
	
	@PostMapping(ApiConstant.ONLINE_USER_LIST)
	@ControllerMethod(interfaceName="在线用户列表接口")
	public BaseResponseResult list(@RequestBody OnlineUserListRequest onlineUserListRequest){
		Set<String> set = stringRedisTemplate.keys("*,1");//只取web端的在线用户
		set = (set==null?new HashSet<>():set);
		List<String> sortedList = new ArrayList<>();
		for(String str:set){
			sortedList.add(str.split(CommonConstant.COMMA)[0]);
		}
		List<User> userList = userService.getAllUsers();
		String userName = onlineUserListRequest.getUserName();
		if(StringUtil.handleNullString(userName).trim().equals(CommonConstant.EMPTY_VALUE)){
			sortedList = sortedList.stream().sorted().collect(Collectors.toList());
		}else{
			sortedList = sortedList.stream().filter(e->{
				for(User user:userList){
					if(e.equals(user.getUserId())&&user.getUserName().contains(userName)){
						return true;
					}
				}
				return false;
			}).sorted().collect(Collectors.toList());
		}
		sortedList = PageUtil.getSubList(sortedList,onlineUserListRequest.getPageSize(),onlineUserListRequest.getCurrentPage());
		if(sortedList==null){
			sortedList = new ArrayList<>();
		}
		List<User> list = new ArrayList<>();
		if(sortedList.size()!=0){
			list = userService.getUsersByUserId(sortedList);
		}
		Page page = new Page(onlineUserListRequest,list,(set.size()+0L));
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"onlineUser.list.success",page);
	}
	
	@GetMapping(ApiConstant.ONLINE_USER_OFFLINE)
	@ControllerMethod(interfaceName="用户下线接口")
	public BaseResponseResult offline(@PathVariable(name="key",required=true) String key){
		stringRedisTemplate.delete(key);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"onlineUser.offline.success",null);
	}

}

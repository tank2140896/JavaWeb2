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

import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.constant.ApiConstant;
import com.javaweb.constant.CommonConstant;
import com.javaweb.constant.SwaggerConstant;
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.util.core.PageUtil;
import com.javaweb.util.core.StringUtil;
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.onlineUser.OnlineUserListRequest;
import com.javaweb.web.po.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags=SwaggerConstant.SWAGGER_ONLINE_USER_CONTROLLER_TAGS)
@RestController
@RequestMapping(ApiConstant.WEB_ONLINE_USER_PREFIX)
public class OnlineUserController extends BaseController {
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_ONLINE_USER_LIST)
	@PostMapping(ApiConstant.ONLINE_USER_LIST)
	public BaseResponseResult list(@RequestBody OnlineUserListRequest onlineUserListRequest){
		Set<String> set = stringRedisTemplate.keys("*,1");//只取web端的在线用户
		set = (set==null?new HashSet<>():set);
		List<String> sortedList = new ArrayList<>();
		for(String str:set){
			sortedList.add(str.split(CommonConstant.COMMA)[0]);
		}
		String userId = onlineUserListRequest.getUserId();
		if(StringUtil.handleNullString(userId).trim().equals(CommonConstant.EMPTY_VALUE)){
			sortedList = sortedList.stream().sorted().collect(Collectors.toList());
		}else{
			sortedList = sortedList.stream().filter(e->e.contains(onlineUserListRequest.getUserId())).sorted().collect(Collectors.toList());
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
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_ONLINE_USER_OFFLINE)
	@GetMapping(ApiConstant.ONLINE_USER_OFFLINE)
	public BaseResponseResult offline(@PathVariable(name="key",required=true) String key){
		stringRedisTemplate.delete(key);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"onlineUser.offline.success",null);
	}

}

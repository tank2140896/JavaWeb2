package com.javaweb.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.constant.ApiConstant;
import com.javaweb.constant.CommonConstant;
import com.javaweb.constant.SwaggerConstant;
import com.javaweb.constant.SystemConstant;
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.web.po.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags=SwaggerConstant.SWAGGER_ONLINE_CHAT_CONTROLLER_TAGS)
@RestController
@RequestMapping(ApiConstant.WEB_ONLINE_CHAT_PREFIX)
public class OnlineChatController extends BaseController {
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_ONLINE_CHAT_USER_LIST)
	@GetMapping(ApiConstant.ONLINE_CHAT_USER_LIST)
	public BaseResponseResult userList(){
		Set<String> set = stringRedisTemplate.keys("*,1");//只取web端的在线用户，这里做的较简单，全部取出不分页
		/**
		数组手动分页处理方式：
		1、List<String> sortedList = set.stream().sorted().collect(Collectors.toList());
		2、调用PageUtil.getSubList(list,pageSize,currentPage)
		*/
		List<String> noSortist = new ArrayList<>();
		for(String str:set){
			if(!str.equals(SystemConstant.ADMIN+CommonConstant.COMMA+CommonConstant.ZERO_STRING_VALUE)){//去除管理员
				noSortist.add(str.split(CommonConstant.COMMA)[0]);
			}
		}
		List<User> list = new ArrayList<>();
		if(noSortist.size()!=0){
			list = userService.getUsersByUserId(noSortist);
		}
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"onlineChat.userList.success",list);
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_ONLINE_CHAT_USER_OFFLINE)
	@GetMapping(ApiConstant.ONLINE_CHAT_USER_OFFLINE)
	public BaseResponseResult userOffline(@PathVariable(name="key",required=true) String key){
		stringRedisTemplate.delete(key);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"onlineChat.userOffline.success",null);
	}

}

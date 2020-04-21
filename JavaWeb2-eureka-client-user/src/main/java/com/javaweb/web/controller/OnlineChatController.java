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
		List<User> list = new ArrayList<>();
		Set<String> set = stringRedisTemplate.keys("*,*");
		for(String str:set){
			if(!str.equals(SystemConstant.ADMIN+CommonConstant.COMMA+CommonConstant.ZERO_STRING_VALUE)){
				list.add(userService.userDetail(str.split(CommonConstant.COMMA)[0]));
			}
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

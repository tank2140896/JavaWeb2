package com.javaweb.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.constant.SystemConstant;
import com.javaweb.web.eo.TokenData;
import com.javaweb.web.eo.chat.ChatRequest;
import com.javaweb.web.po.User;

@RestController
@RequestMapping("/web/other/onlineChat")
public class OnlineChatController extends BaseController {
	
	@PostMapping("/sendChatMessage")
	public BaseResponseResult sendChatMessage(HttpServletRequest request,@RequestBody ChatRequest chatRequest){
		TokenData tokenData = getTokenData(request);
		User user = tokenData.getUser();
		webSocketHandleService.onMessage(chatRequest.getMessage(),user);
		return new BaseResponseResult(SystemConstant.SUCCESS,getMessage("onlineChat.chat.success"),null);
	}
	
}
package com.javaweb.config.websocket;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.base.BaseInject;
import com.javaweb.eo.chat.ChatRequest;

@RestController
@RequestMapping("/web/other/onlineChat")
public class WebsocketController extends BaseInject {
	
	@PostMapping("/sendChatMessage")
	public String sendChatMessage(HttpServletRequest request,@RequestBody ChatRequest chatRequest){
		TokenData tokenData = new TokenData();//getTokenData(request);
		User user = tokenData.getUser();
		webSocketHandleService.onMessage(chatRequest.getMessage(),user);
		return "success";
	}
	
}
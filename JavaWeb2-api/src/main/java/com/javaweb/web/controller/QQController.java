package com.javaweb.web.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.config.QQConfig;
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.util.core.HttpUtil;
import com.javaweb.web.eo.qq.AccessTokenResponse;
import com.javaweb.web.eo.qq.OpenIdResponse;
import com.javaweb.web.eo.qq.UserInfoResponse;

/**
 * 参考:http://wiki.connect.qq.com
 * 步骤:
 * 1.生成授权连接,需要配置回调地址:https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=101420900&redirect_uri=http://127.0.0.1:8764/mobile/qqLoginCallback&state=1234656
 * 2.通过授权码换取assessToken:https://graph.qq.com/oauth2.0/token?grant_type=authorization_code&client_id=101420900&client_secret=bd56a336f6ac49a65005595c2a41201a&code=E28F27AFC3D8A17B75F05E9661FB933E&redirect_uri=http://127.0.0.1:8764/mobile/qqLoginCallback	
 * 3.使用assessToken换取openid:https://graph.qq.com/oauth2.0/me?access_token=CF8775A510EA68ED8576C9F675B42862
 * 4.使用openid和assessToken获取用户信息:https://graph.qq.com/user/get_user_info?access_token=CF8775A510EA68ED8576C9F675B42862&oauth_consumer_key=12345&openid=537F314752DA3A491B4F66C04D6AD9FF
 */
@RestController
public class QQController extends BaseController {
	
	@Autowired
	private QQConfig qqConfig;
	
	//生成授权连接,需要配置回调地址
	@GetMapping("/qq/getAuthorizationCode")
	public BaseResponseResult getAuthorizationCode() {
		String qqForwardUrl = null;
		try{
			qqForwardUrl = String.format(qqConfig.getQqAuthorizeUrl(),qqConfig.getQqAppid(),qqConfig.getQqRedirectUrl(),"1001");
			qqForwardUrl = URLEncoder.encode(qqForwardUrl,"UTF-8");
			return getBaseResponseResult(HttpCodeEnum.SUCCESS,"qq.getAuthorizationCodeSuccess",qqForwardUrl);
		}catch(Exception e){
			return getBaseResponseResult(HttpCodeEnum.SUCCESS,"qq.getAuthorizationCodeFail",qqForwardUrl);
		}
	}
	
	//通过授权码换取assessToken
	@GetMapping("/qq/userLoginCallBack")
	public BaseResponseResult userLoginCallBack(HttpServletRequest request) {
		UserInfoResponse userInfoResponse = null;
		try {
			String code = request.getParameter("code");
			//String state = request.getParameter("state");
			String url = String.format(qqConfig.getQqAccessTokenUrl(),qqConfig.getQqAppid(),qqConfig.getQqAppkey(),code,qqConfig.getQqRedirectUrl());
			url = URLEncoder.encode(url,"UTF-8");
			String out = HttpUtil.defaultGetRequest(url,null);
			AccessTokenResponse accessTokenResponse = new ObjectMapper().readValue(out,AccessTokenResponse.class);
			String accessToken = accessTokenResponse.getAccess_token();
			url = String.format(qqConfig.getQqOpenidUrl(),accessToken);
			url = URLEncoder.encode(url,"UTF-8");
			out = HttpUtil.defaultGetRequest(url,null);
			OpenIdResponse openIdResponse = new ObjectMapper().readValue(out,OpenIdResponse.class);
			String openId = openIdResponse.getOpenid();
			//获取openId后可以去数据库查询下是否有该用户信息
			url = String.format(qqConfig.getQqUserInfo(),accessToken,qqConfig.getQqAppid(),openId);
			url = URLEncoder.encode(url,"UTF-8");
			out = HttpUtil.defaultGetRequest(url,null);
			userInfoResponse = new ObjectMapper().readValue(out,UserInfoResponse.class);
			return getBaseResponseResult(HttpCodeEnum.SUCCESS,"qq.userLoginCallBackSuccess",userInfoResponse);
		}catch (Exception e) {
			return getBaseResponseResult(HttpCodeEnum.SUCCESS,"qq.userLoginCallBackFail",userInfoResponse);
		}
	}

}

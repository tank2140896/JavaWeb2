package com.javaweb.web.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.config.WeixinConfig;
import com.javaweb.constant.SystemConstant;
import com.javaweb.util.core.HttpUtil;
import com.javaweb.web.eo.weixin.AccessTokenResponse;
import com.javaweb.web.eo.weixin.UserInfoResponse;

//更多参考:https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&lang=zh_CN
@RestController
public class WenxinController extends BaseController {
	
	@Autowired
	private WeixinConfig weixinConfig;
	
	//微信扫码登录URL获取(currentPageUrl用于扫码登录后跳转到自己网站的主页)
	@GetMapping("/weixin/getLoginUrl")
	public BaseResponseResult getLoginUrl(@RequestParam(name="currentPageUrl",required=true) String currentPageUrl) {
		String openQrcodeUrl = null;
		try{
			openQrcodeUrl = String.format(weixinConfig.getWxopenQrcodeUrl(),weixinConfig.getWxopenAppid(),URLEncoder.encode(weixinConfig.getWxopenRedirectUrl(),"UTF-8"),currentPageUrl);
			return new BaseResponseResult(SystemConstant.SUCCESS,getMessage("weixin.getLoginUrlSuccess"),openQrcodeUrl);
		}catch(Exception e){
			return new BaseResponseResult(SystemConstant.SUCCESS,getMessage("weixin.getLoginUrlFail"),openQrcodeUrl);
		}
	}
	
	//微信扫码登录成功后的回调处理,用来获得access_token
	@GetMapping("/weixin/userLoginCallBack")
	public BaseResponseResult userLoginCallBack(HttpServletRequest request,HttpServletResponse response,
			                        @RequestParam(name="code",required=true) String code,
			                        @RequestParam(name="state",required=true) String state/*state可以用于页面跳转,一般需要带上http://前缀*/){
		try{
			//这里跳过了校验access_token、刷新access_token等步骤
			String url = String.format(weixinConfig.getWxopenAccessTokenUrl(),weixinConfig.getWxopenAppid(),weixinConfig.getWxopenAppsecret(),code);
			AccessTokenResponse accessTokenResponse = new ObjectMapper().readValue(HttpUtil.defaultGetRequest(url),AccessTokenResponse.class);
			url = String.format(weixinConfig.getWxopenUserInfo(),accessTokenResponse.getAccess_token(),accessTokenResponse.getOpenid());
			UserInfoResponse userInfoResponse = new ObjectMapper().readValue(HttpUtil.defaultGetRequest(url),UserInfoResponse.class);
			return new BaseResponseResult(SystemConstant.SUCCESS,getMessage("weixin.userLoginCallBackSuccess"),userInfoResponse);
		}catch(Exception e){
			return new BaseResponseResult(SystemConstant.SUCCESS,getMessage("weixin.userLoginCallBackFail"),null);
		}
	}
	
}

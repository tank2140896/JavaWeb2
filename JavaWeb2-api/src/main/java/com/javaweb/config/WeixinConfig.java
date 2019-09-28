package com.javaweb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
public class WeixinConfig {
	
	@Value("${wxopen.public.platform.token}")
	private String wxopenPublicPlatformToken;
	
	@Value("${wxopen.access_token}")
	private String wxopenAccessToken;

	@Value("${wxopen.appid}")
	private String wxopenAppid;
	
	@Value("${wxopen.appsecret}")
	private String wxopenAppsecret;
	
	@Value("${wxopen.authorize_url}")
	private String wxopenAuthorizeUrl;
	
	@Value("${wxopen.redirect_url}")
	private String wxopenRedirectUrl;
	
	@Value("${wxopen.access_token_url}")
	private String wxopenAccessTokenUrl;
	
	@Value("${wxopen.user_info}")
	private String wxopenUserInfo;
	
	@Value("${wxopen.message_templete}")
	private String wxopenMessageTemplete;
	
	@Value("${wxopen.qrcode_url}")
	private String wxopenQrcodeUrl;
	
	@Value("${wxpay.mer_id}")
	private String wxpayMerId;
	
	@Value("${wxpay.key}")
	private String wxpayKey;
	
	@Value("${wxpay.callback_url}")
	private String wxpayCallbackUrl;
	
	@Value("${wxpay.unifiedorder_url}")
	private String wxpayUnifiedorderUrl;

}

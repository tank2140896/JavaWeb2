package com.javaweb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
public class QQConfig {
	
	@Value("${qq.appid}")
	private String qqAppid;

	@Value("${qq.appkey}")
	private String qqAppkey;

	@Value("${qq.authorize_url}")
	private String qqAuthorizeUrl;
	
	@Value("${qq.redirect_url}")
	private String qqRedirectUrl;
	
	@Value("${qq.access_token_url}")
	private String qqAccessTokenUrl;

	@Value("${qq.openid_url}")
	private String qqOpenidUrl;
	
	@Value("${qq.user_info}")
	private String qqUserInfo;
	
}

package com.javaweb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeixinConfig {

	@Value("${wxopen.appid}")
	private String wxopenAppid;
	
	@Value("${wxopen.appsecret}")
	private String wxopenAppsecret;
	
	@Value("${wxopen.redirect_url}")
	private String wxopenRedirectUrl;
	
	@Value("${wxopen.qrcode_url}")
	private String wxopenQrcodeUrl; 
	
	@Value("${wxopen.access_token_url}")
	private String wxopenAccessTokenUrl;
	
	@Value("${wxopen.user_info}")
	private String wxopenUserInfo;
	
	public String getWxopenAppid() {
		return wxopenAppid;
	}

	public void setWxopenAppid(String wxopenAppid) {
		this.wxopenAppid = wxopenAppid;
	}

	public String getWxopenAppsecret() {
		return wxopenAppsecret;
	}

	public void setWxopenAppsecret(String wxopenAppsecret) {
		this.wxopenAppsecret = wxopenAppsecret;
	}

	public String getWxopenRedirectUrl() {
		return wxopenRedirectUrl;
	}

	public void setWxopenRedirectUrl(String wxopenRedirectUrl) {
		this.wxopenRedirectUrl = wxopenRedirectUrl;
	}

	public String getWxopenQrcodeUrl() {
		return wxopenQrcodeUrl;
	}

	public void setWxopenQrcodeUrl(String wxopenQrcodeUrl) {
		this.wxopenQrcodeUrl = wxopenQrcodeUrl;
	}

	public String getWxopenAccessTokenUrl() {
		return wxopenAccessTokenUrl;
	}

	public void setWxopenAccessTokenUrl(String wxopenAccessTokenUrl) {
		this.wxopenAccessTokenUrl = wxopenAccessTokenUrl;
	}

	public String getWxopenUserInfo() {
		return wxopenUserInfo;
	}

	public void setWxopenUserInfo(String wxopenUserInfo) {
		this.wxopenUserInfo = wxopenUserInfo;
	}
	
}

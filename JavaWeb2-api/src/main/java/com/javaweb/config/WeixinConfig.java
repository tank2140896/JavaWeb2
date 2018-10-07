package com.javaweb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeixinConfig {
	
	@Value("${wx.appid}")
	private String wxAppid;

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
	
	@Value("${wxpay.mer_id}")
	private String wxpayMerId;
	
	@Value("${wxpay.key}")
	private String wxpayKey;
	
	@Value("${wxpay.callback_url}")
	private String wxpayCallbackUrl;
	
	@Value("${wxpay.unifiedorder_url}")
	private String wxpayUnifiedorderUrl;
	
	public String getWxAppid() {
		return wxAppid;
	}

	public void setWxAppid(String wxAppid) {
		this.wxAppid = wxAppid;
	}

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

	public String getWxpayMerId() {
		return wxpayMerId;
	}

	public void setWxpayMerId(String wxpayMerId) {
		this.wxpayMerId = wxpayMerId;
	}

	public String getWxpayKey() {
		return wxpayKey;
	}

	public void setWxpayKey(String wxpayKey) {
		this.wxpayKey = wxpayKey;
	}

	public String getWxpayCallbackUrl() {
		return wxpayCallbackUrl;
	}

	public void setWxpayCallbackUrl(String wxpayCallbackUrl) {
		this.wxpayCallbackUrl = wxpayCallbackUrl;
	}

	public String getWxpayUnifiedorderUrl() {
		return wxpayUnifiedorderUrl;
	}

	public void setWxpayUnifiedorderUrl(String wxpayUnifiedorderUrl) {
		this.wxpayUnifiedorderUrl = wxpayUnifiedorderUrl;
	}

}

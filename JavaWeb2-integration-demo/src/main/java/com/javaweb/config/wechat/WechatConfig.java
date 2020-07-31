package com.javaweb.config.wechat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
public class WechatConfig {
	
	@Value("${wxopen.public.platform.token}")
	private String wxopenPublicPlatformToken;
	
	@Value("${wxopen.access.token}")
	private String wxopenAccessToken;

	@Value("${wxopen.appid}")
	private String wxopenAppid;
	
	@Value("${wxopen.appsecret}")
	private String wxopenAppsecret;
	
	@Value("${wxopen.create.menu.url}")
	private String wxopenCreateMenuUrl;
	
	@Value("${wxopen.user.info}")
	private String wxopenUserInfo;
	
	@Value("${wxopen.user.info.url}")
	private String wxopenUserInfoUrl;
	
	@Value("${wxopen.user.open.id.list.url}")
	private String wxopenUserOpenIdListUrl;
	
	@Value("${wxopen.create.tag.url}")
	private String wxopenCreateTagUrl;
	
	@Value("${wxopen.tag.list.url}")
	private String wxopenTagListUrl;
	
	@Value("${wxopen.modify.tag.url}")
	private String wxopenModifyTagUrl;
	
	@Value("${wxopen.delete.tag.url}")
	private String wxopenDeleteTagUrl;

	@Value("${wxopen.user.tag.list.url}")
	private String wxopenUserTagListUrl;

	@Value("${wxopen.user.batch.tag.url}")
	private String wxopenUserBatchTagUrl;
	
	@Value("${wxopen.user.batch.untag.url}")
	private String wxopenUserBatchUntagUrl;
	
	@Value("${wxopen.user.tag.url}")
	private String wxopenUserTagUrl;
	
	@Value("${wxopen.js.api.ticket}")
	private String wxopenJsApiTicket;
	
	@Value("${wxopen.openid.url}")
	private String wxopenOpenIdUrl;
	
	@Value("${wxopen.face.identify.url}")
	private String wxopenFaceIdentifyUrl;
	
	@Value("${wxopen.authorize.url}")
	private String wxopenAuthorizeUrl;
	
	@Value("${wxopen.redirect.url}")
	private String wxopenRedirectUrl;
	
	@Value("${wxopen.access.token.url}")
	private String wxopenAccessTokenUrl;
	
	@Value("${wxopen.message.templete}")
	private String wxopenMessageTemplete;
	
	@Value("${wxopen.qrcode.url}")
	private String wxopenQrcodeUrl;
	
	@Value("${wxpay.mer.id}")
	private String wxpayMerId;
	
	@Value("${wxpay.key}")
	private String wxpayKey;
	
	@Value("${wxpay.callback.url}")
	private String wxpayCallbackUrl;
	
	@Value("${wxpay.unifiedorder.url}")
	private String wxpayUnifiedorderUrl;

}

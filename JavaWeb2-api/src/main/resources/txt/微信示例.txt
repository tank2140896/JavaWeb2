package com.javaweb.web.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.config.WeixinConfig;
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.util.core.HttpUtil;
import com.javaweb.util.core.SecretUtil;
import com.javaweb.util.core.StringUtil;
import com.javaweb.util.core.XmlUtil;
import com.javaweb.web.eo.weixin.AccessTokenResponse;
import com.javaweb.web.eo.weixin.UserInfoResponse;

/**
 * 更多参考:
 * 扫码登录:https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&lang=zh_CN
 * 微信付款:https://pay.weixin.qq.com/wiki/doc/api/index.html
 * 扫码支付接入指引:https://pay.weixin.qq.com/guide/qrcode_payment.shtml
 */
//@RestController
public class WenxinController extends BaseController {
	
	@Autowired
	private WeixinConfig weixinConfig;
	
	private String createSign(SortedMap<String,String> sortedMap,String key) throws Exception {
		StringBuilder sb = new StringBuilder();
		Set<Map.Entry<String,String>> entrySet = sortedMap.entrySet();
		Iterator<Entry<String,String>> iterator = entrySet.iterator();
		while(iterator.hasNext()){
			Entry<String,String> entry = iterator.next();
			String k = entry.getKey();
			String v = entry.getValue();
			if(StringUtil.handleNullString(v)!=null){//实际项目这里校验更多更严格
				sb.append(k+"="+v+"&");
			}
		}
		sb.append("key="+key);
		return SecretUtil.getMD5(sb.toString()).toUpperCase();
	}
	
	private boolean checkSign(SortedMap<String,String> sortedMap,String key) throws Exception {
		String sign = createSign(sortedMap,key);
		String weixinPaySign = sortedMap.get("sign").toUpperCase();
		return sign.equals(weixinPaySign);
	}
	
	//微信扫码登录URL获取(currentPageUrl用于扫码登录后跳转到自己网站的主页)
	//@GetMapping("/weixin/getLoginUrl")
	public BaseResponseResult getLoginUrl(@RequestParam(name="currentPageUrl",required=true) String currentPageUrl) {
		String openQrcodeUrl = null;
		try{
			openQrcodeUrl = String.format(weixinConfig.getWxopenQrcodeUrl(),weixinConfig.getWxopenAppid(),URLEncoder.encode(weixinConfig.getWxopenRedirectUrl(),"UTF-8"),currentPageUrl);
			return getBaseResponseResult(HttpCodeEnum.SUCCESS,"weixin.getLoginUrlSuccess",openQrcodeUrl);
		}catch(Exception e){
			return getBaseResponseResult(HttpCodeEnum.SUCCESS,"weixin.getLoginUrlFail",openQrcodeUrl);
		}
	}
	
	//微信扫码登录成功后的回调处理,用来获得access_token
	//@GetMapping("/weixin/userLoginCallBack")
	public BaseResponseResult userLoginCallBack(HttpServletRequest request,HttpServletResponse response,
			                        @RequestParam(name="code",required=true) String code,
			                        @RequestParam(name="state",required=true) String state/*state可以用于页面跳转,一般需要带上http://前缀*/){
		try{
			//这里跳过了校验access_token、刷新access_token等步骤
			String url = String.format(weixinConfig.getWxopenAccessTokenUrl(),weixinConfig.getWxopenAppid(),weixinConfig.getWxopenAppsecret(),code);
			AccessTokenResponse accessTokenResponse = new ObjectMapper().readValue(HttpUtil.defaultGetRequest(url,null),AccessTokenResponse.class);
			url = String.format(weixinConfig.getWxopenUserInfo(),accessTokenResponse.getAccess_token(),accessTokenResponse.getOpenid());
			UserInfoResponse userInfoResponse = new ObjectMapper().readValue(HttpUtil.defaultGetRequest(url,null),UserInfoResponse.class);
			return getBaseResponseResult(HttpCodeEnum.SUCCESS,"weixin.userLoginCallBackSuccess",userInfoResponse);
		}catch(Exception e){
			return getBaseResponseResult(HttpCodeEnum.SUCCESS,"weixin.userLoginCallBackFail",null);
		}
	}
	
	//生成微信扫码付款二维码
	//@GetMapping("/weixin/getPayQrCode")
	public void getPayQrCode() throws Exception {
		//仅展示几个重要的必填字段,更多参考:https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1
		SortedMap<String,String> sortedMap = new TreeMap<>();
		sortedMap.put("appid",weixinConfig.getWxAppid());
		sortedMap.put("mch_id",weixinConfig.getWxpayMerId());
		sortedMap.put("notify_url",weixinConfig.getWxpayCallbackUrl());
		String sign = createSign(sortedMap,weixinConfig.getWxpayKey());
		sortedMap.put("sign",sign);
		String xmlStr = XmlUtil.mapToXml(sortedMap);
		xmlStr = HttpUtil.xmlPostRequest(weixinConfig.getWxpayUnifiedorderUrl(),xmlStr);
		Map<String,String> map = XmlUtil.xmlToMap(xmlStr);
		String codeUrl = map.get("code_url");
		System.out.println(codeUrl);
		//最后可以使用谷歌的二维码生成工具来生成二维码
	}
	
	//微信扫码付款成功后的回调处理
	//@PostMapping("/weixin/payCallBack")
	public void payCallBack(HttpServletRequest request,HttpServletResponse response) throws Exception {
		InputStream is = request.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while((line=br.readLine())!=null){
			sb.append(line);
		}
		br.close();
		is.close();
		Map<String,String> map = XmlUtil.xmlToMap(sb.toString());
		boolean pass = checkSign((SortedMap<String,String>)map/*不能强转！！！这里仅仅是为了满足语法,需要有个方法进行转换*/,weixinConfig.getWxpayKey());//校验签名
		if(pass){
			String resultCode = map.get("result_code");
			if("SUCCESS".equals(resultCode)){
				//这里省略了查询、更新订单状态及事物回滚等操作
				response.setContentType("text/xml");
				response.getWriter().print("success");
			}
		}
	}
	
}

package com.javaweb.config.wechat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.eo.wechat.AccessTokenResponse;
import com.javaweb.eo.wechat.UserInfoResponse;

import net.sf.json.JSONObject;

/**
  * 更多参考:
  * 微信公众平台:https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1445241432
  * 微信公众平台测试账号:https://mp.weixin.qq.com/debug/cgi-bin/sandboxinfo?action=showinfo&t=sandbox/index
  * 微信公众平台示例demo封装调用:https://github.com/Wechat-Group/WxJava    
  * 扫码登录:https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&lang=zh_CN
  * 微信付款:https://pay.weixin.qq.com/wiki/doc/api/index.html
  * 扫码支付接入指引:https://pay.weixin.qq.com/guide/qrcode_payment.shtml
 */
@SuppressWarnings("unused")
@RestController
public class WenxinController {
	
	@Autowired
	private WechatConfig weixinConfig;
	
	//微信公众平台服务器配置(初始化)
	@GetMapping("/wxgzptServerConfig")
	public String wxgzptServerConfigForGet(HttpServletRequest request) {
		String echostr = null;
		try {
			String signature = request.getParameter("signature");
			String timestamp = request.getParameter("timestamp");
			String nonce = request.getParameter("nonce");
			echostr = request.getParameter("echostr");
			String array[] = new String[]{weixinConfig.getWxopenPublicPlatformToken(),timestamp,nonce};
			Arrays.sort(array);
			StringBuilder stringBuilder = new StringBuilder();
			for(int i=0;i<array.length;i++) {
				stringBuilder.append(array[i]);
			}
			boolean ret = signature.equals(""/*SecretUtil.getSecret(stringBuilder.toString(),"SHA-1")*/);
			if(!ret) {
				echostr = null;
			}
		}catch (Exception e) {
			echostr = null;
		}
		return echostr;
	}
	
	//微信公众平台消息处理
	@PostMapping("/wxgzptServerConfig")
	public String wxgzptServerConfigForPost(@RequestBody String body) {
		String out = null;
		try {
			//{Content=1,CreateTime=1560003360,ToUserName=gh_4b54a6836ccf,FromUserName=o8hKM1IHWwSYOtjjRG15bkOYaJF4,MsgType=text,MsgId=22333880939653582}
			Map<String,String> map = new HashMap<>();//XmlUtil.xmlToMap(body);
			String msgType = map.get("MsgType");
			String content = map.get("Content");
			//String createTime = map.get("CreateTime");
			String fromUserName = map.get("FromUserName");
			String toUserName = map.get("ToUserName");
			//String msgId = map.get("MsgId");
			if("text".equals(msgType)) {//发送文字消息
				Map<String,String> outMap = new HashMap<>();
				outMap.put("ToUserName",fromUserName);
				outMap.put("FromUserName",toUserName);
				outMap.put("CreateTime",String.valueOf(new Date().getTime()));
				outMap.put("MsgType","text");
				if("模板消息".equals(content)) {
					new Thread(()->{
						try {
							String outTemplete = "";//HttpUtil.defaultGetRequest(String.format(weixinConfig.getWxopenAccessToken(),weixinConfig.getWxopenAppid(),weixinConfig.getWxopenAppsecret()),null);
							outTemplete = JSONObject.fromObject(outTemplete).get("access_token").toString();
							String outBody = "{\"touser\":\"oF5Is6N_InAXPwByh9Wh1NjdTkvg\",\"template_id\":\"1Dih-sHIXgcoctF1LkUa6iGgdBsvUWolG-3hkqke9R0\",\"url\":\"http://weixin.qq.com/download\",\"topcolor\":\"#FF0000\",\"data\":{\"date\":{\"value\":\"2019-01-01 11:11:11\",\"color\":\"#FF0000\"},\"orderNo\":{\"value\":\"123456789\",\"color\":\"#FF0000\"},\"money\":{\"value\":\"22.2\",\"color\":\"#FF0000\"}}}";
							outTemplete = "";//HttpUtil.defaultPostRequest(String.format(weixinConfig.getWxopenMessageTemplete(),outTemplete),outBody,null);
							System.out.println(outTemplete);
						}catch (Exception e) {
							System.out.println("发生异常");
						}
					}).start();
				}
				out = "";//HttpUtil.defaultGetRequest("http://api.qingyunke.com/api.php?key=free&appid=0&msg="+URLEncoder.encode(content,"UTF-8"),null);
				outMap.put("Content","调用【青云客（http://api.qingyunke.com）】消息智能回复："+JSONObject.fromObject(out).get("content"));
				out = "";//XmlUtil.mapToXml(outMap);
			}else if("event".equals(msgType)){//关注/取消关注事件
				if(map.get("Event").equals("subscribe")) {//订阅
					String wxForwardUrl = String.format(weixinConfig.getWxopenAuthorizeUrl(),weixinConfig.getWxopenAppid(),URLEncoder.encode(weixinConfig.getWxopenRedirectUrl(),"UTF-8"));
					Map<String,String> outMap = new HashMap<>();
					outMap.put("ToUserName",fromUserName);
					outMap.put("FromUserName",toUserName);
					outMap.put("CreateTime",String.valueOf(new Date().getTime()));
					outMap.put("MsgType","text");
					outMap.put("Content",wxForwardUrl);
					out = "";//XmlUtil.mapToXml(outMap);
					System.out.println("用户关注了我");
				}else if(map.get("Event").equals("unsubscribe")){//取消订阅
					System.out.println("用户取消关注了我");
				}else if(map.get("Event").equals("TEMPLATESENDJOBFINISH")) {
					System.out.println("模板消息发送成功");
				}
			}
		} catch (Exception e) {
			out = e.getMessage();
		}
		return out;
	}
	
	//微信公众平台获取用户信息回调地址
	@GetMapping("/weixin/getUserInfoCallBack")
	public String getUserInfoCallBack() {
		String weixinForwardUrl = null;
		try{
			weixinForwardUrl = String.format(weixinConfig.getWxopenAuthorizeUrl(),weixinConfig.getWxopenAppid(),weixinConfig.getWxopenRedirectUrl());
			weixinForwardUrl = URLEncoder.encode(weixinForwardUrl,"UTF-8");
			return weixinForwardUrl;
		}catch(Exception e){
			return weixinForwardUrl;
		}
	}
	
	//微信公众平台获取用户信息
	@GetMapping("/weixin/getUserInfo")
	public UserInfoResponse getUserInfo(HttpServletRequest request) {
		UserInfoResponse userInfoResponse = null;
		try {
			String code = request.getParameter("code");
			String url = String.format(weixinConfig.getWxopenAccessTokenUrl(),weixinConfig.getWxopenAppid(),weixinConfig.getWxopenAppsecret(),code);
			AccessTokenResponse accessTokenResponse = new AccessTokenResponse();//new ObjectMapper().readValue(HttpUtil.defaultGetRequest(url,null),AccessTokenResponse.class);
			url = String.format(weixinConfig.getWxopenUserInfo(),accessTokenResponse.getAccess_token(),accessTokenResponse.getOpenid());
			userInfoResponse = new ObjectMapper().readValue(url,UserInfoResponse.class);//new ObjectMapper().readValue(HttpUtil.defaultGetRequest(url,null),UserInfoResponse.class);
			return userInfoResponse;
		}catch (Exception e) {
			return userInfoResponse;
		}
	}
	
	//微信扫码登录URL获取(currentPageUrl用于扫码登录后跳转到自己网站的主页)
	@GetMapping("/weixin/getLoginUrl")
	public String getLoginUrl(@RequestParam(name="currentPageUrl",required=true) String currentPageUrl) {
		String openQrcodeUrl = null;
		try{
			openQrcodeUrl = String.format(weixinConfig.getWxopenQrcodeUrl(),weixinConfig.getWxopenAppid(),URLEncoder.encode(weixinConfig.getWxopenRedirectUrl(),"UTF-8"),currentPageUrl);
			return openQrcodeUrl;
		}catch(Exception e){
			return openQrcodeUrl;
		}
	}
	
	//生成微信扫码付款二维码
	@GetMapping("/weixin/getPayQrCode")
	public void getPayQrCode() throws Exception {
		//仅展示几个重要的必填字段,更多参考:https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1
		SortedMap<String,String> sortedMap = new TreeMap<>();
		sortedMap.put("appid",weixinConfig.getWxopenAppid());
		sortedMap.put("mch_id",weixinConfig.getWxpayMerId());
		sortedMap.put("notify_url",weixinConfig.getWxpayCallbackUrl());
		String sign = createSign(sortedMap,weixinConfig.getWxpayKey());
		sortedMap.put("sign",sign);
		String xmlStr = "";//XmlUtil.mapToXml(sortedMap);
		xmlStr = "";//HttpUtil.xmlPostRequest(weixinConfig.getWxpayUnifiedorderUrl(),xmlStr);
		Map<String,String> map = new HashMap<>();//XmlUtil.xmlToMap(xmlStr);
		String codeUrl = map.get("code_url");
		System.out.println(codeUrl);
		//最后可以使用谷歌的二维码生成工具来生成二维码
	}
	
	//微信扫码付款成功后的回调处理
	@PostMapping("/weixin/payCallBack")
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
		Map<String,String> map = new HashMap<>();//XmlUtil.xmlToMap(sb.toString());
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
	
	/* -------------------------------------------------- 分界线 -------------------------------------------------- */
	
	private String createSign(SortedMap<String,String> sortedMap,String key) throws Exception {
		StringBuilder sb = new StringBuilder();
		Set<Map.Entry<String,String>> entrySet = sortedMap.entrySet();
		Iterator<Entry<String,String>> iterator = entrySet.iterator();
		while(iterator.hasNext()){
			Entry<String,String> entry = iterator.next();
			String k = entry.getKey();
			String v = entry.getValue();
			if(v!=null){//实际项目这里校验更多更严格
				sb.append(k+"="+v+"&");
			}
		}
		sb.append("key="+key);
		return "MD5";//SecretUtil.getSecret(sb.toString(),"MD5").toUpperCase();
	}
	
	private boolean checkSign(SortedMap<String,String> sortedMap,String key) throws Exception {
		String sign = createSign(sortedMap,key);
		String weixinPaySign = sortedMap.get("sign").toUpperCase();
		return sign.equals(weixinPaySign);
	}
	
}

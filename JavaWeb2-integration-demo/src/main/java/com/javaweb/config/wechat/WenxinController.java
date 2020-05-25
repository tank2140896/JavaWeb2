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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
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
	
	/** 企业微信扫码登录 start */
	/** 原版正常对接步骤 */
	//step1.获取企业微信登录二维码
	@GetMapping("/getLoginQrCode")
	public void getLoginQrCode(HttpServletRequest request,HttpServletResponse response){
		try{
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix bitMatrix = qrCodeWriter.encode("https://127.0.0.1:8001/service_manage_admin/workLogin",BarcodeFormat.QR_CODE,180,180);
			MatrixToImageWriter.writeToStream(bitMatrix,"jpg",response.getOutputStream());
		}catch(Exception e){
			//do nothing
		}
	}
	
	//扫码登录接入参考：https://work.weixin.qq.com/api/doc/90000/90135/91020
	//step2.企业微信扫码登录
	@RequestMapping("/workLogin")
	public void workLogin(HttpServletResponse response) throws Exception {
		String appid = "appid";//企业微信的CorpID，在企业微信管理端查看
		String agentid = "agentid";//权方的网页应用ID，在具体的网页应用中查看
		String redirect_uri = "https://127.0.0.1:8001/service_manage_admin/workLoginCallBack";//重定向地址，需要进行UrlEncode
		String state = "state";//用于保持请求和回调的状态，授权请求后原样带回给企业。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议企业带上该参数，可设置为简单的随机数加session进行校验
		String url = "https://open.work.weixin.qq.com/wwopen/sso/qrConnect?appid="+appid+"&agentid="+agentid+"&redirect_uri="+URLEncoder.encode(redirect_uri,"UTF-8")+"&state="+state;
		//response.sendRedirect(url);
		System.out.println(url);
		//http调用url即可
	}
	
	//step3.企业微信扫码登录回调
	@RequestMapping("/workLoginCallBack")
	public void workLoginCallBack(HttpServletRequest request,HttpServletResponse response,@RequestParam("code") String code,@RequestParam("state") String state) throws Exception{
		//用户允许授权后，将会重定向到redirect_uri的网址上，并且带上code和state参数redirect_uri?code=CODE&state=STATE若用户禁止授权，则重定向后不会带上code参数，仅会带上state参数redirect_uri?state=STATE
		//获取accessToken，参考：https://open.work.weixin.qq.com/api/doc/90000/90135/91039
		String accessToken = "accessToken";
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token="+accessToken+"&code="+code;
		//http调用，成功返回数据格式：{"errcode":0,"errmsg":"ok","UserId":"USERID"}
		System.out.println(url);
		//根据用户ID获取用户信息，参考：https://open.work.weixin.qq.com/api/doc/90000/90135/90196
		//用户名和密码要Base64加密
		response.sendRedirect("https://127.0.0.1:8001/前端页面/login?username=abc123&password=123456");//跳至前端页面模拟登录请求
	}
	
	/** 现版沟通对接步骤
	1.甲方提供扫码URL，如https://www.xxx.com/甲方/x?a=1&b=2  
	2.乙方（我）在后面拼接一段唯一字符串，形如：https://www.xxx.com/甲方/x?a=1&b=2&uniqueCode=xxxxxxxx  
	也就是说我的二维码https://www.xxx.com/甲方/x?a=1&b=2&uniqueCode=xxxxxxxx并由用户扫码
	3.乙方（我）向甲方提供乙方（我）的回调地址，如https://www.yyy.com/workLoginCallBack  
	4.甲方调用乙方（我）的回调地址，传入是否企业用户扫码成功标识（successFlag）、uniqueCode和userCode（工号）
	*/
	/**
	@GetMapping("/getLoginQrCode")
	public void getLoginQrCode(HttpServletRequest request,HttpServletResponse response){
		try{
			String uniqueCode = SecretUtil.getWorkWeixinUniqueCode(HttpUtil.getIpAddress(request));
			logger.info("送出去的uniqueCode是:["+uniqueCode+"]");
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			String replace = "https://qywx.cpic.com.cn/suz/qrauth?src=sm&qrid="+uniqueCode;
			//replace = URLEncoder.encode(replace,"UTF-8");
			replace = SecretUtil.base64EncoderString(replace,"UTF-8");
			String url = SystemConstant.getConfigByKeyCode("weixin.scan.url").getValueCode().replace("[replace]",replace);
			//System.out.println(url);
			valueOperations.set("weixin_sm="+HttpUtil.getIpAddress(request),"a",Duration.ofMinutes(SystemConstant.WEIXIN_ERWEIMA_CODE));//扫码5分钟有效
			logger.info("获取login qrcode成功,key is:["+"weixin_sm="+HttpUtil.getIpAddress(request)+"],value is:[a]");
			BitMatrix bitMatrix = qrCodeWriter.encode(url,BarcodeFormat.QR_CODE,180,180);
			MatrixToImageWriter.writeToStream(bitMatrix,"jpg",response.getOutputStream());
		}catch(Exception e){
			//do nothing
		}
	}
	
	//甲方要求：扫码后，企业号后台返回微信前端结果，并向当前应用后台回调地址返回结果
    ///workLoginCallBack?successFlag=1&uniqueCode=aaabbbbccc&userCode=49853
    //successFlag=1为成功
    //successFlag=-1为失败
	@RequestMapping("/workLoginCallBack")
	public void workLoginCallBack(HttpServletRequest request,HttpServletResponse response,
								  @RequestBody WorkLoginCallBackRequest workLoginCallBackRequest	
			                      //@RequestParam("successFlag") Integer successFlag,@RequestParam("uniqueCode") String uniqueCode,@RequestParam("userCode") 
			                      String userCode) throws Exception{
		String message = "";
		boolean successFlag = false;
		String username = "";
		String password = "";
		String checkValue = null;
		try{
			//successFlag
			if(workLoginCallBackRequest.getSuccessFlag()==1){//1：成功；2：失败
				//uniqueCode
				checkValue = SecretUtil.workWeixinUniqueCodeCheck(workLoginCallBackRequest.getUniqueCode());
				if(checkValue!=null){//校验uniqueCode
					//userCode
					User user = userService.getUserByUserCode(workLoginCallBackRequest.getUserCode());//根据userCode（工号）获取用户ID然后获取用户密码
					if(user!=null){
						PasswordRecord passwordRecord = passwordRecordService.getPasswordRecordByUserId(user.getId());
						if(passwordRecord!=null){
							username = SecretUtil.base64EncoderString(user.getUserCode(),"UTF-8");
							password = SecretUtil.base64EncoderString(passwordRecord.getPassword(),"UTF-8");
							successFlag = true;
						}else{
							message="回调错误[用户未设定密码]";
						}
					}else{
						message="回调错误[用户工号("+workLoginCallBackRequest.getUserCode()+")不存在]";
					}
				}else{
					message="回调错误[校验未通过，接收到的unicode是("+workLoginCallBackRequest.getUniqueCode()+")]";
				}
			}else{
				message="回调错误[successFlag是("+workLoginCallBackRequest.getSuccessFlag()+")]";
			}
		}catch(Exception e){
			message="回调错误[系统异常:("+e.getMessage()+")]";
		}
		if(successFlag){
			//String url = frontendUrl+"?username="+username+"&password="+password;
			//url = URLEncoder.encode(url,"UTF-8");
			String out = username+","+password;
			valueOperations.set("weixin_sm="+checkValue.split(",")[0],out,Duration.ofMinutes(SystemConstant.WEIXIN_SCAN_CODE));
			message= "回调成功[key是:"+"weixin_sm="+HttpUtil.getIpAddress(request)+",value是:"+out+"]";
			logger.info(message);
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/javascript;charset=utf-8");
			PrintWriter printWriter = response.getWriter();
			String info = JSONObject.fromObject(new BaseResponseResult(200,message,null)).toString();
			printWriter.write(info);
			//response.sendRedirect(url);//返回302状态码给甲方
			response.flushBuffer();
			return;
		}else{
			//String url = frontendUrl+"?errorCode=605";
			//url = URLEncoder.encode(url,"UTF-8");
			valueOperations.set("weixin_sm="+checkValue.split(",")[0],"b",Duration.ofMinutes(SystemConstant.WEIXIN_SCAN_CODE));
			logger.info(message);
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/javascript;charset=utf-8");
			PrintWriter printWriter = response.getWriter();
			String info = JSONObject.fromObject(new BaseResponseResult(500,message,null)).toString();
			printWriter.write(info);
			//response.sendRedirect(url);//返回302状态码给甲方
			response.flushBuffer();
			return;
		}
	}
	
	//前端扫码持续监听
	@GetMapping("/workLoginFrontend")
	public BaseResponseResult workLoginCallBack(HttpServletRequest request) throws Exception {
		Object value = valueOperations.get("weixin_sm="+HttpUtil.getIpAddress(request));
		if(value!=null){
			if(value.toString().equals("a")){
				//logger.info("二维码生成成功，等待扫码");
				return new BaseResponseResult(600,"二维码生成成功，等待扫码",value);
			}else if(value.toString().equals("b")){
				//logger.info("扫码失败，请重新扫码");
				return new BaseResponseResult(605,"扫码失败，请重新扫码",value);
			}else{
				stringRedisTemplate.delete("weixin_sm="+HttpUtil.getIpAddress(request));
				logger.info("扫码成功");
				return new BaseResponseResult(200,"扫码成功",value);
			}
		}else{
			String url = SystemConstant.getConfigByKeyCode("frontend.url").getValueCode()+"?errorCode=605";
			url = URLEncoder.encode(url,"UTF-8");
			//logger.info("扫码失效，请刷新页面重新获取二维码");
			return new BaseResponseResult(605,"扫码失效，请刷新页面重新获取二维码",url);
		}
	}
	*/
	/** 企业微信扫码登录 end */
	
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

package com.javaweb.config.thirdparty;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.util.core.HttpUtil;
import com.javaweb.util.core.RsaUtil;
import com.javaweb.util.core.SecretUtil;

import lombok.Getter;
import lombok.Setter;

/**
以网页登录流程为例：
三个角色：用户、用户访问的应用（A）、本服务（第三方）
1、用户通过本服务（第三方）登录应用（A）
2、应用（A）提供一个本服务（第三方）登录（login）的url，前端拿到appid、agentid、state和redirect_uri
String url = "https://www.thirdparty.com/login?appid="+appid+"&agentid="+agentid+"&redirect_uri="+URLEncoder.encode(redirect_uri,"UTF-8")+"&state="+state;
appid为本服务给予第三方应用的应用ID（可选）
agentid为本服务给予第三方应用的功能ID（可选）
redirect_uri为第三方应用地址
state随便一串随机字符串即可（可选）
url为本服务地址
3、用户输入本服务（第三方）的账号和密码，前端将appid、agentid、state、redirect_uri和账号密码通过post请求本服务（第三方）
4、本服务（第三方）登录成功后回调应用（A），然后直接带一个如加密的code（包含账号密码）返回本服务（第三方）前端然后由本服务（第三方）前端跳转至应用（A）
5、应用（A）拿到code经过校验后模拟本系统登录
*/
@RestController
public class Oauth2Controller {
	
	//实际项目建议第三方登录的login和本系统自己登录的login分开来写，不要通过if/else判断处理
    @GetMapping("/login")
    public String login(HttpServletRequest request,HttpServletResponse response,@RequestBody LoginBody loginBody) throws Exception {
    	System.out.println(loginBody.toString());//假设登录成功了
    	String appid = loginBody.getAppid();//request.getParameter("appid");
    	String agentid = loginBody.getAgentid();//request.getParameter("agentid");

    	String code = SecretUtil.defaultGenRandomPass(10);
    	code = RsaUtil.encrypt(code,null/*传入公钥*/);//公钥的话可以直接向应用（A）获取或者放入appid或agentid或新起个字段
		code = code+","+appid+","+agentid;
		code = SecretUtil.base64EncoderString(code,"UTF-8");
		//code也可以在最后加密，加密前的code也可以转化成JwtToken，参考SecretUtil的createJwtToken及analyseJwtToken方法
		
		String state = loginBody.getState();//request.getParameter("state");
		String redirect_uri = loginBody.getRedirectUri();//request.getParameter("redirect_uri");
    	String out = HttpUtil.defaultGetRequest(redirect_uri+"?code="+code+"&state="+state,null);//回调第三方应用
    	System.out.println(out);
    	return code;
    }

}

@Getter
@Setter
class LoginBody{
	private String appid;
	private String agentid;
	private String redirectUri;
	private String state;
	private String username;
	private String password;
	private Integer type;//（自己看情况处理）1：本系统登录；2：第三方授权登录
}

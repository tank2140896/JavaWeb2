package com.javaweb.config.kaptcha;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.base.BaseInject;

@RestController
public class KaptchaController extends BaseInject {
	
	@GetMapping("/getRequestId")
	public String getRequestId() {
		Map<String,Object> map = new HashMap<>();
		map.put("key",defaultKaptcha.createText());
		map.put("date","yyyyMMddHHmmss");
		return "success";
	}
	
	@GetMapping("getKaptcha")
	public void getKaptcha(HttpServletRequest request,HttpServletResponse response,@PathVariable(name="requestId",required=true) String requestId) throws Exception {
		response.setHeader("Cache-Control","no-store,no-cache");
	    response.setContentType("image/jpeg");
	    String text = defaultKaptcha.createText();
	    kaptchaCheck(request);//这里可以进行验证码的校验
	    BufferedImage image = defaultKaptcha.createImage(text);
	    ServletOutputStream out = response.getOutputStream();
	    ImageIO.write(image,"jpg",out);
	}
	
	//验证码校验
	private boolean kaptchaCheck(/*UserLoginRequest userLogin,*/HttpServletRequest request){
		boolean result = true;
		//可以从redis中获取
		String kaptcha = "get from redis";//(String)getDateFromRedis(userLogin.getRequestId());
		if(kaptcha!=null){
			if(kaptcha.equalsIgnoreCase("获得到的验证码"/*userLogin.getKaptcha()*/)){//忽略大小写
				result = false;
			}
		}
		return result;
	}

}

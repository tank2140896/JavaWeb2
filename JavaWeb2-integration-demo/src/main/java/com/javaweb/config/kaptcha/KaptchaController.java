package com.javaweb.config.kaptcha;

import java.awt.image.BufferedImage;
import java.time.Duration;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.base.BaseInject;
import com.javaweb.util.core.HttpUtil;

@RestController
public class KaptchaController extends BaseInject {
	
	//获取验证码
	@GetMapping("getKaptcha")
	public void getKaptcha(HttpServletRequest request,HttpServletResponse response) {
		try{
		    response.setHeader("Cache-Control","no-store,no-cache");
		    response.setContentType("image/jpeg");
		    String ip = HttpUtil.getIpAddress(request);//request.getSession().getId()
		    String text = defaultKaptcha.createText();
		    stringRedisTemplate1.opsForValue().set("kaptcha_"+ip,text,Duration.ofMinutes(5L));
		    BufferedImage image = defaultKaptcha.createImage(text);
		    ServletOutputStream out = response.getOutputStream();
		    ImageIO.write(image,"jpg",out);
		}catch(Exception e){
			//do nothing
		}
	}
	
	//验证码校验
	public boolean kaptchaCheck(String kaptchaFromFrontend,HttpServletRequest request){
		String ip = HttpUtil.getIpAddress(request);//request.getSession().getId()
		boolean result = false;
		//可以从redis中获取
		String kaptcha = stringRedisTemplate1.opsForValue().get("kaptcha_"+ip);
		if(kaptcha!=null){
			if(kaptcha.equalsIgnoreCase(kaptchaFromFrontend)){//忽略大小写
				result = true;
			}
		}
		return result;
	}

}

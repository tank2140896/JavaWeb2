package com.javaweb.config.kaptcha;

import java.awt.image.BufferedImage;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.base.BaseInject;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.util.core.AesDesUtil;
import com.javaweb.util.core.DateUtil;

@RestController
public class KaptchaController extends BaseInject {
	
	//获取验证唯一值
	@GetMapping("/getVerifyUniqueCode/{type}")
	public BaseResponseResult getVerifyUniqueCode(HttpServletRequest httpServletRequest,@PathVariable(value="type",required=true) int type) throws Exception {
		if(type!=1&&type!=2){
			return new BaseResponseResult(605,"请求类型错误");
		}
		String time = DateUtil.getDefaultDate(DateUtil.DATETIME_PATTERN_TYPE1);
		String code = "";
		if(type==1){//二维码
			code = UUID.randomUUID().toString();
		}else if(type==2){//验证码
			code = defaultKaptcha.createText();
		}
		code = type+","+time+","+code;
		code = AesDesUtil.encryptDes(code,AesDesUtil.getDesKey("efvak81l6jzcwi4d148p236l"));
		code = URLEncoder.encode(code,"UTF-8");
		return new BaseResponseResult(200,"获取验证唯一值成功",code);
	}
	
	//验证唯一值（可做更加完善的校验处理）
	private String verifyUniqueCode(String code){
		String result = null;
		try{
			result = URLDecoder.decode(code,"UTF-8");
			result = AesDesUtil.decryptDes(result,AesDesUtil.getDesKey("efvak81l6jzcwi4d148p236l"));
			//type+","+time+","+code;
			String time = code.split(",")[1];
			Duration duration = DateUtil.getDuration(DateUtil.getDateTime(time,DateUtil.DATETIME_PATTERN_TYPE1),LocalDateTime.now());
			if(duration.getSeconds()>5*60){//超过5分钟
				result = null;
			}
		}catch(Exception e){
			//do nothing
		}
		return result;
	}
	
	//获取验证码
	@GetMapping("getKaptcha")
	public void getKaptcha(HttpServletRequest request,HttpServletResponse response) {
		try{
		    String code = request.getParameter("code");
		    code = verifyUniqueCode(code);
		    BufferedImage image = defaultKaptcha.createImage(code.split(",")[2]);
		    ServletOutputStream out = response.getOutputStream();
		    ImageIO.write(image,"jpg",out);
		}catch(Exception e){
			//do nothing
		}
	}
	
	//验证码校验
	public boolean kaptchaCheck(String kaptchaFromFrontend,HttpServletRequest request){
		boolean result = false;
		try{
		    String code = request.getParameter("code");
		    code = verifyUniqueCode(code);
		    result = kaptchaFromFrontend.equals(code.split(",")[2]);
		}catch(Exception e){
			//do nothing
		}
		return result;
	}
	
	/**
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
	*/

}

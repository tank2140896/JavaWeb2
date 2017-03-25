package com.javaweb.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.javaweb.conf.shiro.ShiroSession;
import com.javaweb.dataobject.po.User;
import com.javaweb.help.GsonHelp;
import com.javaweb.help.ResponseResult;
import com.javaweb.service.rbac.UserService;

@RestController
public class LoginController {
	
	@Autowired
	private DefaultKaptcha defaultKaptcha;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/kaptcha")
	public void captcha(HttpServletResponse response)throws ServletException,IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //生成文字验证码
        String text = defaultKaptcha.createText();
        //生成图片验证码
        BufferedImage image = defaultKaptcha.createImage(text);
        //保存到shiro的session
        ShiroSession.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody User user){
		
		
		
		ResponseResult responseResult = new ResponseResult(401,"没有权限",null);
		GsonHelp gsonHelp = new GsonHelp();
		return gsonHelp.fromJsonDefault(responseResult);
	}
	
	@GetMapping("/unauthorized")
	public String unauthorized(){
		ResponseResult responseResult = new ResponseResult(401,"没有权限",null);
		GsonHelp gsonHelp = new GsonHelp();
		return gsonHelp.fromJsonDefault(responseResult);
	}
	
	@GetMapping("/internalServerError")
	public String internalServerError(){
		ResponseResult responseResult = new ResponseResult(500,"系统内部错误",null);
		GsonHelp gsonHelp = new GsonHelp();
		return gsonHelp.fromJsonDefault(responseResult);
	}
	
}

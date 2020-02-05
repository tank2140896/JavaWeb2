package com.javaweb.config.mail;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.base.BaseInject;

@RestController
public class MailSendController extends BaseInject {
	
	@GetMapping("/send")
	public String send(){
        try {
        	//第一种方式
        	SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    		simpleMailMessage.setFrom("123456789@qq.com");
    		simpleMailMessage.setTo("abcdefghijk@go.com");
    		simpleMailMessage.setSubject("测试邮件发送");
    		simpleMailMessage.setText("这是我发送的内容");
            javaMailSender.send(simpleMailMessage);
            //第二种方式
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
			mimeMessageHelper.setFrom("123456789@qq.com");
			mimeMessageHelper.setTo("abcdefghijk@go.com");
			mimeMessageHelper.setSubject("测试邮件发送");
			mimeMessageHelper.setText("<html><body><img src=\"cid:myTag\" ></body></html>",true);
			FileSystemResource file1 = new FileSystemResource(new File("D:\\1.jpg"));
			FileSystemResource file2 = new FileSystemResource(new File("D:\\2.jpg"));
			FileSystemResource file3 = new FileSystemResource(new File("D:\\3.jpg"));
			mimeMessageHelper.addAttachment("附件-1.jpg",file1);
			mimeMessageHelper.addAttachment("附件-2.jpg",file2);
			mimeMessageHelper.addInline("myTag",file3);
			javaMailSender.send(mimeMessage);
        }catch(Exception e){
        	//do nothing
        }
		return "success";
	}
	
}

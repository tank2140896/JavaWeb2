package com.javaweb.config.kaptcha;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

@Configuration
public class KaptchaConfig {
	
	@Bean  
    public DefaultKaptcha getDefaultKaptcha(){  
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();  
        Properties properties = new Properties();  
        properties.setProperty("kaptcha.border","yes");  
        properties.setProperty("kaptcha.border.color","105,179,90");  
        properties.setProperty("kaptcha.textproducer.font.color","blue");
        properties.setProperty("kaptcha.image.width","300");
        properties.setProperty("kaptcha.image.height","75");
        properties.setProperty("kaptcha.noise.impl","com.cpic.config.kaptcha.SelfNoise");//干扰 com.google.code.kaptcha.impl.NoNoise为无干扰，此处用的selfNoise是自己实现的干扰配置
        //properties.setProperty("kaptcha.obscurificator.impl","com.google.code.kaptcha.impl.ShadowGimpy");
        properties.setProperty("kaptcha.textproducer.font.size","70");
        properties.setProperty("kaptcha.textproducer.char.length","4");  
        properties.setProperty("kaptcha.textproducer.char.space","10");
        properties.setProperty("kaptcha.textproducer.char.string","AB3NP8RC4EF6HKMU2WXY");  
        properties.setProperty("kaptcha.textproducer.font.names","宋体,楷体,微软雅黑");
        Config config = new Config(properties);  
        defaultKaptcha.setConfig(config);  
        return defaultKaptcha;  
    } 

}

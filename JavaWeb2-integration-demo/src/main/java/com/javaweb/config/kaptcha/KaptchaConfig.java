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
        properties.setProperty("kaptcha.textproducer.font.color","red");  
        properties.setProperty("kaptcha.image.width","100");  
        properties.setProperty("kaptcha.image.height","27");  
        properties.setProperty("kaptcha.noise.impl","com.google.code.kaptcha.impl.NoNoise"); 
        properties.setProperty("kaptcha.textproducer.font.size","26");  
        properties.setProperty("kaptcha.textproducer.char.length","4");  
        properties.setProperty("kaptcha.textproducer.char.space","3");  
        properties.setProperty("kaptcha.textproducer.char.string","A3C4EFGH6JK7MN8PR9TWX");  
        properties.setProperty("kaptcha.textproducer.font.names","Arial");  
        Config config = new Config(properties);  
        defaultKaptcha.setConfig(config);  
        return defaultKaptcha;  
    }  

}

package com.javaweb.config.i18n;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Configuration
public class I18NConfig {
	
	@Bean
    public LocaleResolver localeResolver() {
		/**
		 * 主要类型(其它方式略)
		 * 1、按HTTP请求头部解析区域：AcceptHeaderLocaleResolver
		 * 2、按会话属性解析区域：SessionLocaleResolver
		 * 3、按Cookie解析区域：CookieLocaleResolver
		 */
		//这里通过RequestHeaders里的Accept-Language来进行国际化处理
		AcceptHeaderLocaleResolver acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver();
		acceptHeaderLocaleResolver.setDefaultLocale(Locale.CHINA);//默认语言
        return acceptHeaderLocaleResolver;
    }

}

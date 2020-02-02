package com.javaweb.config.restTemplate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
	
	@Value("${restTemplate.readTimeout}")
	private int readTimeout;
	
	@Value("${restTemplate.connectTimeout}")
	private int connectTimeout;
	
	@Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory clientHttpRequestFactory){
		return new RestTemplate(clientHttpRequestFactory);
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setReadTimeout(readTimeout);//单位为毫秒
        simpleClientHttpRequestFactory.setConnectTimeout(connectTimeout);//单位为毫秒
        return simpleClientHttpRequestFactory;
    }

}

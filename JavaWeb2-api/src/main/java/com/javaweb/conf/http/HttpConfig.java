package com.javaweb.conf.http;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration  
public class HttpConfig { 
      
    @Bean  
    public CorsFilter corsFilter() {  
    	CorsConfiguration corsConfiguration = new CorsConfiguration();  
        corsConfiguration.addAllowedOrigin("*");  
        corsConfiguration.addAllowedHeader("*");  
        corsConfiguration.addAllowedMethod("*");  
        corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();  
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);  
    } 
	
    /**
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return new EmbeddedServletContainerCustomizer() {
            public void customize(ConfigurableEmbeddedServletContainer container) {
                container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/notFound"));//404
            }
        };
    }
    */

} 
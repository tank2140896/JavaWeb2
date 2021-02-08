package com.javaweb.config.swagger;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//一些过滤器、拦截器等可能会影响到swagger的界面访问
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
    public Docket createRestApi() {
    	List<Parameter> parameters = new ArrayList<Parameter>();
    	parameters.add(new ParameterBuilder().name("token").description("token").modelRef(new ModelRef("string")).parameterType("header").required(false).build());
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .globalOperationParameters(parameters)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.javaweb"))//包形式
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))//注解形式
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
        	.title("SpringBoot使用Swagger构建API文档")
            .description("JavaWeb2")
            .termsOfServiceUrl("https://github.com")
            .version("1.0")
            .build();
    }

}

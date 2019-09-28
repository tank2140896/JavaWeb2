package com.javaweb.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.javaweb.constant.SwaggerConstant;
import com.javaweb.constant.SystemConstant;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	@Value("${swagger.open}")
	private boolean swaggerOpen;
	
	@Bean
    public Docket createRestApi() {
    	List<Parameter> parameters = new ArrayList<Parameter>();
    	parameters.add(new ParameterBuilder().name(SystemConstant.HEAD_USERID).description(SwaggerConstant.SWAGGER_HEAD_USERID).modelRef(new ModelRef("string")).parameterType("header").required(false).build());
    	parameters.add(new ParameterBuilder().name(SystemConstant.HEAD_TOKEN).description(SwaggerConstant.SWAGGER_HEAD_TOKEN).modelRef(new ModelRef("string")).parameterType("header").required(false).build());
    	parameters.add(new ParameterBuilder().name(SystemConstant.HEAD_TYPE).description(SwaggerConstant.SWAGGER_HEAD_TYPE).modelRef(new ModelRef("string")).parameterType("header").required(false).build());
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerOpen)
                .globalOperationParameters(parameters)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(SystemConstant.BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
        	.title(SwaggerConstant.SWAGGER_TITLE)
            .description(SystemConstant.PROJECT_NAME)
            .termsOfServiceUrl(SystemConstant.PROJECT_GITHUB_URL)
            .version(SystemConstant.PROJECT_VERSION)
            .build();
    }

}

package com.hgyl.nonsan_message.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.hgyl.nonsan_message"))
            .paths(PathSelectors.any())
            .build();
    }

    private ApiInfo apiInfo() {//test 01
        return new ApiInfoBuilder()
            .title("nonsan_message Test with Swagger")
            .description("설명 부분")
            .version("1.0.0")
            .build();
    }
}

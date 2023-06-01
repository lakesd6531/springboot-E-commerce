package com.mark.springbootmall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
            .apiInfo(apiInfo())
            // 是否開啟swagger
            .enable(true)
            .select()
            // 過濾條件，掃描指定路徑下的選項
            .apis(RequestHandlerSelectors.basePackage("com.mark.springbootmall.controller"))
            // 指定路徑處理，PathSelectors.any()代表不過濾任何路徑
            .paths(PathSelectors.any())
            .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Spring Boot 簡易電商網站")
            .description("電商網站 api 文件")
            // 開發者資訊
            .contact(new Contact("Mark", "xxx.com.tw" , "xxx@zzz.com.tw"))
            .version("1.0")
            .build();
    }
}

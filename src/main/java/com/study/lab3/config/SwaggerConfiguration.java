package com.study.lab3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import({ BeanValidatorPluginsConfiguration.class, SpringDataRestConfiguration.class})
public class SwaggerConfiguration {
    @Bean
    public Docket apiDocket() {
        Docket docklet = new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis( RequestHandlerSelectors.basePackage("com.study.lab3"))
                .paths(PathSelectors.any())
                .build();

        return docklet;
    }
}

package com.example.restapi.implement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/**
 * @brief:
 *
 * @description Swagger Config
 * @See <a href="http://localhost:5000/swagger-ui/#/">...</a>
 *
 * @Auther RYA234
 */
@Configuration
public class SwaggerConfig {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private ApiKey apiKey(){
        return new ApiKey("JWT",AUTHORIZATION_HEADER, "header");
    }
    private ApiInfo apiInfo(){
        return new ApiInfo(
                "Spring Boot Blog REST APIS",
                "Spring Boot Blog REST API Documentation",
                "1",
                "Term of service",
                new Contact("Ramesh Fadatre", "www.javaguides.net","test.gmail.com"),
                "Lisence of API",
                "API licence URL",
                Collections.emptyList()
        );
    }
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securityContexts(Arrays.asList(securityContext())) // about JWT
                .securitySchemes(Arrays.asList(apiKey()))           // about JWT
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    // about JWT
    private SecurityContext securityContext(){
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    // about JWT
    private List<SecurityReference> defaultAuth(){
        AuthorizationScope authorizationScope = new AuthorizationScope("global","accessEverything");
        AuthorizationScope[]  authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT",authorizationScopes));
    }
}

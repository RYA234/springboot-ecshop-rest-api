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
 * &#064;brief:
 *  &#064;description  Swagger Config
 * &#064;See  <a href="http://localhost:5000/swagger-ui/#/">...</a>
 *
 * &#064;Auther  RYA234
 */
@Configuration
public class SwaggerConfig {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private ApiKey apiKey(){
        return new ApiKey("JWT",AUTHORIZATION_HEADER, "header");
    }
    private ApiInfo apiInfo(){
        return new ApiInfo(
                "ECショップAPIリファレンスページ",
                "本ページはECショップのバックエンドAPIのリファレンスページです。\n\n" +
                        "機能\n"+
                        "ユーザー登録、ログイン機能機能(要認可)\n"+
                        "カテゴリー表示機能(認可不要)\n"+
                        "買い物カゴ機能(要認可)\n"+
                        "クレジットカードでの決済機能(要認可)\n"+
                        "商品一覧機能(認可不要)\n"+
                        "認可認証方式:Authorizationヘッダを使った認証　Bearer認証\n"+
                        "アクセストークンのフォーマットはJWT(Json Web Token)です。\n\n"+

                        "認可を試す方法\n"+
                        "1.ユーザーの認可認証機能のログインAPIを実行します。\n"+
                        "2.実行するとResponse bodyにアクセストークンの値が入っておりそれをコピーします。\n"+
                        "3.右側にあるAuthorizeと記されている緑色のアイコンをクリックして、アクセストークンの値をペーストします。\n"+
                        "4.買い物カゴ機能APIの買い物カゴ表示APIを実行します。実行すると現在の買い物カゴ情報が表示されます。\n"
                ,
                "1",
                "",
                new Contact("RYA234-github", "https://github.com/RYA234/springboot-ecshop-rest-api",""),
                "",
                "",
                Collections.emptyList()
        );
    }
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securityContexts(List.of(securityContext())) // about JWT
                .securitySchemes(List.of(apiKey()))           // about JWT
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
        return List.of(new SecurityReference("JWT", authorizationScopes));
    }
}

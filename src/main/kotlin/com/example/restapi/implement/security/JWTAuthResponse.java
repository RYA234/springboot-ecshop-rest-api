package com.example.restapi.implement.security;

import com.example.restapi.implement.customer.LoginDto;

/**
 * @brief: Response class about JWT
 * @description  When customer login,it is used  as Response.<br>
 *                 {@link com.example.restapi.implement.customer.CustomerRestController#authenticateUser(LoginDto)}
 *
 * @Auther RYA234
 * @See <a href="https://spring.pleiades.io/spring-security/reference/servlet/authentication/passwords/dao-authentication-provider.html">...</a>
 *
 */
public class JWTAuthResponse {
    private String accessToken;
    private String tokenType ="Bearer";

    public JWTAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}

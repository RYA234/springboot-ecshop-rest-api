package com.example.restapi.implement.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @brief: Filter class in SpringSecurity.
 *
 * @description  Override method is only doFilterInternal.
 *               doFilterInternal apply for JWT authentication.
 * @Auther RYA234
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // inject dependencies
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomerUserDetailsService();
    }

    // アクセスできるユーザーの制御をしている
    // todo カート機能、注文機能を実装してからjavadocを完成させる。

/**
 * @brief: Filter class in SpringSecurity.
 *
 * @description  When HttpRequest is received by client,this method does.
 *               doFilterInternal`s role is  the customer only access.
 *               for example,in UseCase, customer check their own Cart or MyPage or OrderHistory
 *              may use ＠@PreAuthorize
 *
 *             // todo まだ動きを確認できていないので、カート機能、注文機能ができ次第確認すること
 *
 * @Auther RYA234
 */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // get JWT form http request
        String token =  getJWTformRequest(request);
        // validate token
        if(StringUtils.hasText(token) && tokenProvider.validateToken(token)){
            // get username from token
            String username = tokenProvider.getCustomernameFromJWT(token);
            // load user associated with token
            CustomerUserDetails customerUserDetails = (CustomerUserDetails) userDetailsService().loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    customerUserDetails,null,customerUserDetails.getAuthorities()
            );
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // set spring security
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request,response);

    }

    // Bearer <accessToken>
    private String getJWTformRequest(HttpServletRequest request){
            String bearerToken = request.getHeader("Authorization");
            if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
                return bearerToken.substring(7);
            }
            return null;
    }
}

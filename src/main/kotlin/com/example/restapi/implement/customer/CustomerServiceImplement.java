package com.example.restapi.implement.customer;

import com.example.restapi.domain.customer.CustomerRepository;
import com.example.restapi.domain.customer.CustomerService;
import com.example.restapi.implement.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Service
public class CustomerServiceImplement implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Override
    public LoginDto findByEmail() {
        return null;
    }

    //todo postmanでidが取得できるところまで確認できた。idを使ってcartの中身を確認できるようにする

    public Integer getIdfromJwtToken(HttpServletRequest request){
        // get Jwt from Response
        String jwtToken = getJWTformRequest(request);
        // get email from jwt
        String email = jwtTokenProvider.getCustomernameFromJWT(jwtToken);

        return customerRepository.findByEmail(email).getId();
    }


    private String getJWTformRequest(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }





}

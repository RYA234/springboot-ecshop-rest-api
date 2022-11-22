package com.example.restapi.domain.customer;

import com.example.restapi.implement.customer.LoginDto;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


// todo extract register and login method from controller
@Service
public interface CustomerService {
        public LoginDto findByEmail();

        // registerUser

        //

        // loginUser

        // JwtトークンからCustomerのidを取得する関数
        // ユーケース上ではCartitemとOrderで使われる
        public Integer getIdfromJwtToken(HttpServletRequest request);

}

package com.example.restapi.implement.customer;

import com.example.restapi.domain.customer.Customer;
import com.example.restapi.domain.customer.CustomerRepository;
import com.example.restapi.domain.customer.CustomerService;
import com.example.restapi.implement.security.JwtTokenProvider;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Service
@Transactional
public class CustomerServiceImplement implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public LoginDto findByEmail() {
        return null;
    }

    @Override
    public Customer registerCustomer(String email, String password) {
        String randomCode = RandomString.make(64);
        Customer newCustomer = new Customer(email, passwordEncoder.encode(password), randomCode, false);
        customerRepository.save(newCustomer);
        return newCustomer;
    }

    @Override
    public boolean verify(String verificationCode) {
        Customer customer = customerRepository.findByVerificationCode(verificationCode);
        if (customer == null || customer.isEnabled()) {
            return false;
        } else {
            customerRepository.enabled(customer.getId());
            return true;
        }

    }


    public Integer getIdfromJwtToken(HttpServletRequest request) {
        // get Jwt from Response
        String jwtToken = getJWTformRequest(request);
        // get email from jwt
        String email = jwtTokenProvider.getCustomernameFromJWT(jwtToken);

        return customerRepository.findByEmail(email).getId();
    }

    private String getJWTformRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }


}

package com.example.restapi.security.jwt;

import com.example.restapi.domain.customer.CustomerRepository;
import com.example.restapi.domain.product.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerUserDetailsServiceTest {
    @Autowired
   private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;
    @Test
    @DisplayName("givenにおいて、whenしたとき、thenが一致する。")
    public void given_when_then() {
        // given-precondition or Setup
        customerRepository.findAll();
        //when - action or the behavior that we are going test

        //then - verify the output
    }




}

package com.example.restapi.implement.customer;

import lombok.Data;

@Data
public class SignupRequestBody {
    private String email;
    private String password;
}

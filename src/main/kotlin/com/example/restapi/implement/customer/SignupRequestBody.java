package com.example.restapi.implement.customer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SignupRequestBody {
    @ApiModelProperty(value = "登録するEメールアドレス", example = "")
    private String email;
    @ApiModelProperty(value = "登録するパスワード", example = "")
    private String password;
}

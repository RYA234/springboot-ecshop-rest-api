package com.example.restapi.implement.customer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginDto {
    @ApiModelProperty(value = "ユーザーのEメールアドレス exmapleのアドレスでログインできます。", example = "aaa@gmail.com")
    private String email;
    @ApiModelProperty(value = "ユーザーのパスワード exmapleのパスワードでログインできます。", example = "test")
    private String password;

}

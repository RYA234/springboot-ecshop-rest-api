package com.example.restapi.implement.customer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VerifyRequestBody {
    @ApiModelProperty(hidden = true)
    String verifyCode;
}

package com.example.restapi.implement.order;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;

@Data
@Hidden
public class PaymentInfoRequest {

    // 金額の量 50以上の値を入力する
    // 単位はcurrency になる
    //例えば　amount=50 currency="jpy"とすると　50円になる
    @ApiModelProperty(value = "支払う金額", example = "2000")
    private int amount;
    //通貨の種類　日本円なので"jpy"と想定する
    @ApiModelProperty(value = "通貨単位　日本円だとjpy", example = "jpy")
    private String currency;

    @ApiModelProperty(value = "送信先のメール　現時点だと機能してない", example = "aaaaaaa")
    private String receiptEmail;

    public PaymentInfoRequest(int amount, String currency, String receiptEmail) {
        this.amount = amount;
        this.currency = currency;
        this.receiptEmail = receiptEmail;
    }
}
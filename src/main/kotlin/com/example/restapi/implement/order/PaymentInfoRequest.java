package com.example.restapi.implement.order;

import lombok.Data;

@Data
public class PaymentInfoRequest {

    // 金額の量 50以上の値を入力する
    // 単位はcurrency になる
    //例えば　amount=50 currency="jpy"とすると　50円になる
    private int amount;
    //通貨の種類　日本円なので"jpy"と想定する
    private String currency;

    private String receiptEmail;

    public PaymentInfoRequest(int amount, String currency, String receiptEmail) {
        this.amount = amount;
        this.currency = currency;
        this.receiptEmail = receiptEmail;
    }
}
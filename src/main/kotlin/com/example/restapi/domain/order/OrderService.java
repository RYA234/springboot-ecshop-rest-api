package com.example.restapi.domain.order;

import com.example.restapi.implement.cartItem.CartItemDto;
import com.example.restapi.implement.order.PaymentInfoRequest;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    // クレジットカード決済をするために、Stripe側の処理を行う
    // mySQLに書き込む処理はなし
    PaymentIntent createPaymentIntent(PaymentInfoRequest paymentInfoRequest) throws StripeException;

    // 決済を行う
    // OrderとOrderItemのテーブルにデータを追加する。CartItemの情報を初期化する Stripe上の処理はなし
    void finishOrder(int customerId, int amount, int type, List<CartItemDto> cartItemDtos);

}

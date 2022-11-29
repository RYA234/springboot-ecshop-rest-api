package com.example.restapi.domain.order;

import com.example.restapi.implement.cartItem.CartItemDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    // 顧客が注文を作成するサービス。ショッピングカート内に入っている商品から注文する内容を決める。
     void create(Integer customerId, List<CartItemDto> cartItemDtos, PaymentMethod paymentMethod);

    // 顧客が今までの注文全てを表示する。
     OrderResponse listByPageByCustomer(Integer customerId, int pageNo,int pageSize);

    // 顧客の注文を一見表示する。
    Order get(Integer orderId);

}

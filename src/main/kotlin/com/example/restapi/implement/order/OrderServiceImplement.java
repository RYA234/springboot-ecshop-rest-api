package com.example.restapi.implement.order;

import com.example.restapi.domain.cartItem.CartItemRepository;
import com.example.restapi.domain.order.Order;
import com.example.restapi.domain.order.OrderRepository;
import com.example.restapi.domain.order.OrderService;
import com.example.restapi.domain.orderItem.OrderItem;
import com.example.restapi.domain.orderItem.OrderItemRepository;
import com.example.restapi.implement.cartItem.CartItemDto;
import com.example.restapi.implement.payment.PaymentInfoRequest;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class OrderServiceImplement implements OrderService {

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public PaymentIntent createPaymentIntent(PaymentInfoRequest paymentInfoRequest) throws StripeException {
        List<String> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");
        List<String> paymentMethod = new ArrayList<>();
        Map<String, Object> automaticPaymentMethods =
                new HashMap<>();
        automaticPaymentMethods.put("enabled", true);
        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentInfoRequest.getAmount());
        params.put("currency", paymentInfoRequest.getCurrency());
        params.put("payment_method_types", paymentMethodTypes);

        return PaymentIntent.create(params);
    }

    @Override
    public void finishOrder(int customerId,int amount,int type,List<CartItemDto> cartItemDtos) {
        // Orderを作成する
        Order order = new Order(customerId,amount, new Date(),0,true,false);
        orderRepository.save(order);

        // cartItemsとorderの情報を元に顧客の。
        List<OrderItem> orderItems = new ArrayList<>();
        for(CartItemDto cartItemDto :cartItemDtos) {
           OrderItem aartItem  = new OrderItem();
           aartItem = orderItemRepository.save(new OrderItem(order.getId(),cartItemDto.getProductId(),cartItemDto.getQuantity()));
        }
        // 顧客のcartItemを削除する
        cartItemRepository.deleteByCustomerId(customerId);

    }
}

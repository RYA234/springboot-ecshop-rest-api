package com.example.restapi.domain.order;

import com.example.restapi.implement.cartItem.CartItemDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderDetailService {

    void Create(Integer CustomerId, List<CartItemDto> cartItemDtos);

    //List<OrderDetailDto> getOrderDetails(Integer orderId);
}

package com.example.restapi.order;


import com.example.restapi.domain.order.OrderRepository;
import com.example.restapi.domain.orderItem.OrderItemRepository;
import com.example.restapi.implement.cartItem.CartItemDto;
import com.example.restapi.implement.order.OrderServiceImplement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class OrderServiceTest {
    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private OrderItemRepository orderItemRepository;

    @InjectMocks
    private OrderServiceImplement orderServiceImplement;


    @Test
    @DisplayName("givenを引数とし、whenを実行したとき、thenがとなる。")
    public void given_when_then() {
        // given-precondition or Setup
        int customerId = 1;
        int amount = 4000;
        int type=0;

        List<CartItemDto> cartItemDtoList = new ArrayList<>();
        cartItemDtoList.add(new CartItemDto(1,25,23,2,"石鹸",200,216));
        cartItemDtoList.add(new CartItemDto(2,25,23,2,"石鹸2",200,216));


        //when - action or the behavior that we are going test



        //then - verify the output
    }

}


package com.example.restapi.order;

import com.example.restapi.domain.order.*;
import com.example.restapi.domain.product.Product;
import com.example.restapi.domain.product.ProductRepository;
import com.example.restapi.implement.cartItem.CartItemDto;
import com.example.restapi.implement.order.OrderDto;
import com.example.restapi.implement.order.OrderServiceImplement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 *
 * @brief:  Order Service Unit Test class
 *
 * @description  this class is Unit test for  {@link OrderServiceImplement}.
 *               Format is base on BDD style(given-when-then).
 *
 * @Auther RYA234
 *
 */
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class OrderServiceImplementTest {

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private OrderDetailRepository orderDetailRepository;

    @MockBean
    private ProductRepository productRepository;
    @InjectMocks
    private OrderServiceImplement orderServiceImplement;


    @Test
    @DisplayName("customerIdとPageableIdを引数とし、listByPageByCustomerを実行したとき、OrderResponseが返り値となる。")
    public void givenCustomerIdandPageable_whenListByPageByCustomer_thenReturnOrderResponse() {
        // given-precondition or Setup
        Integer customerId = 0;
        int pageNo =0;
        int pageSize = 2;
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        int total = 3;
        List<Order> content = new ArrayList<>();
        content.add(new Order(2,0, toDate(LocalDateTime.now()),6000f,100f,6100f,610f,6710f,"pending"));
        content.add(new Order(3,0, toDate(LocalDateTime.now()),6000f,100f,6100f,610f,6710f,"pending"));
        Mockito.doReturn(mockPageOrder(pageable,total,content)).when(orderRepository).findByCustomerId(customerId,pageable);
        //when - action or the behavior that we are going test
        OrderResponse actualOrderResponse = orderServiceImplement.listByPageByCustomer(customerId,pageNo,pageSize);
        //then - verify the output
        OrderResponse expectedOrderResopnse = new OrderResponse(0,2,3,2,false);
        assertThat(actualOrderResponse.getPageNo()).isEqualTo(expectedOrderResopnse.getPageNo());
        assertThat(actualOrderResponse.getTotalPages()).isEqualTo(expectedOrderResopnse.getTotalPages());
        assertThat(actualOrderResponse.getTotalElements()).isEqualTo(expectedOrderResopnse.getTotalElements());
        assertThat(actualOrderResponse.getPageSize()).isEqualTo(expectedOrderResopnse.getPageSize());
    }
    private Page<Order> mockPageOrder(Pageable pageable, int total, List<Order> content){
        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), content.size());
        return new PageImpl<>(content.subList(start,end),pageable,total);
    }

    @Test
    @DisplayName("orderIdを引数とし、Getを実行したとき、OrderDtoが返される。")
    public void givenOrderId_whenGet_thenReturnOrderDto() {
        // given-precondition or Setup


        Order order = new Order(3,0, toDate(LocalDateTime.now()),6000f,100f,6100f,610f,6710f,"pending");
        Integer orderId = 1;
        Mockito.doReturn(order).when(orderRepository).findOrderById(orderId);
        // when - action or the behavior that we are going test
        OrderDto actualOrderDto = orderServiceImplement.get(orderId);
        // then - verify the output
        OrderDto expectedDto = new OrderDto(3,0, order.getOrderTime(),6000f,100f,6100f,610f,6710f,"pending");
        assertThat(actualOrderDto).isEqualTo(actualOrderDto);
    }

    @Test
    @DisplayName("ショッピングカートとCustomerを引数とし、Createを実行したとき、となる。")
    public void givenCartItemDtoAndCustomerId_whenCreate_newOrder() {
        // given-precondition or Setup

        //  GET METHODで使われる想定
        List<CartItemDto> cartItemDtos = new ArrayList<>();
        cartItemDtos.add(new CartItemDto(1,3,3,5));
        cartItemDtos.add(new CartItemDto(1,3,7,1));
        cartItemDtos.add(new CartItemDto(1,3,10,9));
        cartItemDtos.add(new CartItemDto(1,3,13,9));
        cartItemDtos.add(new CartItemDto(1,3,15,3));

        // mock
        Mockito.doReturn(new Product(3,"Salmon","This is a Salmon",true, 1,200,0.01f,0,"maguro_image")).when(productRepository).getProductById(3);
        Mockito.doReturn(new Product(7,"Chicken","This is a chicken",true, 2,800,0.01f,0,"Chicken_image")).when(productRepository).getProductById(7);
        Mockito.doReturn(new Product(10,"Carrot","This is a Carrot",true, 3,100,0.01f,0,"Carrot_image")).when(productRepository).getProductById(10);
        Mockito.doReturn(new Product(13,"Bean","This is a Bean",true, 3,100,0.01f,0,"Bean_image")).when(productRepository).getProductById(13);
        Mockito.doReturn(new Product(15,"Nuts","This is a Nuts",true, 3,100,0.01f,0,"Nuts_image")).when(productRepository).getProductById(15);

        float expectedProductCost = 3900f;
        float expectedShippingCost = 300f;
        float expectedSubtotal = 4100f;
        float expectedTax = 390f;
        float expectedTotal = 4490f;
        PaymentMethod paymentMethod = PaymentMethod.CASH;


        //Mockito.doReturn().when(orderRepository).save()

        //when - action or the behavior that we are going test
        OrderDto orderDto = orderServiceImplement.create(3,cartItemDtos,paymentMethod);

        //then - verify the output
        assertThat(orderDto.getProductCost()).isEqualTo(expectedProductCost);
        assertThat(orderDto.getShippingCost()).isEqualTo(expectedShippingCost);
        assertThat(orderDto.getSubtotal()).isEqualTo(expectedSubtotal);
        assertThat(orderDto.getTax()).isEqualTo(expectedTax);
        assertThat(orderDto.getTotal()).isEqualTo(expectedTotal);

    }

    private Date toDate(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zone);
        Instant instant = zonedDateTime.toInstant();
        return Date.from(instant);
    }
}

package com.example.restapi.order;

import com.example.restapi.domain.order.Order;
import com.example.restapi.domain.order.OrderRepository;
import com.example.restapi.domain.order.OrderResponse;
import com.example.restapi.implement.order.OrderServiceImplement;
import org.junit.jupiter.api.BeforeEach;
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
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class OrderServiceImplementTest {

    @MockBean
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImplement orderServiceImplement;


    @BeforeEach
    public void setUp(){

    }

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
    private Date toDate(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zone);
        Instant instant = zonedDateTime.toInstant();
        return Date.from(instant);
    }
}

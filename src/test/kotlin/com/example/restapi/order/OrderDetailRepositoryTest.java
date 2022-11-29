package com.example.restapi.order;

import com.example.restapi.domain.order.OrderDetail;
import com.example.restapi.domain.order.OrderDetailRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @BeforeEach
    public void setUp(){
        orderDetailRepository.save(new OrderDetail(1,2,3,400,1200));
        orderDetailRepository.save(new OrderDetail(1,4,1,100,100));
        orderDetailRepository.save(new OrderDetail(1,6,5,700,3500));
        orderDetailRepository.save(new OrderDetail(1,2,4,400,1600));
        orderDetailRepository.save(new OrderDetail(2,2,3,400,1200));
        orderDetailRepository.save(new OrderDetail(2,4,1,100,100));
        orderDetailRepository.save(new OrderDetail(2,6,5,700,3500));
        orderDetailRepository.save(new OrderDetail(2,2,4,400,1600));
    }
    @Test
    @DisplayName("OrderDetailを引数とし、Saveを実行したとき、Saveされたが返ってくる。")
    public void givenOrderDetail_whenSave_thenReturnSavedOrderDetail() {
        // given-precondition or Setup
        OrderDetail newOrderDetail = new OrderDetail(1,10,4,400,1600);
        //when - action or the behavior that we are going test
        orderDetailRepository.save(newOrderDetail);
        //then - verify the output
        long expectedRepositoryCount = 9;
        assertThat(orderDetailRepository.count()).isEqualTo(expectedRepositoryCount);
    }
    @Test
    @DisplayName("orderIdを引数とし、findByOrderIdを実行したとき、OrderDetailsが返り値となる。")
    public void givenOrderId_whenFindByOrderId_thenReturnOrderDetails() {
        // given-precondition or Setup

        //when - action or the behavior that we are going test
        Integer orderId = 1;
         List<OrderDetail> actualOrderDetails= orderDetailRepository.findByOrderId(1);
        //then - verify the output
        int expectedSize = 4;
        int expectedFirstOrderId = 1;
        assertThat(actualOrderDetails.size()).isEqualTo(expectedSize);
        assertThat(actualOrderDetails.get(0).getOrderId()).isEqualTo(expectedFirstOrderId);

    }
}

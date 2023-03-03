package com.example.restapi.order;

import com.example.restapi.domain.order.Order;
import com.example.restapi.domain.order.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    @DisplayName("Orderを引数とし、saveを実行したとき、Orderが保存される。")
    public void given_when_then() {
        // given-precondition or Setup
        // 4000円の注文情報をクレカ(type)で決済した場合
        Order order = new Order(1,4000,new Date(),0,true,false);

        //when - action or the behavior that we are going test
        orderRepository.save(order);

        //then - verify the output
        assertThat(orderRepository.count()).isGreaterThan(0);
    }
}

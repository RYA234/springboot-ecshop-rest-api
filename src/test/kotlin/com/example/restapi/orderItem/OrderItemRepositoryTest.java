package com.example.restapi.orderItem;

import com.example.restapi.domain.orderItem.OrderItem;
import com.example.restapi.domain.orderItem.OrderItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class OrderItemRepositoryTest {
    @Autowired
    OrderItemRepository orderItemRepository;


    @Test
    @DisplayName("orderItemを引数とし、saveを実行したとき、OrderItemsが保存される。")
    public void givenOrderItem_whenSave_thenOrderItem() {
        // given-precondition or Setup
        // orderId=1 productId=1 quantity=3のorderitemとする
        OrderItem orderItem = new OrderItem(1,2,3);
        //when - action or the behavior that we are going test
        orderItemRepository.save(orderItem);
        //then - verify the output
        assertThat(orderItemRepository.count()).isGreaterThan(0);
    }
}

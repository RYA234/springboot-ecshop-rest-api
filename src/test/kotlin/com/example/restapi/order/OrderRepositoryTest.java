package com.example.restapi.order;

import com.example.restapi.domain.order.Order;
import com.example.restapi.domain.order.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    @DisplayName("Orderを引数とし、Saveを実行したとき、Orderが返される。")
    public void givenOrder_whenSave_thenReturnOrder() {
        // given-precondition or Setup
        Order newOrder = new Order();
        //when - action or the behavior that we are going test
        Order savedOrder = orderRepository.save(newOrder);
        //then - verify the output
        assertThat(savedOrder.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("cutomerIdとPageableを引数とし、findByCustomerIdを実行したとき、ページネーションかされたOrdersが返される。")
    public void givenCustomerIdandPageable_whenFindByCustomerId_thenReturnPageOrder() {
        // given-precondition or Setup

        //when - action or the behavior that we are going test

        //then - verify the output
    }

    @Test
    @DisplayName("idを引数とし、findOrderByIdを実行したとき、Orderが１件返される。")
    public void givenId_whenFindOrderById_thenReturnOrder() {
        // given-precondition or Setup
        Integer id = 0;
        //when - action or the behavior that we are going test
        Order actualOrder = new Order();
        //then - verify the output
        Order expectedOrder = orderRepository.findOrderById(id);
    }
}

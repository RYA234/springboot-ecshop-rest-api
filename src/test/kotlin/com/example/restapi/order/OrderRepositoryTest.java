package com.example.restapi.order;

import com.example.restapi.domain.order.Order;
import com.example.restapi.domain.order.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

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
        Order newOrder = new Order(0, toDate(LocalDateTime.now()),6000f,100f,6100f,610f,6710f,"pending");
        //when - action or the behavior that we are going test
        Order savedOrder = orderRepository.save(newOrder);
        //then - verify the output
        assertThat(savedOrder.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("cutomerIdとPageableを引数とし、findByCustomerIdを実行したとき、ページネーションかされたOrdersが返される。")
    public void givenCustomerIdandPageable_whenFindByCustomerId_thenReturnPageOrder() {
        // given-precondition or Setup
        Integer customerId = 0;
        // make pageable
        int pageNo = 0;
        int pageSize =2;
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        //when - action or the behavior that we are going test
        Page<Order> actualOrder = orderRepository.findByCustomerId(customerId,pageable);
        //then - verify the output
        int expectedTotal =3;
        int expectedContentSize = 2;
        assertThat(actualOrder.getTotalElements()).isEqualTo(expectedTotal);
        assertThat(actualOrder.getContent().size()).isEqualTo(expectedContentSize);

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

    private Date toDate(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zone);
        Instant instant = zonedDateTime.toInstant();
        return Date.from(instant);
    }
}

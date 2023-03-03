package com.example.restapi.domain.orderItem;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem,Integer> {
    OrderItem save(OrderItem orderItem);

}

package com.example.restapi.domain.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @brief: Order Repository Class
 *
 * @description  本番ではMYSQL、単体テストではH2データベースを使う想定です。
 *
 *
 * @Auther RYA234
 *
 */

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order,Integer> {
    Page<Order> findByCustomerId(Integer customerId, Pageable pageable);
    Order findOrderById(Integer id);

}

package com.example.restapi.domain.order;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderDetailRepository  extends CrudRepository<OrderDetail, Integer>
{
    List<OrderDetail> findByOrderId(Integer orderId);
    OrderDetail save(Integer id);
}

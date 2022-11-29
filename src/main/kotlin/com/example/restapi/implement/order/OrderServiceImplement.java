package com.example.restapi.implement.order;

import com.example.restapi.domain.order.*;
import com.example.restapi.implement.cartItem.CartItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImplement implements OrderService {

    @Autowired
    OrderRepository orderRepository;


    @Override
    public void create(Integer customerId, List<CartItemDto> cartItemDtos, PaymentMethod paymentMethod) {

    }


    @Override
    public OrderResponse listByPageByCustomer(Integer customerId, int pageNo,int pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Order> orders  = orderRepository.findByCustomerId(customerId, pageable);
        List<Order> orderList = orders.getContent();
        List<OrderDto> content = orderList.stream().map(order ->  mapToDTO(order)).collect(Collectors.toList());
        return new OrderResponse(content,pageNo,pageSize,orders.getTotalElements(),orders.getTotalPages(),orders.isLast());
    }

    @Override
    public com.example.restapi.domain.order.Order get(Integer orderId) {
        return orderRepository.findOrderById(orderId);
    }

    private Order mapToEntity(OrderDto orderDto){
        Order order = new Order();

        order.setId(orderDto.getId());
        order.setCustomerId(orderDto.getCustomerId());
        order.setOrderTime(orderDto.getOrderTime());
        order.setProductCost(orderDto.getProductCost());
        order.setShippingCost(orderDto.getShippingCost());
        order.setSubtotal(orderDto.getSubtotal());
        order.setTax(orderDto.getTax());
        order.setTotal(orderDto.getTotal());
        order.setStatus(orderDto.getStatus());

        return order;
    }

    private OrderDto mapToDTO(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setCustomerId(order.getCustomerId());
        orderDto.setOrderTime(order.getOrderTime());
        orderDto.setProductCost(order.getProductCost());
        orderDto.setShippingCost(order.getShippingCost());
        orderDto.setSubtotal(order.getSubtotal());
        orderDto.setTax(order.getTax());
        orderDto.setTotal(order.getTotal());
        orderDto.setStatus(order.getStatus());

        return orderDto;
    }

}

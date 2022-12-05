package com.example.restapi.implement.order;

import com.example.restapi.domain.order.*;
import com.example.restapi.domain.product.ProductRepository;
import com.example.restapi.implement.cartItem.CartItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @brief:  Order service implement class
 *
 * @description  Software architecture is based on  Controller-"Service"-Repository Pattern
 *               This class is "Service" and charge of business logic in Order Domain.
 * @Auther RYA234
 *
 * @Entity: {@link  Order}
 * @UseCase: {@link OrderService}
 */
@Service
public class OrderServiceImplement implements OrderService {

    @Autowired
    OrderRepository orderRepository;


    @Autowired
    ProductRepository productRepository;



    @Override
    public OrderDto create(Integer customerId, List<CartItemDto> cartItemDtos, PaymentMethod paymentMethod) {
        OrderDto orderDto = new OrderDto();
        // cal productCost
       for(var cartItem :cartItemDtos){
           float miniSum = cartItem.getQuantity()*productRepository.getProductById(cartItem.getProductId()).getPrice();
           orderDto.setProductCost(orderDto.getProductCost() + miniSum);
       }
        // cal shipping
        if( orderDto.getProductCost() <= 4000f){
            orderDto.setShippingCost(300);
        }else{
            orderDto.setShippingCost(0);
        }

        // cal subtotal
        orderDto.setSubtotal(orderDto.getProductCost() + orderDto.getShippingCost());
        // cal tax
        for(var cartItem :cartItemDtos){
            float eachTax = cartItem.getQuantity()*productRepository.getProductById(cartItem.getProductId()).getPrice()*productRepository.getProductById(cartItem.getProductId()).getTaxRate();
            orderDto.setTax(orderDto.getTax() + eachTax);
        }
        // cal total;
        orderDto.setTotal(orderDto.getSubtotal() + orderDto.getTax());
        return orderDto;
    }

    @Override
    public void save(Order order) {

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
    public OrderDto get(Integer orderId) {
        OrderDto orderDto = mapToDTO(orderRepository.findOrderById(orderId));
        return orderDto;
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

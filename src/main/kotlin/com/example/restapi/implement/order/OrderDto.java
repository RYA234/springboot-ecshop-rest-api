package com.example.restapi.implement.order;

import lombok.Data;

import java.util.Date;

/**
 * @brief: This Class is Data To Object in Order.
 *
 * @description　when Service method is done,Order Class convert to OrderDto Class.
 *                    OrderDto can do change OrderClass not changing OrderEntity and write test code easily.
 *
 * @Auther RYA234
 *
 * @Entity: {@link  com.example.restapi.domain.order.Order}
 * @UseCase: {@link com.example.restapi.domain.order.OrderService}
 */
@Data
public class OrderDto {
    private Integer id;
    private Integer customerId;
    private Date orderTime;
    private float productCost;
    private float shippingCost;
    private float subtotal;
    private float tax;
    private float total;
    private String status;

    public OrderDto(Integer id, Integer customerId, Date orderTime, float productCost, float shippingCost, float subtotal, float tax, float total, String status) {
        this.id = id;
        this.customerId = customerId;
        this.orderTime = orderTime;
        this.productCost = productCost;
        this.shippingCost = shippingCost;
        this.subtotal = subtotal;
        this.tax = tax;
        this.total = total;
        this.status = status;
    }

    public OrderDto() {

    }
}

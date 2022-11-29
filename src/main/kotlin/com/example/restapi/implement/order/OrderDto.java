package com.example.restapi.implement.order;

import lombok.Data;

import java.util.Date;

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
}

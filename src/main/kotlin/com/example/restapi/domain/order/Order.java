package com.example.restapi.domain.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;




@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  //  @Column(name = "id", nullable = false)
    private Integer id;

    // 注文した顧客のID
    @Column(name = "customer_id")
    private Integer customerId;

    // 注文日　YYYY:MM:DD
    @Column(name = "order_time")
    private Date orderTime;

    // 商品の値段/円
    @Column(name = "product_cost")
    private float productCost;

    // 配送費　購入金額によって変化する。
    @Column(name = "shipping_cost")
    private float shippingCost;

    // 税別
    @Column(name = "subtotal")
    private float subtotal;

    // 消費税の和 8%と10%に分ける
    @Column(name = "tax")
    private float tax;

    // お客様払う総額（税＋税別）
    @Column(name = "total")
    private float total;

    // 注文の状態、
    @Column(name = "status")
    private String status;


    public Order(Integer customerId) {
        this.customerId = customerId;
    }

    public Order(Integer customerId, Date orderTime, float productCost, float shippingCost, float subtotal, float tax, float total, String status) {
        this.customerId = customerId;
        this.orderTime = orderTime;
        this.productCost = productCost;
        this.shippingCost = shippingCost;
        this.subtotal = subtotal;
        this.tax = tax;
        this.total = total;
        this.status = status;
    }

    public Order(Integer id, Integer customerId, Date orderTime, float productCost, float shippingCost, float subtotal, float tax, float total, String status) {
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


}

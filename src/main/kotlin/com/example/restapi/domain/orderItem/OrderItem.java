package com.example.restapi.domain.orderItem;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @JoinColumn(name ="order_id", nullable = false)
    Integer orderId;

    // 商品のId 商品名や商品価格などを参照するときに使う。
    // 値上げなどで商品情報が変化したときに対応できなくなる問題がある
    @JoinColumn(name ="product_id", nullable = false)
    Integer productId;

    // 購入した商品の量
    @JoinColumn(name ="quantity", nullable = false)
    Integer quantity;

    public OrderItem(Integer orderId, Integer productId, Integer quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public OrderItem() {
    }
}

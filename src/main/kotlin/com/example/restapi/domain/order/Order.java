package com.example.restapi.domain.order;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    // 顧客のId
    @JoinColumn(name ="customer_id")
    Integer customerId;

    // お客様が支払う金額
    @JoinColumn(name ="amount")
    Integer amount;

    @JoinColumn(name ="created_at")
    Date createdAt;

    // 支払い方法について　0だとクレカ　1だと現金着払い
    @JoinColumn(name ="type")
    Integer type;

    // 支払状況
    // クレカ決済の場合、データベースに追加時にtrueになる
    // 現金払いの場合、データベースに追加時にはfalseになる。
    @Column(name ="is_payment_finished", nullable = false)
    boolean isPaymentFinished;

    // 出荷状況
    // データベースに追加された時はfalseが入る
    // 客に商品を渡したときに発送担当者が、trueにする。
    @Column(name ="is_shipping_finished", nullable = false)
    boolean isShippingFinished;

    public Order(Integer customerId, Integer amount, Date createdAt, Integer type, boolean isPaymentFinished, boolean isShippingFinished) {
        this.customerId = customerId;
        this.amount = amount;
        this.createdAt = createdAt;
        this.type = type;
        this.isPaymentFinished = isPaymentFinished;
        this.isShippingFinished = isShippingFinished;
    }
}

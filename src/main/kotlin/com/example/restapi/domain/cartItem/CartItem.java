package com.example.restapi.domain.cartItem;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 *
 * @brief:  CartItem Entity class
 *
 * @description In this Project,"Entity" mean java Database O/R mapping,
 *              not mean Domain Driven Development.
 *
 *
 * @Auther RYA234
 *
 * @UseCase: {@link CartItemService}
 */

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "cart_items")
public class CartItem{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @JoinColumn(name ="customer_id")
    Integer customerId;

    @JoinColumn(name ="product_id")
    Integer productId;

    @JoinColumn(name ="quantity")
    Integer quantity;

    public CartItem(int customerId, int productId, int quantity) {
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
    }
    public CartItem(Integer id, int customerId, int productId, int quantity) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
    }
}

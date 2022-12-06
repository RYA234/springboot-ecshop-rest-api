package com.example.restapi.implement.cartItem;

import lombok.Data;

@Data
public class CartItemDto {

    Integer id;

    Integer customerId;

    Integer productId;

    Integer quantity;

    public CartItemDto(Integer id, Integer customerId, Integer productId, Integer quantity) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public CartItemDto() {
    }
}

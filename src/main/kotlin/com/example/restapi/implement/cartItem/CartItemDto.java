package com.example.restapi.implement.cartItem;

import lombok.Data;

@Data
public class CartItemDto {

    Integer id;

    Integer customerId;

    Integer productId;

    Integer quantity;

    String productName;
    float priceWithoutTax;
    float priceWithTax;

    public CartItemDto(Integer id, Integer customerId, Integer productId, Integer quantity, String productName, float priceWithoutTax, float priceWithTax) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.productName = productName;
        this.priceWithoutTax = priceWithoutTax;
        this.priceWithTax = priceWithTax;
    }

    public CartItemDto() {

    }
}

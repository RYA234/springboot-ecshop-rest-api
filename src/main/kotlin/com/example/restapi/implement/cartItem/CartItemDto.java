package com.example.restapi.implement.cartItem;

import lombok.Data;

@Data
public class CartItemDto {

    Integer id;

    Integer customerId;

    Integer productId;

    Integer quantity;
}

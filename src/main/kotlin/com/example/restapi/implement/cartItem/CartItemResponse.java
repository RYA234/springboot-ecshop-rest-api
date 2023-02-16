package com.example.restapi.implement.cartItem;

import com.example.restapi.domain.cartItem.CartItem;
import lombok.Data;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Data
public class CartItemResponse {
    List<CartItemDto> cartItemDtos;
    private float productCost;
    private float shippingCost;
    private float subTotal;
    private float tax;
    private float total;
}

package com.example.restapi.domain.cartItem;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartItemService {
    public Integer addProduct(Integer productId,Integer quantity,Integer customerId);
    public List<CartItem> listCartItems(Integer customerId);
    public float updateQuantity(Integer productId, Integer quantity, Integer customerId);
    public void removeProduct(Integer productId,Integer customerId);
    public void deleteByCustomer(Integer customerId);


}


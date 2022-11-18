package com.example.restapi.implement.cartItem;

import com.example.restapi.domain.cartItem.CartItem;
import com.example.restapi.domain.cartItem.CartItemRepository;
import com.example.restapi.domain.cartItem.CartItemService;
import com.example.restapi.domain.product.Product;
import com.example.restapi.domain.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CartItemServiceImplement  implements CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public Integer addProduct(Integer productId, Integer quantity, Integer customerId)
    {
        Integer updatedQuantity = quantity;
        Product product = new Product(productId);

        CartItem cartItem = cartItemRepository.findByCustomerIdAndProductId(customerId,productId);
        if(cartItem != null){
            updatedQuantity = cartItem.getQuantity() + quantity;
        }else{
            cartItem = new CartItem();
            cartItem.setCustomerId(customerId);
            cartItem.setProductId(productId);
        }
        cartItem.setQuantity(updatedQuantity);
        cartItemRepository.save(cartItem);

        return updatedQuantity;
    }
    @Override
    public List<CartItem> listCartItems(Integer customerId) {



        return null;
    }

    @Override
    public float updateQuantity(Integer productId, Integer quantity, Integer customerId) {
        return 0;
    }

    @Override
    public void removeProduct(Integer productId, Integer customerId) {

    }

    @Override
    public void deleteByCustomer(Integer customerId) {

    }

    private CartItemDto mapToDTO(CartItem cartItem){
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setId(cartItem.getId());
        cartItemDto.setProductId(cartItem.getProductId());
        cartItemDto.setCustomerId(cartItem.getCustomerId());
        cartItemDto.setQuantity(cartItem.getQuantity());
        return cartItemDto;
    }

    private CartItem mapToEntity(CartItemDto cartItemDto){
        CartItem cartItem = new CartItem();
        cartItemDto.setId(cartItem.getId());
        cartItemDto.setQuantity(cartItem.getQuantity());
        cartItemDto.setProductId(cartItem.getProductId());
        cartItemDto.setCustomerId(cartItem.getCustomerId());

        return cartItem;
    }
}

package com.example.restapi.implement.cartItem;

import com.example.restapi.domain.cartItem.CartItem;
import com.example.restapi.domain.cartItem.CartItemRepository;
import com.example.restapi.domain.cartItem.CartItemService;
import com.example.restapi.domain.product.Product;
import com.example.restapi.domain.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
@Transactional
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
    public CartItemResponse listCartItems(Integer customerId) {

        List<CartItem> cartItems = cartItemRepository.findByCustomerId(customerId);
        CartItemResponse cartItemResponse = new CartItemResponse();
        List<CartItemDto> cartItemDtos = new ArrayList<>() ;
        // setCartItemResponse
        for(var cartItem:cartItems){
            Product product = productRepository.getProductById(cartItem.getProductId());
            CartItemDto cartItemDto=new CartItemDto(cartItem.getId(),cartItem.getCustomerId(),cartItem.getProductId(),cartItem.getQuantity(),product.getName(),product.getPrice(),product.getPrice()*(1+product.getTaxRate()));
            cartItemDtos.add(cartItemDto);
        }
        cartItemResponse.setCartItemDtos(cartItemDtos);
        // cal productCost
        for(var cartItem :cartItems){
            float miniSum = cartItem.getQuantity()*productRepository.getProductById(cartItem.getProductId()).getPrice();
            cartItemResponse.setProductCost(cartItemResponse.getProductCost() + miniSum);
        }
        // cal shipping
        if( cartItemResponse.getProductCost() <= 4000f){
            cartItemResponse.setShippingCost(300);
        }else{
            cartItemResponse.setShippingCost(0);
        }

        // cal subtotal
        cartItemResponse.setSubTotal(cartItemResponse.getProductCost() + cartItemResponse.getShippingCost());

        // cal tax
        for(var cartItem :cartItems){
            float eachTax = cartItem.getQuantity()*productRepository.getProductById(cartItem.getProductId()).getPrice()*productRepository.getProductById(cartItem.getProductId()).getTaxRate();
            cartItemResponse.setTax(cartItemResponse.getTax() + eachTax);
        }
        // cal total;
        cartItemResponse.setTotal(cartItemResponse.getSubTotal() + cartItemResponse.getTax());
        return cartItemResponse;
//        return cartItemList.stream().map(cartItem -> mapToDTO(cartItem)).collect(Collectors.toList());
    }
    @Override
    public void updateQuantity(Integer productId, Integer quantity, Integer customerId) {
        cartItemRepository.updateQuantity(quantity,customerId,productId);
    }

    @Override
    public void removeProduct(Integer productId, Integer customerId) {
        cartItemRepository.deleteByCustomerIdAndProductId(customerId,productId);
    }

    @Override
    public void deleteByCustomer(Integer customerId) {
        cartItemRepository.deleteByCustomerId(customerId);
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

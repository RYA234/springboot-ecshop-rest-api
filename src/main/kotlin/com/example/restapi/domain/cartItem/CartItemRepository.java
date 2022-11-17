package com.example.restapi.domain.cartItem;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Integer> {

   // @Query("SELECT c FROM CartItem c WHERE c.customerId = ?1")
    public List<CartItem> findByCustomerId(Integer customerId);


    public CartItem findByCustomerIdAndProductId(Integer customerId,Integer productId);

    @Modifying
    @Query("UPDATE CartItem c SET c.quantity = ?1 WHERE c.customerId = ?2 AND c.productId = ?3")
    public void updateQuantity(Integer quantity, Integer customerId, Integer productId);

    public void deleteByCustomerIdAndProductId(Integer customerId,Integer productId);

    public void deleteByCustomerId(Integer customerId);
}

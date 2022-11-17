package com.example.restapi.cartItem;

import com.example.restapi.domain.cartItem.CartItem;
import com.example.restapi.domain.cartItem.CartItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CartItemRepositoryTest {

    @Autowired
    CartItemRepository cartItemRepository;

    @Test
    @DisplayName("CartItemを引数とし、SaveRepositoryを実行したとき、CartItemが返される。")
    public void givenCartItem_whenSaveRepository_thenReturnCartItem() {
        // given - precondition or Setup
        CartItem newCartItem = new CartItem(1,2,3);
        // when - action or the behavior that we are going test
        CartItem savedItem = cartItemRepository.save(newCartItem);
        // then - verify the output
        assertThat(savedItem.getId()).isGreaterThan(0);
    }
    @Test
    @DisplayName("CustomerIdを引数とし、findByCustomerを実行したとき、CartItemListが返される。")
    public void givenCustomerId_whenFindByCustomer_thenReturnCartItemList() {
        // given-precondition or Setup
        Integer customerId = 3;
        //when - action or the behavior that we are going test
        List<CartItem> findCartItemList = cartItemRepository.findByCustomerId(3);
        //then - verify the output
        assertThat((long) findCartItemList.size()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Customerクラスを引数とし、findByCustomerIdAndProductIdを実行したとき、Customerが追加される。")
    public void givenCustomer_whenfindByCustomerIdAndProductId_thenReturnCartItem() {
        // given-precondition or Setup
        Integer customerId = 3;
        Integer productId =10;

        //when - action or the behavior that we are going test
        CartItem actualCartItem =   cartItemRepository.findByCustomerIdAndProductId(customerId,productId);

        //then - verify the output
        CartItem expectedCartItem = new CartItem(2,3,10,2);

        assertThat(actualCartItem.getId()).isEqualTo(expectedCartItem.getId());
        assertThat(actualCartItem.getProductId()).isEqualTo(expectedCartItem.getProductId());
        assertThat(actualCartItem.getCustomerId()).isEqualTo(expectedCartItem.getCustomerId());
        assertThat(actualCartItem.getQuantity()).isEqualTo(expectedCartItem.getQuantity());
    }

    @Test
    @DisplayName("customerIdとproductIdとquantityを引数とし UpdateChangeを実行したとき、quantityが変更される")
    public void givenQuantityAndQuantityAndCustomerIdAndProductId_whenUpdateQuanitity_thenChangeQuantity() {
        // given-precondition or Setup
        Integer quantity = 10;
        Integer customerId = 3;
        Integer productId = 10;
        //when - action or the behavior that we are going test
        cartItemRepository.updateQuantity(quantity,customerId,productId);
        //then - verify the output
        CartItem expectedCartItem = cartItemRepository.findByCustomerIdAndProductId(customerId,productId);

        assertThat(expectedCartItem.getQuantity()).isEqualTo(quantity);
    }

    @Test
    @DisplayName("customerIdとproductIdを引数とし、DeleteByCustomerIdAndProductIdを実行したとき、thenが一致する。")
    public void givenCustomerIdAndProductId_whenDeleteByCustomerIdAndProductId_thenDeleteCarItem() {
        // given-precondition or Setup
        Integer customerId =3;
        Integer productId = 10;
        //when - action or the behavior that we are going test
        cartItemRepository.deleteByCustomerIdAndProductId(customerId,productId);
        //then - verify the output
       CartItem expectedCartItem = cartItemRepository.findByCustomerIdAndProductId(customerId,productId);
        assertThat(expectedCartItem).isNull();
    }

    @Test
    @DisplayName("customerIdを引数とし、DeleteByCustomerIdを実行したとき、customerのCartItemsがすべて削除される")
    public void givenCustomerId_whenDeleteByCustomerId_thenDeleteCartItem() {
        // given-precondition or Setup
        Integer customerId = 3;
        // when - action or the behavior that we are going test
        cartItemRepository.deleteByCustomerId(customerId);
        // then - verify the output
        List<CartItem> actualCartItems;
        actualCartItems = cartItemRepository.findByCustomerId(customerId);

        Assertions.assertTrue(actualCartItems.isEmpty());
    }
}

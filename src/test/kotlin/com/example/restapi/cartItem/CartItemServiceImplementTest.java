package com.example.restapi.cartItem;

import com.example.restapi.domain.cartItem.CartItem;
import com.example.restapi.domain.cartItem.CartItemRepository;
import com.example.restapi.domain.product.ProductRepository;
import com.example.restapi.implement.cartItem.CartItemServiceImplement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CartItemServiceImplementTest {

    @MockBean
    private CartItemRepository cartItemRepository;
    @MockBean
    private ProductRepository productRepository;
    @InjectMocks
    private CartItemServiceImplement cartItemServiceImplement;

    // if CartItem is  not empty
    @Test
    @DisplayName("該当するCartItemが既にある場合、AddProductを実行したとき、quantityが上書きされる（商品がカートに無い場合）。")
    public void givenProductIdAndQuantityAndCustomerId_whenAddProduct_thenReturnUpdatedQuantity() {
        // given-precondition or Setup
        int productId = 3;
        int beforeQuantity = 4;
        int addQuantity = 10;
        int customerId = 1;
        Integer actualQuantity;
        CartItem beforeUpdatedCartItem = new CartItem(customerId,productId,beforeQuantity);
        CartItem afterUpdatedCartItem = new CartItem(customerId,productId,addQuantity);
        Mockito.doReturn(beforeUpdatedCartItem).when(cartItemRepository).findByCustomerIdAndProductId(customerId,productId);
        Mockito.doReturn(afterUpdatedCartItem).when(cartItemRepository).save(afterUpdatedCartItem);

        //when - action or the behavior that we are going test
        actualQuantity = cartItemServiceImplement.addProduct(productId,addQuantity,customerId);

        //then - verify the output
        Integer expectedQuantity=14;
        assert(actualQuantity.equals(expectedQuantity));
    }

    // if CartItem is empty Negative pattern
    @Test
    @DisplayName("該当するCartItemが無い場合、AddProductを実行したとき、CartItemが新規作成される。")
    public void givenNoProductIdAndQuantityAndCustomerId_whenAddProduct_thenReturnUpdatedQuantity() {
        // given-precondition or Setup
        int productId = 3;
        int addQuantity = 10;
        int customerId = 1;
        Integer actualQuantity;

        CartItem afterUpdatedCartItem = new CartItem(customerId,productId,addQuantity);
        Mockito.doReturn(null).when(cartItemRepository).findByCustomerIdAndProductId(customerId,productId);
        Mockito.doReturn(afterUpdatedCartItem).when(cartItemRepository).save(afterUpdatedCartItem);

        //when - action or the behavior that we are going test
        actualQuantity = cartItemServiceImplement.addProduct(productId,addQuantity,customerId);

        //then - verify the output
        Integer expectedQuantity=10;
        assert(actualQuantity.equals(expectedQuantity));
    }

    @Test
    @DisplayName("givenを引数とし、whenを実行したとき、thenがとなる。")
    public void givenCustomerId_whenListCartItems_thenReturnCartItemList() {
        // given-precondition or Setup
        Integer custoemerId = 1;
        List<CartItem> cartItemList = new ArrayList<>();
        cartItemList.add(new CartItem(0,1,2,3));
        cartItemList.add(new CartItem(1,1,5,2));
        cartItemList.add(new CartItem(2,1,9,4));
        cartItemList.add(new CartItem(1,1,21,6));
        Mockito.doReturn(cartItemList).when(cartItemRepository).findByCustomerId(custoemerId);

        //when - action or the behavior that we are going test


        //then - verify the output
    }
}

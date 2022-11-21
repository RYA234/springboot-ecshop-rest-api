package com.example.restapi.cartItem;

import com.example.restapi.domain.cartItem.CartItem;
import com.example.restapi.domain.cartItem.CartItemRepository;
import com.example.restapi.domain.product.ProductRepository;
import com.example.restapi.implement.cartItem.CartItemDto;
import com.example.restapi.implement.cartItem.CartItemServiceImplement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
    @DisplayName("customerIdを引数とし、listCartItemsを実行したとき、customerIdのカート内すべてを表示する。")
    public void givenCustomerId_whenListCartItems_thenReturnCartItemList() {
        // given-precondition or Setup
        Integer custoemerId = 1;
        List<CartItem> cartItemList = new ArrayList<>();
        cartItemList.add(new CartItem(0,1,2,3));
        cartItemList.add(new CartItem(1,1,5,2));
        cartItemList.add(new CartItem(2,1,9,4));
        cartItemList.add(new CartItem(1,1,21,6));
        Mockito.doReturn(cartItemList).when(cartItemRepository).findByCustomerId(custoemerId);
        List<CartItemDto> expectedCartItemDtoList = new ArrayList<>();
        for (int i=0;i<cartItemList.stream().count();i++){
            expectedCartItemDtoList.add(ReflectionTestUtils.invokeMethod(cartItemServiceImplement,"mapToDTO",cartItemList.get(i)));
        }
        //when - action or the behavior that we are going test
        List<CartItemDto>  actualCartItemDtoList = cartItemServiceImplement.listCartItems(custoemerId);
        //then - verify the output
        assert(expectedCartItemDtoList.equals(actualCartItemDtoList));
    }

    @Test
    @DisplayName("productIdとCustomerIdと数量を引数とし、updateQuantityを実行したとき、CartItemの数量が変更される。")
    public void givenProductIdAndQuantityAndCustomerId_whenUpdateQuantity_thenReturnQuantity() {
        // given-precondition or Setup
        Integer productId = 2;
        Integer quantity = 3;
        Integer customerId = 4;
        Mockito.doNothing().when(cartItemRepository).updateQuantity(quantity,customerId,productId);
        //when - action or the behavior that we are going test
        cartItemServiceImplement.updateQuantity(productId,quantity,customerId);
        //then - verify the output
        verify(cartItemRepository,times(1)).updateQuantity(quantity,customerId,productId);
    }

    @Test
    @DisplayName("givenを引数とし、whenを実行したとき、thenがとなる。")
    public void givenProductIdAndCustomerId_whenRemoveProduct_thenDeleteOneItemInCart() {
        // given-precondition or Setup
        Integer productId = 1;
        Integer customerId = 2;
        Mockito.doNothing().when(cartItemRepository).deleteByCustomerIdAndProductId(customerId,productId);

        // when - action or the behavior that we are going test
        cartItemServiceImplement.removeProduct(productId,customerId);

        // then - verify the output
        verify(cartItemRepository,times(1)).deleteByCustomerIdAndProductId(customerId,productId);
    }

    @Test
    @DisplayName("CustomerIdを引数とし、DeleteByCustomerを実行したとき、カート内の商品全てが削除される。")
    public void givenProductIdAndCustomerId_whenDeleteByCustomer_thenDeleteAllCartItem() {
        // given-precondition or Setup
        Integer customerId = 1;
        Mockito.doNothing().when(cartItemRepository).deleteByCustomerId(customerId);
        //when - action or the behavior that we are going test
        cartItemServiceImplement.deleteByCustomer(customerId);
         //then - verify the output
        verify(cartItemRepository,times(1)).deleteByCustomerId(customerId);
    }




    @Test
    @DisplayName("privateMethodが使えるかの検証")
    public void given_when_then() {
        CartItem cartItem = new CartItem(1,2,4);
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto = ReflectionTestUtils.invokeMethod(cartItemServiceImplement,"mapToDTO",cartItem);
    }
}

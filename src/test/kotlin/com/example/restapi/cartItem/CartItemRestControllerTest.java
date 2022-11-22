package com.example.restapi.cartItem;

import com.example.restapi.domain.cartItem.CartItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class CartItemRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartItemService cartItemService;

    @Autowired
    private ObjectMapper objectMapper;



    @Test
    @DisplayName("customerIdを引数とし、showCartを実行したとき、ユーザーが現在持っているCartItemを取得する。")
    public void givenCustomerId_whenShowCart_thenReturnCartItems() {
        // given-precondition or Setup

        //when - action or the behavior that we are going test

        //then - verify the output
    }

    @Test
    @DisplayName("producIdとquantityとcustomerIdを引数とし、AddProductを実行したとき、カートに数量分の商品が追加される。")
    public void givenProductIdAndQuantityAndCustomerId_whenAddProduct_thenAddItemInCart() {
        // given-precondition or Setup

        //when - action or the behavior that we are going test

        //then - verify the output
    }


}

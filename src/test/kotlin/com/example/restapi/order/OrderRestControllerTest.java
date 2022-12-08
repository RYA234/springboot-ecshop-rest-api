package com.example.restapi.order;

import com.example.restapi.domain.cartItem.CartItemService;
import com.example.restapi.domain.customer.CustomerService;
import com.example.restapi.domain.order.OrderService;
import com.example.restapi.implement.cartItem.CartItemDto;
import com.example.restapi.implement.order.OrderDto;
import com.example.restapi.implement.order.OrderRestController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderRestController.class)
public class OrderRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerService customerService;
    @MockBean
    private  OrderService orderService;
    @MockBean
    private CartItemService cartItemService;

    @Test
    @DisplayName("HttpResponseを引数とし、Createを実行したとき、注文情報がJson形式で返される。")
    public void givenHttpResponse_whenCreate_thenReturnOrderbyJson() throws Exception {
        // given-precondition or Setup


        // make mock in customerId from HttpResponse
        //HttpResponse -> Jwt -> customerId(Email)
        HttpServletRequest request = null;
       //  Mockito.when(customerService.getIdfromJwtToken(request)).thenReturn(3);
        // todo SpringSecurityの挙動が謎すぎて、コントローラーの単体テストが書けない。
        // todo 一旦保留にしてSpringSecurityの単体テストを片づける。
        Integer customerId = 3;
        Mockito.doReturn(3).when(customerService.getIdfromJwtToken(request));

        // make mock in cartItem
        List<CartItemDto> cartItemDtos = new ArrayList<>();
        cartItemDtos.add(new CartItemDto(1,3,3,5));
        cartItemDtos.add(new CartItemDto(1,3,7,1));
        cartItemDtos.add(new CartItemDto(1,3,10,9));
        cartItemDtos.add(new CartItemDto(1,3,13,9));
        cartItemDtos.add(new CartItemDto(1,3,15,3));
        Mockito.doReturn(cartItemDtos).when(cartItemService.listCartItems(customerId));

        //  make mock in Order create
        OrderDto expectedOrderDto = new OrderDto(10,3,toDate(LocalDateTime.now()),3900f,300f,4200f,390f,4590f, "pending");
        //when - action or the behavior that we are going test
       ResultActions response = mockMvc.perform(get("/api/order",request));
        //then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }
    private Date toDate(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zone);
        Instant instant = zonedDateTime.toInstant();
        return Date.from(instant);
    }
}

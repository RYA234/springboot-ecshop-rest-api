package com.example.restapi.implement.order;

import com.example.restapi.domain.cartItem.CartItemService;
import com.example.restapi.domain.customer.CustomerService;
import com.example.restapi.domain.order.OrderService;
import com.example.restapi.domain.order.PaymentMethod;
import com.example.restapi.implement.cartItem.CartItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class OrderRestController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/api/order")
    public OrderDto create(HttpServletRequest request){
        OrderDto orderDto = new OrderDto();
        // get customerId from HttpRequest
        Integer customerId = customerService.getIdfromJwtToken(request);
        // get cartItems
        List<CartItemDto> cartItemDtos = cartItemService.listCartItems(customerId);

        return orderService.create(customerId,cartItemDtos, PaymentMethod.CREDIT_CARD);
    }
}

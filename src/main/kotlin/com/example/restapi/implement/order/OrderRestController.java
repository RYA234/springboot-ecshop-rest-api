package com.example.restapi.implement.order;

import com.example.restapi.domain.cartItem.CartItemService;
import com.example.restapi.domain.customer.CustomerService;
import com.example.restapi.domain.order.OrderService;
import com.example.restapi.implement.cartItem.CartItemResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "${frontend.URL}")
@RestController
public class OrderRestController {

    @Autowired
    OrderService orderService;

    @Autowired
    CartItemService cartItemService;

    @Autowired
    CustomerService customerService;

    @PostMapping("/api/pay/payment-intent")
    public ResponseEntity<String> preparePayment(@RequestBody PaymentInfoRequest paymentInfoRequest)throws StripeException{
        PaymentIntent paymentIntent = orderService.createPaymentIntent(paymentInfoRequest);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<>(paymentStr, HttpStatus.OK);
    }

    @PutMapping("/api/pay/finish")
    public void finish( HttpServletRequest request){
        Integer customerId = customerService.getIdfromJwtToken(request);
        CartItemResponse cartItemResponse = cartItemService.listCartItems(customerId);
        orderService.finishOrder(customerId,(int)cartItemResponse.getTotal(),0,cartItemResponse.getCartItemDtos());
    }
}

package com.example.restapi.implement.order;

import com.example.restapi.domain.cartItem.CartItemService;
import com.example.restapi.domain.customer.CustomerService;
import com.example.restapi.domain.order.OrderService;
import com.example.restapi.implement.cartItem.CartItemResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "${frontend.URL}")
@RestController
@Api(value = "OrderRestController", tags = "クレジット決済機能に関するAPI　認可は必要")
public class OrderRestController {

    @Autowired
    OrderService orderService;

    @Autowired
    CartItemService cartItemService;

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/api/pay/payment-intent", method = RequestMethod.POST,produces = "application/json")
    @ApiOperation(value = "決済準備API。",notes="Stripeの支払い情報を新規作成するAPIです。決済画面に進む時に実行されます。")
    public ResponseEntity<String> preparePayment(@RequestBody PaymentInfoRequest paymentInfoRequest)throws StripeException{
        PaymentIntent paymentIntent = orderService.createPaymentIntent(paymentInfoRequest);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<>(paymentStr, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/pay/finish", method = RequestMethod.PUT,produces = "application/json")
    @ApiOperation(value = "決済完了API。",notes="お客様の買い物カゴ情報を全消去し、注文情報をDBに保存します。フロントエンド側でStripeの支払い情報を完了した後に実行されます。")
    public void finish( HttpServletRequest request){
        Integer customerId = customerService.getIdfromJwtToken(request);
        CartItemResponse cartItemResponse = cartItemService.listCartItems(customerId);
        orderService.finishOrder(customerId,(int)cartItemResponse.getTotal(),0,cartItemResponse.getCartItemDtos());
    }
}

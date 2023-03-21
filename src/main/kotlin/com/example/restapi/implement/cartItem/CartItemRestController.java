package com.example.restapi.implement.cartItem;


import com.example.restapi.domain.cartItem.CartItemService;
import com.example.restapi.domain.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins = "${frontend.URL}")
public class CartItemRestController {
    @Autowired
    CartItemService cartItemService;
    @Autowired
    CustomerService customerService;

    @PostMapping("/api/cart/add")
    public String addProductCart(  @RequestParam("productId") Integer productId,
                                   @RequestParam("quantity") Integer quantity,
                                   HttpServletRequest request
    ){
        String message;
        try {
            //Get customerId from Jwt
            Integer customerId = customerService.getIdfromJwtToken(request);
            cartItemService.addProduct(productId, quantity, customerId);
            // String  email = jwtTokenProvider.getCustomernameFromJWT(token);
            message = "customerId:"+ customerId +  "added" + quantity +"quantity productId" + productId;
            return message;
        }catch(Exception e){
            message = "Exception error";
            return message;
        }
    }

    @GetMapping("/api/cart/all")
    public CartItemResponse listCartItems(HttpServletRequest request){
        List<CartItemDto> cartItemDtoList = new ArrayList<>();
        //Get customerId from Jwt
        Integer customerId = customerService.getIdfromJwtToken(request);
        return cartItemService.listCartItems(customerId);
        }

    @PutMapping("/api/cart/update")
    public String updateQuantity(@RequestParam("productId") Integer productId,
                                 @RequestParam("quantity") Integer quantity,
                                 HttpServletRequest request){
        //Get customerId from Jwt
        Integer customerId = customerService.getIdfromJwtToken(request);
        cartItemService.updateQuantity(productId,quantity,customerId);
        return "updated";
    }


    @DeleteMapping("/api/cart/remove")
    public String removeProduct( @RequestParam("productId") Integer productId,
                                 HttpServletRequest request){
            //Get customerId from Jwt
            Integer customerId = customerService.getIdfromJwtToken(request);
            cartItemService.removeProduct(productId,customerId);
            return "ShoppingCart is removed in customerId" + customerId ;
        }

    @DeleteMapping("/api/cart/delete")
    public String deleteByCustomer(HttpServletRequest request){
        //Get customerId from Jwt
        Integer customerId = customerService.getIdfromJwtToken(request);
        cartItemService.deleteByCustomer(customerId);
         return customerId + "is all deleted in all ShoppinCart";
    }





}

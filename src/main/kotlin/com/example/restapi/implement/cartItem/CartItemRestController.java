package com.example.restapi.implement.cartItem;


import com.example.restapi.domain.cartItem.CartItemService;
import com.example.restapi.domain.customer.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins = "${frontend.URL}")
@Api(value = "ProductRestController", tags = "買い物カゴ機能に関するAPI　認証は必要")
public class CartItemRestController {
    @Autowired
    CartItemService cartItemService;
    @Autowired
    CustomerService customerService;


    @RequestMapping(value = "/api/cart/add", method = RequestMethod.POST,produces = "application/json")
    @ApiOperation(value = "買い物カゴ商品追加API。",notes="買い物カゴに商品を追加するAPIです。商品一覧コンポーネントで使われることを想定しています。")
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


    @RequestMapping(value = "/api/cart/all", method = RequestMethod.GET,produces = "application/json")
    @ApiOperation(value = "買い物カゴ表示API。",notes="買い物カゴに入っている商品を表示するAPIです。買い物カゴの商品コンポーネントで使われることを想定しています。")
    public CartItemResponse listCartItems(HttpServletRequest request){
        List<CartItemDto> cartItemDtoList = new ArrayList<>();
        //Get customerId from Jwt
        Integer customerId = customerService.getIdfromJwtToken(request);
        return cartItemService.listCartItems(customerId);
        }

    @RequestMapping(value = "/api/cart/update", method = RequestMethod.PUT,produces = "application/json")
    @ApiOperation(value = "買い物カゴ商品数更新API。",notes="買い物カゴに商品を１つ更新するAPIです。買い物カゴの商品コンポーネントで使われることを想定しています。")
    public String updateQuantity(@RequestParam("productId") Integer productId,
                                 @RequestParam("quantity") Integer quantity,
                                 HttpServletRequest request){
        //Get customerId from Jwt
        Integer customerId = customerService.getIdfromJwtToken(request);
        cartItemService.updateQuantity(productId,quantity,customerId);
        return "updated";
    }



    @RequestMapping(value = "/api/cart/remove", method = RequestMethod.DELETE,produces = "application/json")
    @ApiOperation(value = "買い物カゴ商品削除API。",notes="買い物カゴに商品を１つ削除するAPIです。買い物カゴの商品コンポーネントで使われることを想定しています。")
    public String removeProduct( @RequestParam("productId") Integer productId,
                                 HttpServletRequest request){
            //Get customerId from Jwt
            Integer customerId = customerService.getIdfromJwtToken(request);
            cartItemService.removeProduct(productId,customerId);
            return "ShoppingCart is removed in customerId" + customerId ;
        }


    @RequestMapping(value = "/api/cart/delete", method = RequestMethod.DELETE,produces = "application/json")
    @ApiOperation(value = "買い物カゴに全削除API。",notes="ユーザーの買い物カゴ情報を全て削除するAPIです。決済が完了される際に使われることを想定しています。。")
    public String deleteByCustomer(HttpServletRequest request){
        //Get customerId from Jwt
        Integer customerId = customerService.getIdfromJwtToken(request);
        cartItemService.deleteByCustomer(customerId);
         return customerId + "is all deleted in all ShoppinCart";
    }





}

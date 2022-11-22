package com.example.restapi.domain.cartItem;

import com.example.restapi.implement.cartItem.CartItemDto;
import org.springframework.stereotype.Service;

import java.util.List;

// todo クラスのJavadocを追加します。
@Service
public interface CartItemService {
    // customerIdの顧客が買い物カゴにproductIdの商品をquantity個追加する。
    public Integer addProduct(Integer productId,Integer quantity,Integer customerId);
    // customerIdの顧客が買い物カゴの情報を取得します。
    public List<CartItemDto> listCartItems(Integer customerId);
    // customerIdの顧客が既に買い物カゴにあるproductIdの商品をquantity個に変化する。
    public void updateQuantity(Integer productId, Integer quantity, Integer customerId);
    // customerIdの顧客の買い物カゴにあるproductIdの商品を削除する。
    public void removeProduct(Integer productId,Integer customerId);
    // customerIdの顧客の買い物かごを全て削除する。
    public void deleteByCustomer(Integer customerId);
}


package com.example.restapi.Product;

import com.example.restapi.domain.product.Product;
import com.example.restapi.domain.product.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ProductRepositoryTest {
    @Autowired
   private ProductRepository productRepository;
    @Test
    @DisplayName("商品Idで商品情報を検索すると、商品の情報を取得する。。")
    public void giveProductObject_whenFindById_thenReturnProduct() {
        // given-precondition or Setup
        Product expectedProduct = new Product(0,"pork","this is pork",true,32,200,0.08f,0f,"pork_image");
        //when - action or the behavior that we are going test
        Product actualProduct = productRepository.getProductById(0);
        //then - verify the output
        assert(actualProduct.equals(expectedProduct));
    }

    @Test
    @DisplayName("givenにおいて、CategoryIdでしたとき、thenが一致する。")
    public void givenProductObject_whenFindByCategoryId_thenReturnPage_Product() {
        // given-precondition or Setup
        List<Product> expectedProductList = new ArrayList<>();
        expectedProductList.add( new Product(0,"pork","this is pork",true,32,200,0.08f,0f,"pork_image"));
        expectedProductList.add(new Product(1,"chicken","thisi a Chicken",true, 32,150,0.08f,0,"chicken_image"));
        int pageNo = 0;
        int pageSize = 2;
        int categoryId =32;
        // convert List<Product> to Page<Product>
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), expectedProductList.size());
        Page<Product> productPage = new PageImpl<>( expectedProductList.subList(start,end),pageable,expectedProductList.size());
        // when - action or the behavior that we are going test
        Page<Product> actualProducts = productRepository.findByCategoryId( categoryId, pageable);
        // then - verify the output
        int expectedTotal = 3;
        assertEquals(actualProducts.getTotalElements(),expectedTotal);
        assertEquals(actualProducts.getContent(),expectedProductList);
    }
}

package com.example.restapi.Product;

import com.example.restapi.domain.product.Product;
import com.example.restapi.domain.product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@DataJpaTest(showSql = false)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(false)

@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
   private ProductRepository productRepository;
    @BeforeEach
    public void setup(){
        productRepository.save(new Product(1,"Sardin","This is a Sardin",false, 1,200,0.01f,0,"aa"));
        productRepository.save(new Product(2,"Maguro","This is a Maguro",false, 1,200,0.01f,0,"maguro_image"));
        productRepository.save(new Product(3,"Salmon","This is a Salmon",false, 1,200,0.01f,0,"maguro_image"));
        productRepository.save(new Product(4,"Tuna","This is a Tuna",false, 1,200,0.01f,0,"maguro_image"));
        productRepository.save(new Product(5,"Pork","This is a Pork",false, 2,330,0.01f,0,"porkImage"));
        productRepository.save(new Product(6,"Beef","This is a Beef",false, 2,400,0.01f,0,"Beef_image"));
        productRepository.save(new Product(7,"Chicken","This is a chicken",false, 2,800,0.01f,0,"Chicken_image"));
        productRepository.save(new Product(8,"Spam","This is a Spam",false, 2,200,0.01f,0,"Spam_image"));
        productRepository.save(new Product(9,"Cabbage","This is a Cabbage",false, 3,100,0.01f,0,"Cabbage_image"));
        productRepository.save(new Product(10,"Carrot","This is a Carrot",false, 3,100,0.01f,0,"Carrot_image"));
        productRepository.save(new Product(11,"EggPlant","This is a EggPlant",false, 3,100,0.01f,0,"EggPlant_image"));
        productRepository.save(new Product(12,"Potato","This is a Potato",false, 3,100,0.01f,0,"Potato_image"));
        productRepository.save(new Product(13,"Bean","This is a Bean",false, 3,100,0.01f,0,"Bean_image"));
        productRepository.save(new Product(14,"Tomato","This is a Tomato",false, 3,100,0.01f,0,"Tomato_image"));
        productRepository.save(new Product(15,"Nuts","This is a Nuts",false, 3,100,0.01f,0,"Nuts_image"));

    }
    @Test
    @DisplayName("商品Idで商品情報を検索すると、商品の情報を取得する。。")
    public void giveProductObject_whenFindById_thenReturnProduct() {
        // given-precondition or Setup
        Product expectedProduct = new Product(5,"Pork","This is a Pork",false, 2,330,0.01f,0,"porkImage");
        //when - action or the behavior that we are going test
        Product actualProduct = productRepository.getProductById(5);
        //then - verify the output
        assert(actualProduct.equals(expectedProduct));
    }

    @Test
    @DisplayName("givenにおいて、CategoryIdでしたとき、thenが一致する。")
    public void givenCategoryIdAndPegeable_whenFindByCategoryId_thenReturnPage_Product() {
        // given-precondition or Setup
        int pageNo = 0;
        int pageSize = 2;
        int categoryId =1;
        // convert List<Product> to Page<Product>
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        // when - action or the behavior that we are going test
        Page<Product> actualProducts = productRepository.findByCategoryId( categoryId, pageable);
        // then - verify the output
        int expectedTotal = 4;
        assertEquals(actualProducts.getTotalElements(),expectedTotal);
    }
}

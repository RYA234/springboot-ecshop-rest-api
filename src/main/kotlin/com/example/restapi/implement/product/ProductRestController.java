package com.example.restapi.implement.product;

import com.example.restapi.domain.product.Product;
import com.example.restapi.domain.product.ProductService;
import com.example.restapi.implement.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @brief: This Class is RestController class in Architecture Controller-Service-Repository Pattern
 *
 * @description　
 *
 *
 * @Auther RYA234
 *
 * @Entity: {@link  Product}
 * @UseCase: {@link ProductService}
 */
@RestController
public class ProductRestController {

    @Autowired
     ProductService productService;

    @Autowired
    CategoryService categoryService;
    @GetMapping("/api/products")
    public ProductResponse getProductByCategory(
            @RequestParam(value ="pageNo", defaultValue = "0", required = false ) int pageNo,
            @RequestParam(value = "pageSize", defaultValue ="2", required = false) int pageSize,
            @RequestParam(value = "category", defaultValue="32", required = false) int categoryId
    ){
        ProductResponse productResponse;
        productResponse = productService.getProductsByCategory(pageNo,pageSize,categoryId);
        productResponse.setCategoryId(categoryId);
        productResponse.setCategoryName(categoryService.findById(categoryId).getName());

        return productResponse;
    }

    @GetMapping("/api/products/")
    public ProductDto getProductById(
            @RequestParam(value ="id",defaultValue = "1", required = false) int productId
    ){
        ProductDto productDto;
        productDto = productService.getProductById(productId);
        return productDto;
    }

}

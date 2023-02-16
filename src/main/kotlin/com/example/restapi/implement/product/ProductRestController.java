package com.example.restapi.implement.product;

import com.example.restapi.domain.product.Product;
import com.example.restapi.domain.product.ProductService;
import com.example.restapi.implement.category.CategoryService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
//
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "http://127.0.0.1:3000")
public class ProductRestController {

    @Autowired
     ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/api/products")
    public ProductResponse getProductByCategory(
            @RequestParam(value ="pageNo", defaultValue = "0", required = false ) int pageNo,
            @RequestParam(value = "pageSize", defaultValue ="4", required = false) int pageSize,
            @RequestParam(value = "category", defaultValue="1", required = false) int categoryId
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

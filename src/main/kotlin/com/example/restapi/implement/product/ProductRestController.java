package com.example.restapi.implement.product;

import com.example.restapi.implement.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductRestController {

    @Autowired
    ProductServiceImplement productServiceImplement;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/api/products")
    public ProductResponse getProductByCategory(
            @RequestParam(value ="pageNo", defaultValue = "0", required = false ) int pageNo,
            @RequestParam(value = "pageSize", defaultValue ="2", required = false) int pageSize,
            @RequestParam(value = "category", defaultValue="32", required = false) int categoryId
    ){
        ProductResponse productResponse;
        productResponse = productServiceImplement.getProductsByCategory(pageNo,pageSize,categoryId);
        productResponse.setCategoryId(categoryId);
        productResponse.setCategoryName(categoryService.findById(categoryId).getName());

        return productResponse;
    }



}

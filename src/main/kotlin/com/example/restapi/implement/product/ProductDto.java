package com.example.restapi.implement.product;

import com.example.restapi.domain.product.Product;
import com.example.restapi.domain.product.ProductService;
import lombok.Data;

import java.util.List;
import java.util.stream.Collector;

/**
 * @brief: This Class is Data To Object in Product.
 *
 * @description　when Service method is done,Product Class convert to ProductDto.
 *                    ProductDto can do service test code easily.
 *
 * @Auther RYA234
 *
 * @Entity: {@link  Product}
 * @UseCase: {@link ProductService}
 */
@Data
public class ProductDto {
    private Integer id;
    private String name;
    private String description;
    private boolean inStock;
    private Integer categoryId;
    private float price;
    private float taxRate;
    private float discountPercent;
    private String image;
}

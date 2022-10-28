package com.example.restapi.implement.product;

import lombok.Data;

import java.util.List;
import java.util.stream.Collector;

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

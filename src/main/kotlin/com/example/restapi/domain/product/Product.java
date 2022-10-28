package com.example.restapi.domain.product;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(length = 128, nullable = false, unique = true)
    private String name;

    @Column(name = "description",length = 4096, nullable = false)
    private String description;

    @Column(name = "in_stock", nullable = true)
    private boolean inStock;

    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    private float price;

    @Column(name = "tax_rate")
    private float taxRate;

    @Column(name = "discount_percent")
    private float discountPercent;

    @Column(nullable = false)
    private String image;

    public Product(Integer id) {
        this.id = id;
    }

    public Product(Integer id, String name, String description, boolean inStock, Integer categoryId, float price, float taxRate, float discountPercent, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.inStock = inStock;
        this.categoryId = categoryId;
        this.price = price;
        this.taxRate = taxRate;
        this.discountPercent = discountPercent;
        this.image = image;
    }
}

package com.example.restapi.implement.product;

import com.example.restapi.domain.product.Product;
import com.example.restapi.domain.product.ProductService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @brief: This Class is API`s Response.
 *
 * @description　ProductResponse is converted  from ProductDto,
 *               because of implementing pagination.
 * @Auther RYA234
 *
 * @Entity: {@link  Product}
 * @UseCase: {@link ProductService}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private List<ProductDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
    private int categoryId;
    private String categoryName;
}

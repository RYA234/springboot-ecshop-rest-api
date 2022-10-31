package com.example.restapi.domain.product;

import com.example.restapi.implement.product.ProductDto;
import com.example.restapi.implement.product.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    ProductResponse getProductsByCategory(int pageNo, int pageSize, int CategoryId);
    ProductDto getProductById(int productId);
}

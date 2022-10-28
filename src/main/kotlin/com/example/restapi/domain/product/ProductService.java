package com.example.restapi.domain.product;

import com.example.restapi.implement.product.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    public ProductResponse getProductsByCategory(int pageNo, int pageSize, int CategoryId);
}

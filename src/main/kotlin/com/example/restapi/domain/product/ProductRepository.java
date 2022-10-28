package com.example.restapi.domain.product;

import com.example.restapi.domain.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product,Integer> {
    @Query("select p from Product p where p.categoryId = ?1")
    public Page<Product> getProductsByCategory(int categoryId, Pageable pageable);
}

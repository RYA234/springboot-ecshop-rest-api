package com.example.restapi.domain.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Integer> {

    Page<Product> findByCategoryId(int categoryId, Pageable pageable);

    @Query("select p from Product p where p.id = ?1")
    Product getProductById(int productId);
}

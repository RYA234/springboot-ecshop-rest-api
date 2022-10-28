package com.example.restapi.implement.category;

import com.example.restapi.domain.category.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CategoryRepository extends PagingAndSortingRepository<Category,Integer> {


    @Override
    public List<Category> findAll();

    public Category findById(int id);
}

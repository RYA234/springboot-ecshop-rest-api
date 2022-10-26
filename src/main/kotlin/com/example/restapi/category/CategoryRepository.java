package com.example.restapi.category;

import com.example.restapi.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CategoryRepository extends PagingAndSortingRepository<Category,Integer> {


    @Override
    public List<Category> findAll();
}

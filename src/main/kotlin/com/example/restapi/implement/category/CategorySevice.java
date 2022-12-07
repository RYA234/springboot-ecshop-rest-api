package com.example.restapi.implement.category;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategorySevice {

    List<CategoryDto> listAll();

    List<CategoryDto> addChilren(List<CategoryDto> CategoryDtoList);

    CategoryDto findById(int categoryId);

}

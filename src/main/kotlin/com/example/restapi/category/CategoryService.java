package com.example.restapi.category;

import com.example.restapi.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDto> listAll(){
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtoList;
        categoryDtoList = categories.stream().map(this::mapToDTO).collect(Collectors.toList());
        return   addChilren(categoryDtoList);
    }
    public List<CategoryDto> addChilren(List<CategoryDto> CategoryDtoList){
        List<Integer> indexRef;
        indexRef = new ArrayList<>();
        for(CategoryDto categoryDto : CategoryDtoList){
            HashSet<Integer> children = new HashSet<>();
            indexRef.add(categoryDto.getId());
            if(categoryDto.getParent() != null){
                children.add(categoryDto.getId());
                CategoryDtoList.get(indexRef.indexOf(categoryDto.getParent())).setChildren(children);
            }
        }
        return CategoryDtoList;
    }
    // convert Entity into DTO
    private CategoryDto mapToDTO(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setAlias(category.getAlias());
        categoryDto.setParent(category.getParent());
        return categoryDto;
    }

    // convert DTO to entity
    private Category mapToEntity(CategoryDto categoryDto){
        Category category = new Category();
        categoryDto.setId(categoryDto.getId());
        categoryDto.setName(categoryDto.getName());
        categoryDto.setAlias(categoryDto.getAlias());
        categoryDto.setParent(categoryDto.getParent());
        return category;
    }



}

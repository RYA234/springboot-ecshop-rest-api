package com.example.restapi.category;

import com.example.restapi.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/api/category/all")
    public List<CategoryDto> listAll(){
        return categoryService.listAll();
    }
}

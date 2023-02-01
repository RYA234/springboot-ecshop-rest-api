package com.example.restapi.implement.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/api/category/all")
    public List<CategoryDto> listAll(){
        return categoryService.listAll();
    }
}

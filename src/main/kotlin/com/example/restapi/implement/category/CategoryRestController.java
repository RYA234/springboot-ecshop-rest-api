package com.example.restapi.implement.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryRestController {

    @Autowired
    private CategorySevice categorySevice;

    @GetMapping("/api/category/all")
    public List<CategoryDto> listAll(){
        return categorySevice.listAll();
    }
}

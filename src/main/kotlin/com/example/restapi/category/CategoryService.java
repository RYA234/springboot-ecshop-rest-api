package com.example.restapi.category;

import com.example.restapi.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> listAll(){
        List<Category> rootCategories = categoryRepository.findRootCategories();
        return listHierachicalCategories(rootCategories);
    }

    private List<Category> listHierachicalCategories(List<Category> rootCategories){
        List<Category> hierachicalCategories = new ArrayList<>();
        for(Category rootCategory : rootCategories){
            hierachicalCategories.add(Category.copyFull(rootCategory));

            Set<Category> children = rootCategory.getChildren();

            for(Category subCategory : children){
                String name = "--" + subCategory.getName();
                hierachicalCategories.add(Category.copyFull(subCategory, name));

                listSubHierachicalCategories(hierachicalCategories, subCategory, 1);
            }
        }
        return hierachicalCategories;
    }

    private void listSubHierachicalCategories(List<Category> hierachicalCategories,
            Category parent,int subLevel){
        Set<Category> children = parent.getChildren();
        int newSubLevel = subLevel +1;
        for(Category subCategory : children){
            String name = "";
            for(int i =0; i< newSubLevel; i++){
                name += "--";
            }
            name += subCategory.getName();
            hierachicalCategories.add((Category.copyFull(subCategory, name)));

            listSubHierachicalCategories(hierachicalCategories,subCategory, newSubLevel);
        }

    }


}

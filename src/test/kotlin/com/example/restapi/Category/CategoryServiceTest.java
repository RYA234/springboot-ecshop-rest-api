package com.example.restapi.Category;

import com.example.restapi.category.CategoryDto;
import com.example.restapi.category.CategoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class CategoryServiceTest {
    @Autowired
    private CategoryService categoryService;
    // ToDo CategorySevice nullになるのでテストできない　原因は不明
    @Test
    @DisplayName("CategoryDtoにChildrenが付与されたか確認")
    public void CheckIfAddChildren(){
        List<CategoryDto> beforeCategoryDtoList = new ArrayList<>();
        CategoryDto categoryDto1 = new CategoryDto(1, "PC","PC",null);
        CategoryDto categoryDto2 = new CategoryDto(2, "DeskTop","DeskTop",1);
        CategoryDto categoryDto3 = new CategoryDto(3, "Note","Note",3);

        beforeCategoryDtoList.add(categoryDto1);
        beforeCategoryDtoList.add(categoryDto2);
        beforeCategoryDtoList.add(categoryDto3);

        HashSet<Integer> firstChildren = new HashSet<>();
        firstChildren.add(2);
        if( (categoryService.addChilren(beforeCategoryDtoList)).get(0).getChildren() == firstChildren){
            System.out.println("good");
        }

        //assert(categoryService.addChilren(beforeCategoryDtoList).equals());
        //CategoryDto(Integer id, String name, String alias, Integer parent)

        //beforeCategoryDtoList.add()

    }

}

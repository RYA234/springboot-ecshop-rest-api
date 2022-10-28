package com.example.restapi.Category;

import com.example.restapi.implement.category.CategoryDto;
import com.example.restapi.implement.category.CategoryRepository;
import com.example.restapi.implement.category.CategoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CategoryServiceTest {

    @MockBean
    private CategoryRepository repo;
    @InjectMocks
    private CategoryService categoryService;

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

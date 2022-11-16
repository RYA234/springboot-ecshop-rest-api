package com.example.restapi.Category;

import com.example.restapi.domain.category.Category;
import com.example.restapi.implement.category.CategoryDto;
import com.example.restapi.implement.category.CategoryRepository;
import com.example.restapi.implement.category.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CategoryServiceTest {

    public enum OrderNumber{
        listAll_case1,
        findById_case1,

        addChildren_case1,
        addChildren_case2,
        addChildren_case3,
        addChildren_case4,
        addChildren_case5,
    }
    @MockBean
    private CategoryRepository repo;
    @InjectMocks
    private CategoryService categoryService;

    List<CategoryDto> categoryDtoList = new ArrayList<>();
    @BeforeEach
    public void prepareCategoryDto(){

        categoryDtoList.add(new CategoryDto(0, "null","null",null));
        categoryDtoList.add(new CategoryDto(1, "PC","PC",null));
        categoryDtoList.add(new CategoryDto(2, "DeskTop","DeskTop",1));
        categoryDtoList.add(new CategoryDto(3, "notePC","notePC",1));
        categoryDtoList.add(new CategoryDto(4, "TabletPC","TabletPC",1));

        categoryDtoList.add(new CategoryDto(5, "Food","Food",null));
        categoryDtoList.add(new CategoryDto(6, "Meat","Meat",5));
        categoryDtoList.add(new CategoryDto(7, "Fish","Fish",5));
        categoryDtoList.add(new CategoryDto(8, "Vegetable","Vegetable",5));
        categoryDtoList.add(new CategoryDto(9, "Rice","Rice",5));

        categoryDtoList.add(new CategoryDto(10, "stationary","stationary",null));
        categoryDtoList.add(new CategoryDto(11, "pencil","pencil",10));
        categoryDtoList.add(new CategoryDto(12, "notebook","notebook",10));
        categoryDtoList.add(new CategoryDto(13, "ballpen","ballpen",10));
        categoryDtoList.add(new CategoryDto(14, "eraser","eraser",10));



    }

    @Test
    @DisplayName("listAllmethodのテスト")
    public void listAllTest(){
        // given_prepared
        List<Category> actualCategoryList = new ArrayList<>();
        actualCategoryList.add(new Category(0, "null","null",null));
        actualCategoryList.add(new Category(1, "PC","PC",null));
        actualCategoryList.add(new Category(2, "DeskTop","DeskTop",1));
        actualCategoryList.add(new Category(3, "notePC","notePC",1));
        actualCategoryList.add(new Category(4, "TabletPC","TabletPC",1));

        actualCategoryList.add(new Category(5, "Food","Food",null));
        actualCategoryList.add(new Category(6, "Meat","Meat",5));
        actualCategoryList.add(new Category(7, "Fish","Fish",5));
        actualCategoryList.add(new Category(8, "Vegetable","Vegetable",5));
        actualCategoryList.add(new Category(9, "Rice","Rice",5));

        actualCategoryList.add(new Category(10, "stationary","stationary",null));
        actualCategoryList.add(new Category(11, "pencil","pencil",10));
        actualCategoryList.add(new Category(12, "notebook","notebook",10));
        actualCategoryList.add(new Category(13, "ballpen","ballpen",10));
        actualCategoryList.add(new Category(14, "eraser","eraser",10));
        // do service
        Mockito.doReturn(actualCategoryList).when(repo).findAll();


        assertIterableEquals(categoryService.addChilren(categoryDtoList), categoryService.listAll());

    }

    @Test
    @DisplayName("findByIdのテスト")
    public void findById(){
        CategoryDto expetectedCategoryDto;

        CategoryDto acutalCategoryDto;
//        assert(expetectedCategoryDto.equals(acutalCategoryDto));


    }
    @Test
    @DisplayName("AddChildren　id=1のPCカテゴリーの子カテゴリーがDeskTop,notePC,TabletPC{2,3,4}であるか確認。")
    public void CheckIfAddChildren_(){
        int testCategoryId = 1;
        // create expectedData
        Integer[] tmpExpectedchildren = {2,3,4};
        HashSet<Integer> expectedChildren = new HashSet<>(List.of(tmpExpectedchildren));

        // add actualChildren
        categoryService.addChilren(categoryDtoList);
        HashSet<Integer> actualChildren = new HashSet<>(categoryDtoList.get(testCategoryId).getChildren());
        assertIterableEquals(actualChildren,expectedChildren);


    }
    @Test
    @DisplayName("AddChildren　id=2のDeskTopの子カテゴリーがnullであることを確認。")
    public void CheckIfAddChildren_Case_id_2(){
        int testCategoryId = 2;
        // create expectedData
        Integer[] tmpExpectedchildren = {};
        HashSet<Integer> expectedChildren = new HashSet<>(List.of(tmpExpectedchildren));

        // add actualChildren
        categoryService.addChilren(categoryDtoList);
        HashSet<Integer> actualChildren = new HashSet<>(categoryDtoList.get(testCategoryId).getChildren());

        assertIterableEquals(actualChildren,expectedChildren);
    }

    @Test
    @DisplayName("AddChildren　id=3のDeskTopの子カテゴリーがnullであることを確認。")
    public void CheckIfAddChildren_Case_id_3(){
        int testCategoryId = 3;
        // create expectedData
        Integer[] tmpExpectedchildren = {};
        HashSet<Integer> expectedChildren = new HashSet<>(List.of(tmpExpectedchildren));

        // add actualChildren
        categoryService.addChilren(categoryDtoList);
        HashSet<Integer> actualChildren = new HashSet<>(categoryDtoList.get(testCategoryId).getChildren());

        assertIterableEquals(actualChildren,expectedChildren);
    }

    @Test
    @DisplayName("AddChildren　id=4のDeskTopの子カテゴリーがnullであることを確認。")
    public void CheckIfAddChildren_Case_id_4(){
        int testCategoryId = 4;
        // create expectedData
        Integer[] tmpExpectedchildren = {};
        HashSet<Integer> expectedChildren = new HashSet<>(List.of(tmpExpectedchildren));

        // add actualChildren
        categoryService.addChilren(categoryDtoList);
        HashSet<Integer> actualChildren = new HashSet<>(categoryDtoList.get(testCategoryId).getChildren());

        assertIterableEquals(actualChildren,expectedChildren);
    }

    @Test
    @DisplayName("AddChildren　id=5のFOODカテゴリーの子カテゴリーがMeat,Fish,Vegetable,Rice{6,7,8,9}であるか確認。")
    public void CheckIfAddChildren_Case_id_5(){
        int testCategoryId = 5;
        // create expectedData
        Integer[] tmpExpectedchildren = {6,7,8,9};
        HashSet<Integer> expectedChildren = new HashSet<>(List.of(tmpExpectedchildren));

        // add actualChildren
        categoryService.addChilren(categoryDtoList);
        HashSet<Integer> actualChildren = new HashSet<>(categoryDtoList.get(testCategoryId).getChildren());

        assertIterableEquals(actualChildren,expectedChildren);
    }



}

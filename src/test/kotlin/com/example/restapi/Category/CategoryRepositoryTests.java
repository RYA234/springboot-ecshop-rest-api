package com.example.restapi.Category;

import com.example.restapi.category.CategoryRepository;
import com.example.restapi.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CategoryRepositoryTests {
    @Autowired
    private CategoryRepository repository;

    @Test
    public void testCreateRootCategory(){
        Category category = new Category("Electonics");
        Category savedCategory = repository.save(category);

        assertThat(savedCategory.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateSubCategory(){
        Category parent = new Category(18);
        //Category camera = new Category("Camera",parent);
        Category smartphones = new Category("iPhone",parent);
        repository.save(smartphones);
        // Category savedCategory = repository.save(subCategory);
        // repository.saveAll(List.of(camera,smartphones));

        //assertThat(savedCategory.getId()).isGreaterThan(0);
    }

    @Test
    public void testGetCategory(){
       Category category = repository.findById(15).get();
       System.out.println(category.getName());

       Set<Category> children = category.getChildren();
       for(Category subCategory : children){
           System.out.println(subCategory.getName());
       }
       assertThat(children.size()).isGreaterThan(0);

    }
    @Test
    public void testprintHierachicalCategories(){
        Iterable<Category> categories = repository.findAll();
        for(Category category:categories){
            if(category.getParent() == null){
                System.out.println(category.getName());
                Set<Category> children = category.getChildren();

                for( Category subCategory : children ){
                    System.out.println("--" + subCategory.getName());
                    printChildren(subCategory, 1);
                }
            }
        }
    }

    private void printChildren(Category parent, int subLevel){
        int newSubLevel = subLevel +1;
        Set<Category> children = parent.getChildren();

        for( Category subCategory : children ){
            for(int i = 0; i < newSubLevel; i++) {
                System.out.print("--");
            }
            System.out.println(subCategory.getName());

        }
    }

    @Test
    public void testListRootCategories(){
        List<Category> rootCategories = repository.findRootCategories();
        rootCategories.forEach(cat -> System.out.println((cat.getName())));

    }


}

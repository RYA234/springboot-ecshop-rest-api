package com.example.restapi.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(length = 128, nullable = false, unique = true)
    private String name;

    @Column(length = 64, nullable = false, unique = true)
    private String alias;

    @OneToMany(mappedBy = "parent")
    @OrderBy("name asc")
    private Set<Category> children = new HashSet<>();

    @OneToOne
    @JoinColumn(name ="parent_id")
    private Category parent;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
        this.alias = name;
    }

    public Category(Integer id) {
        this.id = id;
    }

    public Category(String name, Category parent) {
        this(name);
        this.parent = parent;
    }



    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public Category getParent() {
        return parent;
    }

    public Set<Category> getChildren() {
        return children;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setChildren(Set<Category> children) {
        this.children = children;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public static Category copyFull(Category category){
        Category copyCategory = new Category();
        copyCategory.setId((category.getId()));
        copyCategory.setName((category.getName()));
        copyCategory.setAlias((category.getAlias()));

        return copyCategory;
    }

    public static Category copyFull(Category category,String name){
        Category copyCategory = Category.copyFull(category);
        copyCategory.setName(name);
        return copyCategory;
    }
}

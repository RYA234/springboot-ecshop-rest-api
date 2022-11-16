package com.example.restapi.implement.category;

import lombok.Data;

import java.util.HashSet;

@Data
public class CategoryDto {
    private Integer id;
    private String name;
    private String alias;
    private Integer parent;
    private HashSet<Integer> children = new HashSet<>();
    public void setChildren(HashSet<Integer> children) {
        this.children.addAll(children);
    }
    public HashSet<Integer> getChildren() {
        return children;
    }

    public CategoryDto() {
    }

    public CategoryDto(Integer id, String name, String alias, Integer parent) {
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.parent = parent;
    }
    public CategoryDto(Integer id, String name, String alias, Integer parent, HashSet<Integer> children) {
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.parent = parent;
        this.children = children;
    }
}

package com.example.restapi.category;

import com.example.restapi.entity.Category;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.HashSet;

@Data
public class CategoryDto {
    private Integer id;
    private String name;
    private String alias;
    private Integer parent;
    private HashSet<Integer> children = new HashSet<>();
    public void setChildren(HashSet<Integer> children) {
        this.children = children;
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

}

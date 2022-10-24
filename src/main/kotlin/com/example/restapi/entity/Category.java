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

    public Category() {
    }

    public Category(String name) {
        this.name = name;
        this.alias = name;
    }

    public Category(String name,Category parent) {
        this(name);
        this.parent = parent;
    }

    @OneToOne
    @JoinColumn(name ="parent_id")
    private Category parent;

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
}

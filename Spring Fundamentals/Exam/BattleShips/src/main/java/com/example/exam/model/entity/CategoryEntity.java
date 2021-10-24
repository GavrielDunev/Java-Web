package com.example.exam.model.entity;

import com.example.exam.model.entity.enums.CategoryNameEnum;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity{

    @Enumerated(EnumType.ORDINAL)
    private CategoryNameEnum name;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    public CategoryNameEnum getName() {
        return name;
    }

    public CategoryEntity setName(CategoryNameEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CategoryEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}

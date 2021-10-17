package com.example.coffeeshop.service;

import com.example.coffeeshop.model.entity.CategoryEntity;
import com.example.coffeeshop.model.entity.enums.CategoryNameEnum;

public interface CategoryService {

    void initializeCategories();

    CategoryEntity findByCategoryEnumName(CategoryNameEnum name);
}

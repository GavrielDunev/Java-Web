package com.example.pathfinder.service;

import com.example.pathfinder.model.entity.CategoryEntity;
import com.example.pathfinder.model.entity.enums.CategoryEnum;

public interface CategoryService {

    CategoryEntity findCategoryByName(CategoryEnum categoryEnum);

}

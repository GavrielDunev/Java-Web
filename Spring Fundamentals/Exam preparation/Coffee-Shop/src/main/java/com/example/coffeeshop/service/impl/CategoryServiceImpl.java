package com.example.coffeeshop.service.impl;

import com.example.coffeeshop.model.entity.CategoryEntity;
import com.example.coffeeshop.model.entity.enums.CategoryNameEnum;
import com.example.coffeeshop.repository.CategoryRepository;
import com.example.coffeeshop.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initializeCategories() {
        if (this.categoryRepository.count() == 0) {
            Arrays.stream(CategoryNameEnum.values())
                    .forEach(categoryNameEnum -> {

                        CategoryEntity category = new CategoryEntity();
                        category.setName(categoryNameEnum);

                        switch (categoryNameEnum.name()) {
                            case "COFFEE" -> category.setNeededTime(2);
                            case "DRINK" -> category.setNeededTime(1);
                            case "OTHER" -> category.setNeededTime(5);
                            case "CAKE" -> category.setNeededTime(10);
                        }

                        this.categoryRepository.save(category);
                    });
        }
    }

    @Override
    public CategoryEntity findByCategoryEnumName(CategoryNameEnum name) {

        return this.categoryRepository.findByName(name).orElse(null);
    }
}

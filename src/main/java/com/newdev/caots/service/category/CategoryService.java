package com.newdev.caots.service.category;

import com.newdev.caots.entities.category.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAllCategory();

    List<Category> findAllCategoryByMenu(int menuId);

    Category findCategoryById(int id);

    boolean saveCategory(Category category);

    boolean deleteCategory(Category category);

}

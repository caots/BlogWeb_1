package com.newdev.caots.service_impl.category;

import com.newdev.caots.entities.Menu;
import com.newdev.caots.entities.category.Category;
import com.newdev.caots.repository.category.CategoryRepository;
import com.newdev.caots.repository.category.MenuRepository;
import com.newdev.caots.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CategoryService_Impl implements CategoryService {

    private final static Logger LOGGER = Logger.getLogger(CategoryService_Impl.class.getName());

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MenuRepository menuRepository;


    @Override
    public List<Category> findAllCategory() {
        return categoryRepository.findAllCategory();
    }

    @Override
    public List<Category> findAllCategoryByMenu(int menuId) {
        try {
            Menu menu = menuRepository.findById(menuId);
            if (menu.isStatus()) return categoryRepository.findByMenu(menu);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-big-by-menu-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public Category findCategoryById(int id) {
        try {
            Category bigCategory = categoryRepository.findById(id);
            if (bigCategory.isStatus()) return bigCategory;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-big-category-by-id-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean saveCategory(Category bigCategory) {
        try {

            categoryRepository.save(bigCategory);
            return true;

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-big-category-error : {0}", ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteCategory(Category category) {
        try {
            if (category.isStatus()) {
                category.setStatus(false);
                categoryRepository.save(category);
                return true;
            }

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-big-category-error : {0}", ex.getMessage());
        }
        return false;
    }
}

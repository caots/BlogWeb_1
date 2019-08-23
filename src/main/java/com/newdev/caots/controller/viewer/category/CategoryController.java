package com.newdev.caots.controller.viewer.category;

import com.newdev.caots.common.Token;
import com.newdev.caots.entities.category.Category;
import com.newdev.caots.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("api/v1/public/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<Category>> findAllCategory(
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            List<Category> bigCategories = categoryService.findAllCategory();
            return new ResponseEntity<>(bigCategories, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping(value = "/find-by-menu")
    public ResponseEntity<List<Category>> findAllCategoryByMenu(
            @RequestParam("menu-id") int menuId,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            List<Category> bigCategories = categoryService.findAllCategoryByMenu(menuId);
            return new ResponseEntity<>(bigCategories, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/find-by-id")
    public ResponseEntity<Category> findCategoryById(
            @RequestParam("id") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            return new ResponseEntity<>(categoryService.findCategoryById(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}

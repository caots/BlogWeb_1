package com.newdev.caots.controller.admin.category;

import com.newdev.caots.entities.Menu;
import com.newdev.caots.entities.Record;
import com.newdev.caots.entities.category.Category;
import com.newdev.caots.service.RecordService;
import com.newdev.caots.service.category.CategoryService;
import com.newdev.caots.service.category.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("api/v1/admin")
@RolesAllowed("ROLE_ADMIN")
public class AdminCategoryController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RecordService recordService;


    //=========================== Menu ==========================

    @PostMapping(value = "/menu")
    public ResponseEntity<Object> createMenu(@RequestBody Menu menu) {
        Record record = recordService.findByName("menu");
        menu.setStatus(true);
        if (menuService.saveBMenu(menu)) {
            record.setNumber(record.getNumber() + 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>(menu, HttpStatus.OK);
        } else
            return new ResponseEntity<>("create menu fail !", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/menu")
    public ResponseEntity<Object> updateMenu(@RequestBody Menu menu) {
        if (menuService.saveBMenu(menu))
            return new ResponseEntity<>(menu, HttpStatus.OK);
        else
            return new ResponseEntity<>("update menu fail!", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/menu/delete")
    public ResponseEntity<String> deleteMenu(@RequestParam("id") int menuId) {
        Record record = recordService.findByName("menu");

        Menu menu = menuService.findMenuById(menuId);
        if (menuService.deleteBMenu(menu)) {
            record.setNumber(record.getNumber() - 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>("delete menu success", HttpStatus.OK);
        } else
            return new ResponseEntity<>("delete menu fails", HttpStatus.BAD_REQUEST);
    }


    //===========================  Category ===========================

    @PostMapping(value = "/category")
    public ResponseEntity<Object> createBigCategory(@RequestBody Category category,
                                                    @RequestParam(value = "menu-id") int menuId) {
        Record record = recordService.findByName("category");

        Menu menu = menuService.findMenuById(menuId);
        category.setMenu(menu);
        category.setStatus(true);
        if (categoryService.saveCategory(category)) {
            record.setNumber(record.getNumber() + 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else
            return new ResponseEntity<>("create category fail!", HttpStatus.BAD_REQUEST);

    }

    @PutMapping(value = "/category")
    public ResponseEntity<Object> updateBigCategory(@RequestBody Category category) {
        if (categoryService.saveCategory(category))
            return new ResponseEntity<>(category, HttpStatus.OK);
        else
            return new ResponseEntity<>("update category fail!", HttpStatus.BAD_REQUEST);
    }


    @PutMapping(value = "category/delete")
    public ResponseEntity<String> deleteMediumCategory(@RequestParam("id") int id) {
        Record record = recordService.findByName("category");

        Category category = categoryService.findCategoryById(id);
        if (categoryService.deleteCategory(category)) {
            record.setNumber(record.getNumber() - 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>("delete category success", HttpStatus.OK);
        } else
            return new ResponseEntity<>("delete category fails", HttpStatus.BAD_REQUEST);
    }
}

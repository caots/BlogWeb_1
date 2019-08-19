package com.newdev.caots.controller.viewer.category;

import com.newdev.caots.common.Token;
import com.newdev.caots.entities.Menu;
import com.newdev.caots.service.RecordService;
import com.newdev.caots.service.category.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/public/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;


    @GetMapping(value = "/all")
    public ResponseEntity<List<Menu>> findAllMenu(
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            List<Menu> menus = menuService.findAllMenu();
            return new ResponseEntity<>(menus, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/find-by-id")
    public ResponseEntity<Menu> findMenuById(
            @RequestParam("id") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {

            return new ResponseEntity<>(menuService.findMenuById(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

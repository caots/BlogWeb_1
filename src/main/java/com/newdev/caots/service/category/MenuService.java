package com.newdev.caots.service.category;

import com.newdev.caots.entities.category.Menu;

import java.util.List;

public interface MenuService {

    List<Menu> findAllMenu();

    Menu findMenuById(int id);

    boolean saveBMenu(Menu menu);

    boolean deleteBMenu(Menu menu);
}

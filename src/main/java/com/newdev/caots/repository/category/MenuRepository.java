package com.newdev.caots.repository.category;


import com.newdev.caots.entities.category.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    Menu findByName(String name);

    Menu findById(int id);

    List<Menu> findByStatus(boolean status);

}

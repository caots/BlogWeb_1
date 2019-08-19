package com.newdev.caots.repository.category;

import com.newdev.caots.entities.Menu;
import com.newdev.caots.entities.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {


    List<Category> findByMenu(Menu menu);

    Category findById(int id);

    @Query("select ca from Category ca where ca.status=true")
    List<Category> findAllCategory();

}
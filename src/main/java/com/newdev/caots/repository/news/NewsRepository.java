package com.newdev.caots.repository.news;

import com.newdev.caots.entities.category.Category;
import com.newdev.caots.entities.news.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {

    @Query("SELECT n FROM News n WHERE n.status = true and n.title LIKE CONCAT('%',:title,'%')")
    Page<News> findAllNewsByTitlePage(@Param("title") String name, Pageable pageable);

    @Query("SELECT n FROM News n WHERE n.status = true and n.title LIKE CONCAT('%',:title,'%')")
    List<News> findAllNewsByTitle(@Param("title") String name);

    @Query("select n from News n where n.status = true order by n.timePost desc")
    Page<News> findAllNewsPage( Pageable pageable);

    @Query("select n from News n where n.status = true and  n.category.id= :id  order by n.timePost desc")
    List<News> findAllNewsByCategoryId(@Param("id") int id);

    Page<News> findByCategory(Category category, Pageable pageable);

    @Query("select n from News n where n.status = true order by n.view desc")
    Page<News> findByView(Pageable pageable);

    News findById(int id);

}

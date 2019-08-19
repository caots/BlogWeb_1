package com.newdev.caots.repository.news;

import com.newdev.caots.entities.news.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    Page<Tag> findByStatus(boolean status, Pageable pageable);

    @Query(value = " SELECT t From Tag t where  t.status = true")
    List<Tag> findAllTag();

    Tag findByName(String name);

    Tag findById(int id);

}
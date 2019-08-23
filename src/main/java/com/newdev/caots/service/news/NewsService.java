package com.newdev.caots.service.news;

import com.newdev.caots.entities.category.Category;
import com.newdev.caots.entities.news.News;
import com.newdev.caots.entities.news.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Set;

public interface NewsService {
    List<News> findAllNewsByNameTitlePage(String name, Pageable pageable);

    int sizeOfNewsByNameTitle(String name);

    List<News> findAllNewsPage(Pageable pageable);

    List<News> findAllNewsByCategoryPage(Category category, Pageable pageable);

    int sizeOfNewsByCategory(int topicId);

    List<News> findByView(Pageable pageable);

    List<News> findByTag(int tagId);

    int sizeOfNewsByTag(int tagId);

    News findById(int id);

    boolean saveNews(News news);

    boolean deleteNews(News news);

    Sort sortData(String sort, String field);

    Set<Integer> listTagAdd(String content);


    List<News> findAllNewsByNameTitle(String title);
}

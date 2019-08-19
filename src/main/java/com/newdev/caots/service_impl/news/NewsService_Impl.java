package com.newdev.caots.service_impl.news;

import com.newdev.caots.entities.category.Category;
import com.newdev.caots.entities.news.News;
import com.newdev.caots.entities.news.Tag;
import com.newdev.caots.repository.news.NewsRepository;
import com.newdev.caots.repository.news.TagRepository;
import com.newdev.caots.service.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class NewsService_Impl implements NewsService {

    private final static Logger LOGGER = Logger.getLogger(NewsService_Impl.class.getName());

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private TagRepository tagRepository;


    @Override
    public List<News> findAllNewsByNameTitlePage(String name, Pageable pageable) {
        return newsRepository.findAllNewsByTitlePage(name, pageable).getContent();
    }

    @Override
    public int sizeOfNewsByNameTitle(String name) {
        return newsRepository.findAllNewsByTitle(name).size();

    }

    @Override
    public List<News> findAllNewsPage(Pageable pageable) {
        try {
            return newsRepository.findAllNewsPage(pageable).getContent();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-news-page-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<News> findAllNewsByCategoryPage(Category category, Pageable pageable) {
        try {
            return newsRepository.findByCategory(category, pageable).getContent();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-news-by-category-page-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public int sizeOfNewsByCategory(int topicId) {
        try {
            return newsRepository.findAllNewsByCategoryId(topicId).size();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "size-of-news-by-topic-error : {0}", ex.getMessage());
        }
        return 0;
    }

    @Override
    public List<News> findByView(Pageable pageable) {
        try {
            return newsRepository.findByView(pageable).getContent();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-by-view-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<News> findByTag(int tagId) {
        try {
            Tag tag = tagRepository.findById(tagId);
            return tag.getNews();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-by-tag-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public int sizeOfNewsByTag(int tagId) {
        try {
            Tag tag = tagRepository.findById(tagId);
            return tag.getNews().size();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "size-of-news-by-tag-error : {0}", ex.getMessage());
        }
        return 0;
    }

    @Override
    public News findById(int id) {
        try {
            News news = newsRepository.findById(id);
            if (news.isStatus()) return news;

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-by-id-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean saveNews(News news) {
        try {
            newsRepository.save(news);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-news-error : {0}", ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteNews(News news) {
        try {
            if (news.isStatus()) {
                news.setStatus(false);
                newsRepository.save(news);
                return true;
            }

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-news-error : {0}", ex.getMessage());
        }
        return false;
    }

    @Override
    public Sort sortData(String sort, String field) {
        Sort sortable = null;
        if (sort.equals("ASC")) {
            sortable = Sort.by(field).ascending();
        }
        if (sort.equals("DESC")) {
            sortable = Sort.by(field).descending();
        }
        return sortable;

    }

}

package com.newdev.caots.service_impl.news;

import com.newdev.caots.entities.Record;
import com.newdev.caots.entities.category.Category;
import com.newdev.caots.entities.news.News;
import com.newdev.caots.entities.news.Tag;
import com.newdev.caots.repository.RecordRepository;
import com.newdev.caots.repository.news.NewsRepository;
import com.newdev.caots.repository.news.TagRepository;
import com.newdev.caots.service.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class NewsService_Impl implements NewsService {

    private final static Logger LOGGER = Logger.getLogger(NewsService_Impl.class.getName());

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private RecordRepository recordRepository;


    @Override
    public List<News> findAllNewsByNameTitlePage(String name, Pageable pageable) {
        return newsRepository.findAllNewsByTitlePage(name, pageable).getContent();
    }

    @Override
    public int sizeOfNewsByNameTitle(String name) {
        return newsRepository.findAllNewsByTitle(name).size();

    }

    @Override
    public List<News> findAllNews() {
        try {
            return newsRepository.findAllNews();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-news-page-error : {0}", ex.getMessage());
        }
        return null;
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

    @Override
    public Set<Integer> listTagAdd(String content) {
        Set<Integer> tagIds = new HashSet<>();
        System.out.println(content);
        Record record = recordRepository.findByName("tag");
        record.setNumber(record.getNumber() + 1);

        String[] nameTag = content.split("@");

        for (int i = 1; i < nameTag.length; i++) {
            nameTag[i].replaceAll("\\s++", "");
            nameTag[i].trim();
            System.out.println(nameTag[i]);
        }

        List<Tag> tags = tagRepository.findAll();
        for (int i = 1; i < nameTag.length; i++) {
            int index = 1;

            for (Tag tag : tags) {
                if (tag.getName().equals(nameTag[i])) {
                    index = -1;
                    break;
                }
            }
            System.out.println(index);
            if (index == 1) {
                Tag t = new Tag();
                t.setName(nameTag[i]);
                t.setStatus(true);
                tagRepository.save(t);
                Tag tag = findByNameUnique(t.getName());
                tagIds.add(tag.getId());
            } else {
                Tag tag = findByNameUnique(nameTag[i]);
                System.out.println(tag.getId());
                tagIds.add(tag.getId());
            }
        }
        return tagIds;
    }

    @Override
    public List<News> findAllNewsByNameTitle(String title) {
        try {
            return newsRepository.findAllNewsByTitle(title);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-news-by-title-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<News> findAllNewsByCategory(int categoryId) {
        try {
            return newsRepository.findAllNewsByCategoryId(categoryId);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-tag-by-name-error : {0}", ex.getMessage());
        }
        return null;
    }

    private Tag findByNameUnique(String name) {
        try {
            return tagRepository.findByName(name);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-tag-by-name-error : {0}", ex.getMessage());
        }
        return null;
    }

}

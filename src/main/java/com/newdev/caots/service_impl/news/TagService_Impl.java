package com.newdev.caots.service_impl.news;

import com.newdev.caots.entities.news.News;
import com.newdev.caots.entities.news.Tag;
import com.newdev.caots.repository.news.NewsRepository;
import com.newdev.caots.repository.news.TagRepository;
import com.newdev.caots.service.news.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class TagService_Impl implements TagService {

    private final static Logger LOGGER = Logger.getLogger(TagService_Impl.class.getName());

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public List<Tag> findAllTag() {
        try {
            return tagRepository.findAllTag();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-tag-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Tag> findByNews(int newsIs) {
        try {
            News news = newsRepository.findById(newsIs);
            return news.getTags();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-tag-by-product-id-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public Tag findById(int id) {
        try {
            Tag tag = tagRepository.findById(id);
            if (tag.isStatus()) return tag;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-tag-by-id-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean saveTag(Tag tag) {
        try {
            tagRepository.save(tag);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-tag-error : {0}", ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteTag(Tag tag) {
        try {
            if (tag.isStatus()) {
                tag.setStatus(false);
                tagRepository.save(tag);
                return true;
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-tag-error : {0}", ex.getMessage());
        }
        return false;
    }
}

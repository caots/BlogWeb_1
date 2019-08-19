package com.newdev.caots.service.news;


import com.newdev.caots.entities.news.Tag;

import java.util.List;

public interface TagService {

    List<Tag> findAllTag();

    List<Tag> findByNews(int newsIs);

    Tag findById(int id);

    boolean saveTag(Tag tag);

    boolean deleteTag(Tag tag);

}

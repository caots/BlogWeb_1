package com.newdev.caots.controller.admin.news;


import com.newdev.caots.entities.Record;
import com.newdev.caots.entities.news.News;
import com.newdev.caots.entities.news.Tag;
import com.newdev.caots.service.RecordService;
import com.newdev.caots.service.category.CategoryService;
import com.newdev.caots.service.news.NewsService;
import com.newdev.caots.service.news.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/admin/news")
@RolesAllowed("ROLE_ADMIN")
public class AdminNewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private TagService tagService;

    @PostMapping
    public ResponseEntity<Object> createNews(
            @RequestBody News news,
            @RequestParam(name = "category-id") int categoryId,
            @RequestParam("tag") String tagString
    ) {
        Record record = recordService.findByName("news");
        news.setStatus(true);
        news.setCategory(categoryService.findCategoryById(categoryId));
        news.setView(0);
        news.setTimePost(LocalDate.now());
        Set<Integer> listTagId = newsService.listTagAdd(tagString);

        List<Tag> tagList = new ArrayList<>();
        listTagId.forEach(id -> {
            Tag tag = tagService.findById(id);
            if (tag != null) {
                tagList.add(tag);
            }
        });
        news.setTags(tagList);
        if (newsService.saveNews(news)) {
            record.setNumber(record.getNumber() + 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>(news, HttpStatus.OK);
        } else
            return new ResponseEntity<>("create news fail", HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<Object> updateNews(@RequestBody News news,
                                             @RequestParam("list-tag") String listTag) {

        Set<Integer> listTagId = newsService.listTagAdd(listTag);

        List<Tag> tagOld = news.getTags();

        Set<Tag> tagTotal = new HashSet<>();

        List<Tag> tagList = new ArrayList<>();
        listTagId.forEach(id -> {
            Tag tag = tagService.findById(id);
            if (tag != null) {
                tagList.add(tag);
            }
        });

        tagTotal.addAll(tagOld);
        tagTotal.addAll(tagList);
        List<Tag> total = new ArrayList<>();
        total.addAll(tagTotal);
        news.setTags(total);

        if (newsService.saveNews(news))
            return new ResponseEntity<>(news, HttpStatus.OK);
        else
            return new ResponseEntity<>("update news fail", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/delete")
    public ResponseEntity<Object> deleteNews(@RequestParam("id") int id) {
        Record record = recordService.findByName("news");
        News news = newsService.findById(id);
        if (newsService.deleteNews(news)) {
            record.setNumber(record.getNumber() - 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>("delete news success", HttpStatus.OK);
        } else
            return new ResponseEntity<>("delete news fail", HttpStatus.BAD_REQUEST);
    }


}

package com.newdev.caots.controller.viewer.news;

import com.newdev.caots.common.Token;
import com.newdev.caots.entities.Record;
import com.newdev.caots.entities.category.Category;
import com.newdev.caots.entities.news.News;
import com.newdev.caots.entities.news.Tag;
import com.newdev.caots.service.RecordService;
import com.newdev.caots.service.category.CategoryService;
import com.newdev.caots.service.news.NewsService;
import com.newdev.caots.service.news.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("api/v1/public/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/page")
    public ResponseEntity<List<News>> findAllNews(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (page < 1) page = 1;
        if (size < 0) size = 0;
        if (header.equals(Token.tokenHeader)) {

            Pageable pageable = PageRequest.of(page - 1, size);
            List<News> newsList = newsService.findAllNewsPage(pageable);

            if (newsList != null) {
                return new ResponseEntity<>(newsList, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("find-by-title/all")
    public ResponseEntity<List<News>> findAllNewsByTitle(
            @RequestParam("title") String title,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {

            List<News> newsList = newsService.findAllNewsByNameTitle(title);

            if (newsList != null) {
                return new ResponseEntity<>(newsList, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("find-by-title/page")
    public ResponseEntity<List<News>> findAllNewsPage(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "type", required = false, defaultValue = "DESC") String type,
            @RequestParam(name = "field", required = false, defaultValue = "id") String field,
            @RequestParam("title") String title,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (page < 1) page = 1;
        if (size < 0) size = 0;
        if (header.equals(Token.tokenHeader)) {
            Sort sortable = newsService.sortData(type, field);

            Pageable pageable = PageRequest.of(page - 1, size, sortable);
            List<News> newsList = newsService.findAllNewsByNameTitlePage(title, pageable);

            if (newsList != null) {
                return new ResponseEntity<>(newsList, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "find-by-title/size")
    public ResponseEntity<Double> pageNumberNewsFindByTitleName(
            HttpServletResponse response,
            @RequestParam("title") String title,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            double result = Math.ceil((double) newsService.sizeOfNewsByNameTitle(title) / 10);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/size")
    public ResponseEntity<Double> pageNumberNews(
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            Record record = recordService.findByName("news");
            double result = Math.ceil((double) record.getNumber() / 10);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/find-by-id")
    public ResponseEntity<News> findMenuById(
            @RequestParam("id") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {

            return new ResponseEntity<>(newsService.findById(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/views")
    public ResponseEntity<List<News>> findAllNewsByViews(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (page < 1) page = 1;
        if (size < 0) size = 0;
        if (header.equals(Token.tokenHeader)) {
            Pageable pageable = PageRequest.of(page - 1, size);
            List<News> newsList = newsService.findByView(pageable);
            if (newsList != null) {
                return new ResponseEntity<>(newsList, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/category/page")
    public ResponseEntity<List<News>> findAllNewsByCategory(
            @RequestParam("category-id") int categoryId,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (page < 1) page = 1;
        if (size < 0) size = 0;
        if (header.equals(Token.tokenHeader)) {
            Pageable pageable = PageRequest.of(page - 1, size);
            Category category = categoryService.findCategoryById(categoryId);
            if (category.isStatus()) {
                List<News> newsList = newsService.findAllNewsByCategoryPage(category, pageable);
                return new ResponseEntity<>(newsList, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/category/size")
    public ResponseEntity<Double> pageNumberNews(
            HttpServletResponse response,
            @RequestParam("category-id") int categoryId,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            double result = Math.ceil((double) newsService.sizeOfNewsByCategory(categoryId) / 10);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/tag")
    public ResponseEntity<List<News>> findAllNewsByTag(
            @RequestParam("tag-id") int tagId,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            Tag tag = tagService.findById(tagId);
            if (tag.isStatus()) {
                List<News> newsList = newsService.findByTag(tagId);
                return new ResponseEntity<>(newsList, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

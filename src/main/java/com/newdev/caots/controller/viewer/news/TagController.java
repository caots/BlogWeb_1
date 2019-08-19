package com.newdev.caots.controller.viewer.news;

import com.newdev.caots.entities.news.Tag;
import com.newdev.caots.service.RecordService;
import com.newdev.caots.service.news.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("api/v1/public/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private RecordService recordService;

    @GetMapping("/all")
    public ResponseEntity<List<Tag>> findAllTag(
            HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<Tag> tags = tagService.findAllTag();
        if (tags != null) {
            return new ResponseEntity<>(tags, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/find-by-id")
    public ResponseEntity<Tag> findTagById(@RequestParam("id") int id,
                                            HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Tag tag = tagService.findById(id);
        if (tag != null) {
            return new ResponseEntity<>(tag, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/size")
    public ResponseEntity<Double> findAllTagPageSize(
            HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        double pageNumber = recordService.findByName("tag").getNumber();
        double result = Math.ceil(pageNumber / 10);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/find-by-news")
    public ResponseEntity<List<Tag>> findByNewsId(@RequestParam("news-id") int newsId,
                                                  HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<Tag> tags = tagService.findByNews(newsId);
        if (tags != null) return new ResponseEntity<>(tags, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
package com.newdev.caots.controller.admin.news;

import com.newdev.caots.entities.Record;
import com.newdev.caots.entities.news.Tag;
import com.newdev.caots.service.RecordService;
import com.newdev.caots.service.news.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("api/v1/admin/tag")
@RolesAllowed("ROLE_ADMIN")
public class AdminTagController {

    @Autowired
    private RecordService recordService;

    @Autowired
    private TagService tagService;

    @PostMapping
    public ResponseEntity<Object> createTag(@RequestBody Tag tag) {
        Record record = recordService.findByName("tag");
        tag.setStatus(true);
        if (tagService.saveTag(tag)) {
            record.setNumber(record.getNumber() + 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>(tag, HttpStatus.OK);
        } else
            return new ResponseEntity<>("create tag fail !", HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<Object> updateTag(@RequestBody Tag tag) {
        if (tagService.saveTag(tag))
            return new ResponseEntity<>(tag, HttpStatus.OK);
        else
            return new ResponseEntity<>("update tag fail!", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/delete")
    public ResponseEntity<String> deleteTag(@RequestParam("id") int tagId) {
        Record record = recordService.findByName("tag");
        Tag tag = tagService.findById(tagId);
        if (tagService.deleteTag(tag)) {
            record.setNumber(record.getNumber() - 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>("delete tag success", HttpStatus.OK);
        } else
            return new ResponseEntity<>("delete tag fails", HttpStatus.BAD_REQUEST);
    }


}

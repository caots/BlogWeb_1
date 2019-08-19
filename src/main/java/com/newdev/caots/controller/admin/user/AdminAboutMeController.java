package com.newdev.caots.controller.admin.user;

import com.newdev.caots.entities.Menu;
import com.newdev.caots.entities.Record;
import com.newdev.caots.entities.admin.AboutMe;
import com.newdev.caots.service.RecordService;
import com.newdev.caots.service.admin.AboutMeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;


@RestController
@RequestMapping("api/v1/admin/about-me")
@RolesAllowed("ROLE_ADMIN")
public class AdminAboutMeController {

    @Autowired
    private RecordService recordService;

    @Autowired
    private AboutMeService aboutMeService;

    @PostMapping
    public ResponseEntity<Object> createAboutMe(@RequestBody AboutMe aboutMe) {
        aboutMe.setStatus(true);
        if (aboutMeService.save(aboutMe)) {
            return new ResponseEntity<>(aboutMe, HttpStatus.OK);
        } else
            return new ResponseEntity<>("create about me fail !", HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<Object> updateAboutMe(@RequestBody AboutMe aboutMe) {
        if (aboutMeService.save(aboutMe))
            return new ResponseEntity<>(aboutMe, HttpStatus.OK);
        else
            return new ResponseEntity<>("update about me fail!", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/delete")
    public ResponseEntity<String> deleteAboutMe(@RequestParam("id") int id) {
        if (aboutMeService.delete(id)) {
            return new ResponseEntity<>("delete about me success", HttpStatus.OK);
        } else
            return new ResponseEntity<>("delete about me fails", HttpStatus.BAD_REQUEST);
    }

}

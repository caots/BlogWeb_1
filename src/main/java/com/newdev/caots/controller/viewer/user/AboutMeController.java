package com.newdev.caots.controller.viewer.user;

import com.newdev.caots.common.Token;
import com.newdev.caots.entities.admin.AboutMe;
import com.newdev.caots.service.admin.AboutMeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/public/about-me")
public class AboutMeController {

    @Autowired
    private AboutMeService aboutMeService;


    @GetMapping(value = "/all")
    public ResponseEntity<List<AboutMe>> findAllAboutMe(
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            List<AboutMe> aboutMes = aboutMeService.findAllAboutMe();
            return new ResponseEntity<>(aboutMes, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/find-by-id")
    public ResponseEntity<AboutMe> findById(
            @RequestParam("id") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {

            return new ResponseEntity<>(aboutMeService.findById(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

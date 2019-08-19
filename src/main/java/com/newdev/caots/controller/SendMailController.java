package com.newdev.caots.controller;


import com.newdev.caots.entities.UserMail;
import com.newdev.caots.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/public/send-email")
public class SendMailController {

    @Autowired
    private SendMailService sendMailService;


    @GetMapping
    public ResponseEntity<Object> sendEmail(
            @RequestParam("email") String email,
            @RequestParam("title") String title,
            @RequestParam("content") String content

    ) {
        boolean result = sendMailService.sendEmail(new UserMail(email, title, content));
        if (result)
            return new ResponseEntity<>("Congratulations! Your mail has been send to the user " + email, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
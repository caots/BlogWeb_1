package com.newdev.caots.entities;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserMail {
    private String emailAddress;
    private String title;
    private String content;

    public UserMail() {
    }

    public UserMail(String emailAddress, String title, String content) {
        this.emailAddress = emailAddress;
        this.title = title;
        this.content = content;
    }
}
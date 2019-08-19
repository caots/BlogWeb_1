package com.newdev.caots.service;


import com.newdev.caots.entities.UserMail;

public interface SendMailService {

    boolean sendEmail(UserMail userMail);
}
package com.newdev.caots.entities.json_playload;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterForm {

    private String email;

    private String password;

    private String fullName;

    private LocalDate initDate;

}
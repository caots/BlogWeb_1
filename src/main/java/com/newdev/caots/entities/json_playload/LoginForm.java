package com.newdev.caots.entities.json_playload;

import lombok.Data;

@Data
public class LoginForm {

    private String email;

    private String password;

    private boolean remember;
}
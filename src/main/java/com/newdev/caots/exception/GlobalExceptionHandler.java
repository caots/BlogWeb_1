package com.newdev.caots.exception;

import org.springframework.web.multipart.MultipartException;

public class GlobalExceptionHandler extends MultipartException {
    private String message;

    public GlobalExceptionHandler(String message) {
        super(message);
        this.message = message;
    }
}

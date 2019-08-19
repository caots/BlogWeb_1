package com.newdev.caots.exception;

public class ResourceNotFoundEx extends RuntimeException {

    private String message;

    public ResourceNotFoundEx(String message) {
        super(message);
        this.message = message;
    }
}

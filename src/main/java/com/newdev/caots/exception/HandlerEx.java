package com.newdev.caots.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class HandlerEx extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestEx.class)
    protected ResponseEntity<Object> getBadRequest(RuntimeException bex, WebRequest request) {
        return getResponse(bex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ResourceNotFoundEx.class})
    protected ResponseEntity<Object> notFoundResource(RuntimeException ex, WebRequest request) {
        return getResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ForbiddenEx.class})
    protected ResponseEntity<Object> forbidden(RuntimeException ex, WebRequest request) {
        return getResponse(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(GlobalExceptionHandler.class)
    public ResponseEntity<Object> globalExceptionHandler(MultipartException ex) {
        return getResponse(ex.getMessage(), HttpStatus.MULTI_STATUS);


    }


    public ResponseEntity<Object> getResponse(String message, HttpStatus httpStatus, Object... data) {
        Map<String, Object> map = new HashMap<>();
        if (data == null || (data.getClass().isArray() && data.length == 0)) {
            map.put("data", new HashMap<>());
        } else {
            map.put("data", data[0]);
        }
        map.put("message", message);
        return new ResponseEntity<>(map, httpStatus);
    }
}
package com.server.socialBees.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

public class BaseController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleIllegalArgumentException(MethodArgumentNotValidException ex){
        List<String> errors = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(
                fieldError -> errors.add(fieldError.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}

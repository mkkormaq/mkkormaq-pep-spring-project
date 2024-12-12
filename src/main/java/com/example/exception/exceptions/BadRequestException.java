package com.example.exception.exceptions;

import org.springframework.http.ResponseEntity;

public class BadRequestException extends RuntimeException{
    public BadRequestException (String str){
        super(str);
    }
}

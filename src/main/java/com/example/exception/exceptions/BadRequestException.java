package com.example.exception.exceptions;

// Defining custom exception for 400 responses
public class BadRequestException extends RuntimeException{
    public BadRequestException (String str){
        super(str);
    }
}

package com.example.exception.exceptions;


public class BadRequestException extends RuntimeException{
    public BadRequestException (String str){
        super(str);
    }
}

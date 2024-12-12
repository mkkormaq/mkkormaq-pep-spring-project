package com.example.exception.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String str){
        super(str);
    }
}

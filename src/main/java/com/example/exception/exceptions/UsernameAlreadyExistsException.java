package com.example.exception.exceptions;

// Defining custom exception for 409 responses
public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String str){
        super(str);
    }
}

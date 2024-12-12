package com.example.exception.exceptions;

// Defining custom exception for 401 responses
public class FaultyLoginCredentialException  extends RuntimeException{
    public FaultyLoginCredentialException(String str){
        super(str);
    }
}

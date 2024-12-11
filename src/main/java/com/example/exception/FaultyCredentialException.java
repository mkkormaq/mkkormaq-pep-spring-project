package com.example.exception;

public class FaultyCredentialException extends RuntimeException{
    public FaultyCredentialException(String str){
        super(str);
    }
}

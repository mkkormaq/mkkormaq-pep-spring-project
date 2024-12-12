package com.example.exception.exceptions;

public class FaultyLoginCredentialException  extends RuntimeException{
    public FaultyLoginCredentialException(String str){
        super(str);
    }
}

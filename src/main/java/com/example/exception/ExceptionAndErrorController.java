package com.example.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAndErrorController {
    
    @ExceptionHandler(FaultyCredentialException.class)
    public ResponseEntity<String> handleFaultyCredentials(FaultyCredentialException ex){
        return ResponseEntity.status(400).body(ex.getMessage());
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<String> handleUsernameExists(UsernameAlreadyExistsException ex){
        return ResponseEntity.status(409).body(ex.getMessage());
    }

}

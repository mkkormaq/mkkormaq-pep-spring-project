package com.example.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.exception.exceptions.BadRequestException;
import com.example.exception.exceptions.FaultyLoginCredentialException;
import com.example.exception.exceptions.UsernameAlreadyExistsException;


// Excpetion handling controller for the whole application utilizing the Spring Boot annotation for RestControllerAdvice.

@RestControllerAdvice
public class ExceptionAndErrorController {
    
    // General excpetion for 400 responses.
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleRuntimeException(BadRequestException ex){
        return ResponseEntity.status(400).body(ex.getMessage());
    }

    // Specific exception for failed login attempts.
    @ExceptionHandler(FaultyLoginCredentialException.class)
    public ResponseEntity<String> handleFaultyLogin(FaultyLoginCredentialException ex){
        return ResponseEntity.status(401).body(ex.getMessage());
    }

    // Specific exception for registration attempts if the username is already found in the database.
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<String> handleUsernameExists(UsernameAlreadyExistsException ex){
        return ResponseEntity.status(409).body(ex.getMessage());
    }

}

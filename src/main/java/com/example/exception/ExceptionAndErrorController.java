package com.example.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.exception.exceptions.BadRequestException;
import com.example.exception.exceptions.FaultyLoginCredentialException;
import com.example.exception.exceptions.UsernameAlreadyExistsException;

@RestControllerAdvice
public class ExceptionAndErrorController {
    

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleRuntimeException(BadRequestException ex){
        return ResponseEntity.status(400).body(ex.getMessage());
    }

    @ExceptionHandler(FaultyLoginCredentialException.class)
    public ResponseEntity<String> handleFaultyLogin(FaultyLoginCredentialException ex){
        return ResponseEntity.status(401).body(ex.getMessage());
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<String> handleUsernameExists(UsernameAlreadyExistsException ex){
        return ResponseEntity.status(409).body(ex.getMessage());
    }

}

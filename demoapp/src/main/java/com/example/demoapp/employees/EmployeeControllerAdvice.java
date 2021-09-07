package com.example.demoapp.employees;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmployeeControllerAdvice {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEmployeeNotfound(EmployeeNotFoundException exception){

        ErrorResponse errorResponse = new ErrorResponse(404 , exception.getMessage()) ;
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND) ;
    }
}
